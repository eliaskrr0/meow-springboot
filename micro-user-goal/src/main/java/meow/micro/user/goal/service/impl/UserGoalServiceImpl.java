package meow.micro.user.goal.service.impl;

import feign.FeignException;
import meow.common.dto.user.goal.enums.ActivityRate;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.common.dto.user.goal.enums.TypeTarget;
import meow.common.dto.user.profile.UserProfileDTO;
import meow.micro.user.goal.client.UserProfileClient;
import meow.micro.user.goal.exception.ResourceNotFoundException;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.repository.UserGoalRepository;
import meow.micro.user.goal.service.UserGoalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class UserGoalServiceImpl implements UserGoalService {
    private final UserGoalRepository userGoalRepository;
    private final UserProfileClient userProfileClient;


    public UserGoalServiceImpl(UserGoalRepository userGoalRepository, UserProfileClient userProfileClient) {
        this.userGoalRepository = userGoalRepository;
        this.userProfileClient = userProfileClient;
    }

    @Override
    public UserGoal getUserGoalById(Long id) {
        return userGoalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public UserGoal updateUserGoal(UserGoal userGoal) {
        if (userGoalRepository.existsById(userGoal.getIdUserGoal())) {
            UserProfileDTO profile;
            try {
                profile = userProfileClient.getProfile(userGoal.getIdUserProfile());
            } catch (FeignException e) {
                throw new ResourceNotFoundException(userGoal.getIdUserProfile());
            }

            // Cálculo de calorías
            BigDecimal calories = calculateCalories(userGoal.getTypeTarget(), userGoal.getActivityRate(), profile);

            // Cálculo de macros reutilizando las calorías
            Macros macros = calculateMacros(userGoal.getTypeTarget(), profile, calories);
            userGoal.setCaloriesTarget(macros.calories());
            userGoal.setProteinTarget(BigDecimal.valueOf(macros.proteinG()));
            userGoal.setCarbsTarget(BigDecimal.valueOf(macros.carbsG()));
            userGoal.setFatTarget(BigDecimal.valueOf(macros.fatG()));

            return userGoalRepository.save(userGoal);
        } else {
            throw new ResourceNotFoundException(userGoal.getIdUserGoal());
        }
    }

    private BigDecimal calculateCalories(TypeTarget target, ActivityRate activityRate, UserProfileDTO profile) {
        double height = profile.getHeight();
        double weight = 22 * Math.pow(height / 100.0, 2);
        double bmr;
        if (profile.getGender() == TypeGender.MASCULINE) {
            bmr = 10 * weight + 6.25 * height - 5 * profile.getAge() + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * profile.getAge() - 161;
        }
        double maintenance = bmr * activityRate.getFactor();
        double factor = switch (target) {
            case GAIN_WEIGHT_AGGRESSIVELY -> 1.20;
            case GAIN_WEIGHT_MODERATELY -> 1.15;
            case GAIN_WEIGHT_SLOWLY -> 1.10;
            case MAINTAIN_WEIGHT -> 1.00;
            case LOSE_WEIGHT_SLOWLY -> 0.90;
            case LOSE_WEIGHT_MODERATELY -> 0.85;
            case LOSE_WEIGHT_AGGRESSIVELY -> 0.80;
        };
        return BigDecimal.valueOf(maintenance * factor).setScale(2, RoundingMode.HALF_UP);
    }

    private Macros calculateMacros(TypeTarget target, UserProfileDTO profile, BigDecimal calories) {
        double caloriesValue = calories.doubleValue();
        double height = profile.getHeight();
        double weightKg = 22 * Math.pow(height / 100.0, 2);

        double protPerKg = switch (target) {
            case LOSE_WEIGHT_AGGRESSIVELY -> 2.4;
            case LOSE_WEIGHT_MODERATELY -> 2.2;
            case LOSE_WEIGHT_SLOWLY, MAINTAIN_WEIGHT -> 2.0;
            case GAIN_WEIGHT_SLOWLY, GAIN_WEIGHT_MODERATELY, GAIN_WEIGHT_AGGRESSIVELY -> 1.8;
        };
        double fatPerKg = switch (target) {
            case LOSE_WEIGHT_AGGRESSIVELY -> 0.6;
            case LOSE_WEIGHT_MODERATELY -> 0.7;
            case LOSE_WEIGHT_SLOWLY -> 0.8;
            case MAINTAIN_WEIGHT -> 0.9;
            case GAIN_WEIGHT_SLOWLY, GAIN_WEIGHT_MODERATELY, GAIN_WEIGHT_AGGRESSIVELY -> 0.8;
        };

        double minProtPerKg = 1.6;
        double minFatPerKg = 0.6;

        int proteinG = (int) Math.round(protPerKg * weightKg);
        int fatG = (int) Math.round(fatPerKg * weightKg);

        double kcalFromProt = proteinG * 4.0;
        double kcalFromFat = fatG * 9.0;
        double kcalLeft = caloriesValue - kcalFromProt - kcalFromFat;

        int minFatG = (int) Math.round(minFatPerKg * weightKg);
        int minProtG = (int) Math.round(minProtPerKg * weightKg);

        if (kcalLeft < 0) {
            int reducibleFat = Math.max(0, fatG - minFatG);
            int needFromFat = (int) Math.ceil((-kcalLeft) / 9.0);
            int reduceFat = Math.min(reducibleFat, needFromFat);
            fatG -= reduceFat;

            kcalFromFat = fatG * 9.0;
            kcalLeft = caloriesValue - kcalFromProt - kcalFromFat;
        }
        if (kcalLeft < 0) {
            int reducibleProt = Math.max(0, proteinG - minProtG);
            int needFromProt = (int) Math.ceil((-kcalLeft) / 4.0);
            int reduceProt = Math.min(reducibleProt, needFromProt);
            proteinG -= reduceProt;

            kcalFromProt = proteinG * 4.0;
            kcalLeft = caloriesValue - kcalFromProt - (fatG * 9.0);
        }

        int carbsG = (int) Math.max(0, Math.round(kcalLeft / 4.0));

        return new Macros(
                calories.setScale(0, RoundingMode.HALF_UP),
                proteinG,
                carbsG,
                fatG
        );
    }

    private record Macros(BigDecimal calories, int proteinG, int carbsG, int fatG) {
    }
}
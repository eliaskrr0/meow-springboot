package meow.micro.user.goal.service.impl;

import meow.common.dto.user.goal.enums.ActivityRate;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.common.dto.user.goal.enums.converter.TypeTarget;
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
    public UserGoal saveUserGoal(UserGoal userGoal) {
        UserProfileDTO profile = userProfileClient.getProfile(userGoal.getIdUserProfile());
        userGoal.setCaloriesTarget(calculateCalories(userGoal.getTypeTarget(), userGoal.getActivityRate(), profile));
        return userGoalRepository.save(userGoal);
    }

    @Override
    public UserGoal updateUserGoal(UserGoal userGoal) {
        if (userGoalRepository.existsById(userGoal.getIdUserGoal())) {
            UserProfileDTO profile = userProfileClient.getProfile(userGoal.getIdUserProfile());
            userGoal.setCaloriesTarget(calculateCalories(userGoal.getTypeTarget(), userGoal.getActivityRate(), profile));
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
}
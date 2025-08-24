package meow.micro.user.goal.service.impl;

import meow.common.dto.user.goal.enums.ActivityRate;
import meow.common.dto.user.goal.enums.TypeTarget;
import meow.common.dto.user.profile.UserProfileDTO;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.micro.user.goal.client.UserProfileClient;
import meow.micro.user.goal.exception.ResourceNotFoundException;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.repository.UserGoalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGoalServiceImplTest {
    @Mock
    private UserGoalRepository userGoalRepository;
    @Mock
    private UserProfileClient userProfileClient;
    @InjectMocks
    private UserGoalServiceImpl userGoalServiceImpl;

    @Test
    void getUserGoalById() {
        // Arrange
        Long id = 1L;
        UserGoal userGoal = getUserGoalList().getFirst();
        when(userGoalRepository.findById(id)).thenReturn(Optional.of(userGoal));

        // Act
        UserGoal result = userGoalServiceImpl.getUserGoalById(id);

        // Assert
        assertEquals(userGoal, result);
        verify(userGoalRepository, times(1)).findById(id);
    }

    @Test
    void updateUserGoal_whenIdExists_updateData() {
        // Arrange
        UserGoal userGoal = getUserGoalList().getFirst();
        when(userGoalRepository.existsById(userGoal.getIdUserGoal())).thenReturn(true);
        when(userGoalRepository.save(userGoal)).thenReturn(userGoal);
        when(userProfileClient.getProfile(userGoal.getIdUserProfile())).thenReturn(new meow.common.dto.user.profile.UserProfileDTO());

        // Act
        UserGoal result = userGoalServiceImpl.updateUserGoal(userGoal);
        assertEquals(userGoal, result);

        // Assert
        verify(userGoalRepository, times(1)).existsById(userGoal.getIdUserGoal());
        verify(userGoalRepository, times(1)).save(userGoal);
        verify(userProfileClient, times(1)).getProfile(userGoal.getIdUserProfile());
    }

    @Test
    void updateUserGoal_whenIdNotExists_throwException() {
        // Arrange
        UserGoal userGoal = getUserGoalList().getFirst();
        when(userGoalRepository.existsById(userGoal.getIdUserGoal())).thenReturn(false);

        // Assert & Act
        assertThrows(ResourceNotFoundException.class, () -> userGoalServiceImpl.updateUserGoal(userGoal));
    }

    @ParameterizedTest
    @EnumSource(TypeTarget.class)
    void calculateCalories_returnsExpectedValue_forMasculine(TypeTarget target) throws Exception {
        UserProfileDTO profile = new UserProfileDTO(1L, "Mohammed", TypeGender.MASCULINE, 25, 173, BigDecimal.ZERO);

        BigDecimal result = invokeCalculateCalories(target, ActivityRate.LIGHT, profile);
        BigDecimal expected = expectedCalories(target, ActivityRate.LIGHT, profile);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @EnumSource(TypeTarget.class)
    void calculateCalories_returnsExpectedValue_forFeminine(TypeTarget target) throws Exception {
        UserProfileDTO profile = new UserProfileDTO(2L, "Sokaina", TypeGender.FEMININE, 19, 163, BigDecimal.ZERO);

        BigDecimal result = invokeCalculateCalories(target, ActivityRate.MODERATE, profile);
        BigDecimal expected = expectedCalories(target, ActivityRate.MODERATE, profile);

        assertEquals(expected, result);
    }

    private BigDecimal invokeCalculateCalories(TypeTarget target, ActivityRate activityRate, UserProfileDTO profile) throws Exception {
        Method method = UserGoalServiceImpl.class.getDeclaredMethod("calculateCalories", TypeTarget.class, ActivityRate.class, UserProfileDTO.class);
        method.setAccessible(true);
        return (BigDecimal) method.invoke(userGoalServiceImpl, target, activityRate, profile);
    }

    private BigDecimal expectedCalories(TypeTarget target, ActivityRate activityRate, UserProfileDTO profile) {
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

    private static List<UserGoal> getUserGoalList() {
        UserGoal userGoalFirst = new UserGoal(1L, new BigDecimal("50.2"), new BigDecimal("150.56"), new BigDecimal("25.6"), new BigDecimal("1200"), 1L, TypeTarget.GAIN_WEIGHT_SLOWLY, ActivityRate.LIGHT);
        UserGoal userGoalSecond = new UserGoal(2L, new BigDecimal("54.2"), new BigDecimal("180.56"), new BigDecimal("25.68"), new BigDecimal("1246"), 2L, TypeTarget.LOSE_WEIGHT_MODERATELY, ActivityRate.SEDENTARY);

        return List.of(userGoalFirst, userGoalSecond);
    }
}
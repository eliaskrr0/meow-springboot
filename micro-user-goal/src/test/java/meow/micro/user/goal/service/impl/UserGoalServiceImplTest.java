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
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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

    @Test
    void calculateCalories_verifyUserIsMasculine() {
        // Arrange
        UserProfileDTO profile = getUserProfileDTOList().getFirst();

        // Act
        assertEquals(TypeGender.MASCULINE, profile.getGender());

    }

    @Test
    void calculateCalories_verifyUserIsFeminine() {
        // Arrange
        UserProfileDTO profile = getUserProfileDTOList().get(1);

        // Act
        assertEquals(TypeGender.FEMININE, profile.getGender());
    }

    @Test
    void calculateCalories_verifyFactor() throws Exception {
        UserGoal goal = getUserGoalList().getFirst();
        double result = TypeTarget.GAIN_WEIGHT_SLOWLY == goal.getTypeTarget() ? 1.10 : 1.20;

        assertEquals(1.10, result, 1e-9);
    }

    private static List<UserGoal> getUserGoalList() {
        UserGoal userGoalFirst = new UserGoal(1L, new BigDecimal("50.2"), new BigDecimal("150.56"), new BigDecimal("25.6"), new BigDecimal("1200"), 1L, TypeTarget.GAIN_WEIGHT_SLOWLY, ActivityRate.LIGHT);
        UserGoal userGoalSecond = new UserGoal(2L, new BigDecimal("54.2"), new BigDecimal("180.56"), new BigDecimal("25.68"), new BigDecimal("1246"), 2L, TypeTarget.LOSE_WEIGHT_MODERATELY, ActivityRate.SEDENTARY);

        return List.of(userGoalFirst, userGoalSecond);
    }

    private static List<UserProfileDTO> getUserProfileDTOList() {
        UserProfileDTO userProfileFirst = new UserProfileDTO(1L, "Mohammed", TypeGender.MASCULINE, 173, 25, new BigDecimal("73.2"));
        UserProfileDTO userProfileSecond = new UserProfileDTO(2L, "Sokaina", TypeGender.FEMININE, 163, 19, new BigDecimal("50.2"));

        return List.of(userProfileFirst, userProfileSecond);
    }
}
package meow.micro.user.goal.service.impl;

import meow.common.dto.user.goal.enums.ActivityRate;
import meow.common.dto.user.goal.enums.TypeTarget;
import meow.micro.user.goal.client.UserProfileClient;
import meow.micro.user.goal.exception.ResourceNotFoundException;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.repository.UserGoalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
        UserGoal userGoal = userGoalList().getFirst();
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
        UserGoal userGoal = userGoalList().getFirst();
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
        UserGoal userGoal = userGoalList().getFirst();
        when(userGoalRepository.existsById(userGoal.getIdUserGoal())).thenReturn(false);

        // Assert & Act
        assertThrows(ResourceNotFoundException.class, () -> userGoalServiceImpl.updateUserGoal(userGoal));
    }
    
    private static List<UserGoal> userGoalList() {
        UserGoal userGoalFirst = new UserGoal(1L, new BigDecimal("50.2"), new BigDecimal("150.56"), new BigDecimal("25.6"), new BigDecimal("1200"), 1L, TypeTarget.GAIN_WEIGHT_SLOWLY, ActivityRate.LIGHT);
        UserGoal userGoalSecond = new UserGoal(1L, new BigDecimal("50.2"), new BigDecimal("150.56"), new BigDecimal("25.6"), new BigDecimal("1200"), 1L, TypeTarget.GAIN_WEIGHT_SLOWLY, ActivityRate.LIGHT);

        return List.of(userGoalFirst, userGoalSecond);
    }
}
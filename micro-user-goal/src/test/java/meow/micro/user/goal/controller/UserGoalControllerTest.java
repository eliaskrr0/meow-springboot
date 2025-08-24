package meow.micro.user.goal.controller;

import meow.common.dto.user.goal.enums.ActivityRate;
import meow.common.dto.user.goal.enums.TypeTarget;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.service.UserGoalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGoalControllerTest {

    @Mock
    private UserGoalService userGoalService;

    @InjectMocks
    private UserGoalController userGoalController;

    @Test
    void getUserGoalById() {
        // Arrange
        Long id = 1L;
        UserGoal goal = new UserGoal(id,
                new BigDecimal("100.00"),
                new BigDecimal("200.00"),
                new BigDecimal("50.00"),
                new BigDecimal("2100.00"),
                1L,
                TypeTarget.MAINTAIN_WEIGHT,
                ActivityRate.MODERATE);
        when(userGoalService.getUserGoalById(id)).thenReturn(goal);

        // Act
        ResponseEntity<UserGoal> response = userGoalController.getUserGoalById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(goal, response.getBody());
        verify(userGoalService).getUserGoalById(id);
    }

    @Test
    void updateUserGoal() {
        // Arrange
        Long id = 1L;
        UserGoal goal = new UserGoal(null,
                new BigDecimal("100.00"),
                new BigDecimal("200.00"),
                new BigDecimal("50.00"),
                new BigDecimal("2000.00"),
                1L,
                TypeTarget.MAINTAIN_WEIGHT,
                ActivityRate.SEDENTARY);
        UserGoal updatedGoal = new UserGoal(id,
                goal.getProteinTarget(),
                goal.getCarbsTarget(),
                goal.getFatTarget(),
                new BigDecimal("2100.00"),
                goal.getIdUserProfile(),
                goal.getTypeTarget(),
                goal.getActivityRate());
        when(userGoalService.updateUserGoal(goal)).thenReturn(updatedGoal);

        // Act
        ResponseEntity<UserGoal> response = userGoalController.updateUserGoal(id, goal);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedGoal, response.getBody());
        assertEquals(id, goal.getIdUserGoal());
        verify(userGoalService).updateUserGoal(goal);
    }

    @Test
    void getTypeTargets() {
        // Act
        ResponseEntity<List<TypeTarget>> response = userGoalController.getTypeTargets();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(TypeTarget.values()), response.getBody());
    }

    @Test
    void getActivityRates() {
        // Act
        ResponseEntity<List<ActivityRate>> response = userGoalController.getActivityRates();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(ActivityRate.values()), response.getBody());
    }
}
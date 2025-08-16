package meow.micro.user.goal.service;

import meow.micro.user.goal.model.UserGoal;

public interface UserGoalService {
    UserGoal getUserGoalById(Long id);
    UserGoal saveUserGoal(UserGoal userGoal);
    UserGoal updateUserGoal(UserGoal userGoal);
}

package meow.micro.user.goal.service.impl;

import meow.micro.user.goal.exception.ResourceNotFoundException;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.repository.UserGoalRepository;
import meow.micro.user.goal.service.UserGoalService;
import org.springframework.stereotype.Service;

@Service
public class UserGoalServiceImpl implements UserGoalService {
    private final UserGoalRepository userGoalRepository;

    public UserGoalServiceImpl(UserGoalRepository userGoalRepository) {
        this.userGoalRepository = userGoalRepository;
    }

    @Override
    public UserGoal getUserGoalById(Long id) {
        return userGoalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public UserGoal saveUserGoal(UserGoal userGoal) {
        return userGoalRepository.save(userGoal);
    }

    @Override
    public UserGoal updateUserGoal(UserGoal userGoal) {
        if (userGoalRepository.existsById(userGoal.getIdUser())) {
            return userGoalRepository.save(userGoal);
        } else {
            throw new ResourceNotFoundException(userGoal.getIdUser());
        }
    }
}

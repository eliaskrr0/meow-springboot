package meow.micro.user.profile.service.impl;

import exception.ResourceNotFoundException;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.repository.UserProfileRepository;
import meow.micro.user.profile.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateProfile(UserProfile userProfile) {
        if (repository.existsById(userProfile.getIdUserProfile())) {
            repository.save(userProfile);
        } else {
            throw new ResourceNotFoundException(userProfile.getIdUserProfile());
        }
    }
}

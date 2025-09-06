package meow.micro.user.profile.service.impl;

import meow.micro.user.profile.exception.ResourceNotFoundException;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.repository.UserProfileRepository;
import meow.micro.user.profile.service.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public UserProfile updateProfile(UserProfile userProfile) {
        if (repository.existsById(userProfile.getIdUserProfile())) {
            return repository.save(userProfile);
        } else {
            throw new ResourceNotFoundException(userProfile.getIdUserProfile());
        }
    }
}

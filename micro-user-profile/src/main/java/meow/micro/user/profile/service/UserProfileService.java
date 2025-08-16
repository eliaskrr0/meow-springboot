package meow.micro.user.profile.service;

import meow.micro.user.profile.model.UserProfile;

public interface UserProfileService {
    UserProfile findById(Long id);
    UserProfile updateProfile(UserProfile userProfile);
}

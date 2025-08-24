package meow.micro.user.profile.service.impl;

import meow.common.dto.user.profile.enums.TypeGender;
import meow.micro.user.profile.exception.ResourceNotFoundException;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {

    @Mock
    private UserProfileRepository repository;

    @InjectMocks
    private UserProfileServiceImpl service;

    @Test
    void findById_whenProfileExists_shouldReturnProfile() {
        UserProfile profile = createUserProfile(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(profile));

        UserProfile result = service.findById(1L);

        assertThat(result).isEqualTo(profile);
        verify(repository).findById(1L);
    }

    @Test
    void findById_whenProfileDoesNotExist_shouldReturnNull() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        UserProfile result = service.findById(1L);

        assertThat(result).isNull();
        verify(repository).findById(1L);
    }

    @Test
    void updateProfile_whenProfileExists_shouldSaveAndReturnProfile() {
        UserProfile profile = createUserProfile(1L);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(profile)).thenReturn(profile);

        UserProfile result = service.updateProfile(profile);

        assertThat(result).isEqualTo(profile);
        verify(repository).existsById(1L);
        verify(repository).save(profile);
    }

    @Test
    void updateProfile_whenProfileDoesNotExist_shouldThrowException() {
        UserProfile profile = createUserProfile(1L);
        when(repository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.updateProfile(profile))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("1");

        verify(repository).existsById(1L);
        verify(repository, never()).save(any());
    }

    private UserProfile createUserProfile(Long id) {
        UserProfile profile = new UserProfile();
        profile.setIdUserProfile(id);
        profile.setName("Juan");
        profile.setGender(TypeGender.MASCULINE);
        profile.setAge(30);
        profile.setHeight(170);
        profile.setWeight(new BigDecimal("70.5"));
        return profile;
    }
}
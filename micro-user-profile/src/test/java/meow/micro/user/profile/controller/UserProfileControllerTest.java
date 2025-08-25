package meow.micro.user.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserProfileService service;

    @Test
    void getProfile_whenUserExists_shouldReturnProfile() throws Exception {
        // Arrange
        UserProfile profile = createUserProfile(1L);
        when(service.findById(1L)).thenReturn(profile);

        // Act & Assert
        mockMvc.perform(get("/api/user/profile/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUserProfile").value(1L))
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.gender").value("Masculino"));

        verify(service).findById(1L);
    }

    @Test
    void getTypeGender_shouldReturnAllGenders() throws Exception {
        mockMvc.perform(get("/api/user/profile/gender"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Masculino"))
                .andExpect(jsonPath("$[1]").value("Femenino"));
    }

    @Test
    void updateProfile_whenDataIsValid_shouldReturnUpdatedProfile() throws Exception {
        // Arrange
        UserProfile request = createUserProfile(null);
        UserProfile updated = createUserProfile(1L);
        when(service.updateProfile(any(UserProfile.class))).thenReturn(updated);

        // Act & Assert
        mockMvc.perform(put("/api/user/profile/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUserProfile").value(1L));

        ArgumentCaptor<UserProfile> captor = ArgumentCaptor.forClass(UserProfile.class);
        verify(service).updateProfile(captor.capture());
        assertThat(captor.getValue().getIdUserProfile()).isEqualTo(1L);
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
package meow.micro.user.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.common.dto.user.profile.enums.TypeTarget;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.service.UserProfileService;
import meow.micro.user.profile.service.impl.UserProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Users", description = "API de usuarios")
@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {
    private final UserProfileServiceImpl service;

    public UserProfileController(UserProfileServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Obtener perfil de usuario")
    @RequestMapping("/{id}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Obtiene todos los tipos de genero")
    @GetMapping("/gender")
    public ResponseEntity<List<TypeGender>> getTypeGender() {
        return ResponseEntity.ok(Arrays.asList(TypeGender.values()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable Long id, @RequestBody UserProfile userProfile){
        userProfile.setIdUserProfile(id);
        UserProfile updated = service.updateProfile(userProfile);
        return ResponseEntity.ok(updated);
    }
}

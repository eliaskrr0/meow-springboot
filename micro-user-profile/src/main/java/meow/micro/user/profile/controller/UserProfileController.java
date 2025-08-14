package meow.micro.user.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.common.dto.user.profile.enums.TypeTarget;
import meow.micro.user.profile.model.UserProfile;
import meow.micro.user.profile.service.impl.UserProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/profile/{id}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Obtiene todos los tipos de objetivos")
    @GetMapping("/target")
    public ResponseEntity<List<TypeTarget>> getTypeTargets() {
        return ResponseEntity.ok(Arrays.asList(TypeTarget.values()));
    }

    @Operation(summary = "Obtiene todos los tipos de genero")
    @GetMapping("/gender")
    public ResponseEntity<List<TypeGender>> getTypeGender() {
        return ResponseEntity.ok(Arrays.asList(TypeGender.values()));
    }
}

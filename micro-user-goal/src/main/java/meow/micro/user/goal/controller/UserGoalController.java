package meow.micro.user.goal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import meow.common.dto.user.profile.enums.TypeTarget;
import meow.micro.user.goal.model.UserGoal;
import meow.micro.user.goal.service.UserGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "User goal", description = "API del objetivo del usuario")
@RestController
@RequestMapping("/api/user/goal")
public class UserGoalController {
    private final UserGoalService service;

    public UserGoalController(UserGoalService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene la información del objetivo del usuario")
    @GetMapping("/{id}")
    public ResponseEntity<UserGoal> getUserGoalById(@PathVariable Long id){
        UserGoal goal = service.getUserGoalById(id);
        return ResponseEntity.ok(goal);
    }

    @ApiResponse(responseCode = "201", description = "Objetivo del usuario creado correctamente")
    @Operation(summary = "Guarda el objetivo del usuario")
    @PostMapping
    public ResponseEntity<UserGoal> saveUserGoal(@RequestBody UserGoal userGoal){
        UserGoal goal = service.saveUserGoal(userGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(goal);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alimento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Actualiza el objetivo del usuario")
    @PutMapping("{id}")
    public ResponseEntity<UserGoal> updateUserGoal(@PathVariable Long id, @Valid @RequestBody UserGoal goal) {
        goal.setIdUserGoal(id);
        UserGoal updatedGoal = service.updateUserGoal(goal);
        return ResponseEntity.ok(updatedGoal);
    }

    @Operation(summary = "Obtiene todos los tipos de objetivos")
    @GetMapping
    public ResponseEntity<List<TypeTarget>> getTypeTargets() {
        return ResponseEntity.ok(Arrays.asList(TypeTarget.values()));
    }
}

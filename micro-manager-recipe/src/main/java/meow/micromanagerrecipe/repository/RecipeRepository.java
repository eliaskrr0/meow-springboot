package meow.micromanagerrecipe.repository;

import meow.micromanagerrecipe.model.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Override
    @EntityGraph(attributePaths = "ingredients")
    List<Recipe> findAll();

    @EntityGraph(attributePaths = "ingredients")
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Recipe> findByNameContainingIgnoreCase(@Param("name") String name);
}

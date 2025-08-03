CREATE TABLE tb_recipe (
    idRecipe BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    amount INT NOT NULL
);

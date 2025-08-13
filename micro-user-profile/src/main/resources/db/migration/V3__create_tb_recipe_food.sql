CREATE TABLE tb_rel_recipe_food (
    id_rel_recipe_food BIGSERIAL PRIMARY KEY,
    id_recipe BIGINT NOT NULL REFERENCES tb_recipe (id_recipe),
    id_food BIGINT NOT NULL,
    amount INT NOT NULL
);

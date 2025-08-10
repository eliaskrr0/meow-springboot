CREATE TABLE tb_recipe (
   id_recipe BIGSERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE,
   amount INT NOT NULL,
   id_food BIGINT NOT NULL,
   CONSTRAINT fk_tb_recipe_id_food FOREIGN KEY (id_food) REFERENCES tb_food (id_food)
);

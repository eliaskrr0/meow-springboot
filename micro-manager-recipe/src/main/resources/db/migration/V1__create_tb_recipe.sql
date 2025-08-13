CREATE TABLE tb_recipe (
   id_recipe BIGSERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE,
   id_food BIGINT NOT NULL,
   version BIGINT NOT NULL
);

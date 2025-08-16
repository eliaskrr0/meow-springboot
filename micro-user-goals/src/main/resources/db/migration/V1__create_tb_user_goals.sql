CREATE TABLE tb_user_goals (
    id_user_goals BIGSERIAL PRIMARY KEY,
    protein_target DECIMAL(10,2) NOT NULL,
    carbs_target DECIMAL(10,2) NOT NULL,
    fat_target DECIMAL(10,2) NOT NULL,
    calories_target DECIMAL(10,2) NOT NULL,
    type_target VARCHAR(18) NOT NULL
);

CREATE TABLE tb_user_goal (
    id_user_goal BIGSERIAL PRIMARY KEY,
    protein_target DECIMAL(10,2) NOT NULL,
    carbs_target DECIMAL(10,2) NOT NULL,
    fat_target DECIMAL(10,2) NOT NULL,
    calories_target DECIMAL(10,2) NOT NULL,
    id_user_profile BIGINT NOT NULL,
    type_target VARCHAR(20) NOT NULL,
    activity_rate VARCHAR(10) NOT NULL
);
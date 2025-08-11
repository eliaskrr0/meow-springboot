CREATE TABLE tb_food (
    id_food BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    unit_measure VARCHAR(10) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    protein_amount DECIMAL(10,2) NOT NULL,
    carbs_amount DECIMAL(10,2) NOT NULL,
    fat_amount DECIMAL(10,2) NOT NULL,
    calories DECIMAL(10,2) NOT NULL
);

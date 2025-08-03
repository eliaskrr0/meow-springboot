CREATE TABLE tb_food (
    idFood BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    unit_measure VARCHAR(10) NOT NULL,
    amount INT NOT NULL,
    protein_amount INT NOT NULL,
    carbs_amount INT NOT NULL,
    fat_amount INT NOT NULL,
    calories INT NOT NULL
);

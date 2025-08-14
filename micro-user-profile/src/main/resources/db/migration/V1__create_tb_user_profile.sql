CREATE TABLE tb_user_profile (
   id_user_profile BIGSERIAL PRIMARY KEY,
   gender VARCHAR(10) NOT NULL,
   name VARCHAR(30) NOT NULL UNIQUE,
   age INT NOT NULL,
   height INT NOT NULL,
   type_target VARCHAR(18) NOT NULL
);

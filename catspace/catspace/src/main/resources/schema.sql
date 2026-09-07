CREATE TABLE racaGato (
   id INT PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuario (
     id INT PRIMARY KEY AUTO_INCREMENT,
     nome VARCHAR(100) NOT NULL,
     email VARCHAR(100) NOT NULL UNIQUE,
     dataNascimento DATE NOT NULL,
     qtdGatosTem INT NOT NULL,
     senha VARCHAR(40) NOT NULL
);

CREATE TABLE corGato (
 id INT PRIMARY KEY AUTO_INCREMENT,
 nome VARCHAR(50) NOT NULL
);

CREATE TABLE corFavUsuario (
       fkUsuario INT NOT NULL,
       fkCor INT NOT NULL,
       PRIMARY KEY (fkUsuario, fkCor),
       FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
       FOREIGN KEY (fkCor) REFERENCES corGato(id)
);
CREATE TABLE preferencias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fkUsuario INT NOT NULL,
    fkRacaFavorita INT,
    FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
    FOREIGN KEY (fkRacaFavorita) REFERENCES racaGato(id)
);

CREATE TABLE personalidade(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45)
);


CREATE TABLE gato (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    urlFoto VARCHAR(255) NOT NULL,
    fkRaca INT,
    FOREIGN KEY (fkRaca) REFERENCES racaGato(id)
);

CREATE TABLE colecaoUsuario (
 fkUsuario INT NOT NULL,
 fkGato INT NOT NULL,
 dataGanhou TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (fkUsuario, fkGato),
 FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
 FOREIGN KEY (fkGato) REFERENCES gato(id)
);

-- populando raças gatos
INSERT INTO racaGato (nome) VALUES
('Angorá Turco'),
('Azul Russo'),
('Persa'),
('American Shorthair'),
('Ragdoll'),
('Mau Egípcio'),
('Bombaim'),
('Bobtail Japonês'),
('Bengal'),
('Siamês'),
('Sphynx'),
('Pelo Curto Brasileiro');

-- populando os gatinhos
INSERT INTO gato (nome,urlFoto, fkRaca) VALUES
('Mingau','http://localhost:8080/gato1.png', 1),
('Sashimi','http://localhost:8080/gato2.png', 2),
('Tronquilho','http://localhost:8080/gato3.png', 3),
('Marinheiro','http://localhost:8080/gato4.png', 4),
('Capitão','http://localhost:8080/gato5.png', 5),
('Riscado','http://localhost:8080/gato6.png', 6),
('Drácula','http://localhost:8080/gato7.png', 7),
('Grindewald','http://localhost:8080/gato8.png', 8),
('Tigreso','http://localhost:8080/gato9.png', 9),
('Nami','http://localhost:8080/gato10.png', 10),
('Baby','http://localhost:8080/gato11.png', 11),
('Batman','http://localhost:8080/gato12.png', 12);


INSERT INTO corGato (nome) VALUES
   ('Branco'),
   ('Cinza'),
   ('Creme'),
   ('Laranja'),
   ('Trigre'),
   ('Multicolor'),
   ('Preto');


INSERT INTO personalidade(nome) VALUES
        ('Preguiçoso'),
        ('Bravo'),
        ('Amoroso'),
        ('Brincalhão');

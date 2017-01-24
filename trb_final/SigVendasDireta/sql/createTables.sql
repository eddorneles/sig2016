DROP TABLE IF EXISTS representante;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS endereco;
DROP TABLE IF EXISTS trajeto;
DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS venda_produto;

CREATE TABLE IF NOT EXISTS representante (
    cod_representante SERIAL,
    nome varchar(30),
    cpf integer,
    rg varchar(20),
    nascimento date,
    cod_patrocinador integer,
    cod_endereco integer
);

CREATE TABLE IF NOT EXISTS produto (
    cod_produto SERIAL,
    nome varchar(40) NOT NULL,
    categoria varchar(30),
    descricao text
);

CREATE TABLE IF NOT EXISTS endereco (
    cod_endereco SERIAL,
    numero integer,
    logradouro varchar(25),
    cep integer,
    geom_endereco geometry( POINT, 4326 )
);

CREATE TABLE IF NOT EXISTS trajeto (
    cod_trajeto SERIAL,
    cod_representante integer,
    geom_trajeto geometry( LINESTRING , 4326 ),
    data DATE,
    horario_min TIME,
    horario_max TIME
);


CREATE TABLE IF NOT EXISTS venda (
    cod_venda SERIAL,
    cod_representante integer,
    geom_venda geometry( POINT , 4326 ),
    data DATE
);

CREATE TABLE IF NOT EXISTS bairro (
    nome varchar (30),
    cod_bairro varchar(30),
    geom_bairro geometry( POLYGON , 4326 )
);

CREATE TABLE IF NOT EXISTS venda_produto (
    cod_venda integer,
    cod_produto integer
);

--Adição de chaves

ALTER TABLE representante
    ADD PRIMARY KEY ( cod_representante );

ALTER TABLE produto
    ADD PRIMARY KEY ( cod_produto );

ALTER TABLE endereco
    ADD PRIMARY KEY ( cod_endereco );

ALTER TABLE venda
    ADD PRIMARY KEY ( cod_venda );

ALTER TABLE trajeto
    ADD PRIMARY KEY ( cod_trajeto );

ALTER TABLE venda_produto
    ADD PRIMARY KEY ( cod_venda , cod_produto );

--Adição de restrição de FKs
ALTER TABLE venda_produto
    ADD CONSTRAINT cod_venda FOREIGN KEY ( cod_venda ) REFERENCES venda ( cod_venda )
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    ADD CONSTRAINT cod_produto FOREIGN KEY ( cod_produto ) REFERENCES produto( cod_produto )
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE venda
    ADD CONSTRAINT cod_representante FOREIGN KEY ( cod_representante )
        REFERENCES representante (cod_representante ) ON DELETE NO ACTION ON UPDATE NO ACTION;

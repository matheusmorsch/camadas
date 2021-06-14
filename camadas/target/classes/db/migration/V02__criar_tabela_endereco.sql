CREATE SEQUENCE endereco_id_seq;
CREATE TABLE endereco ( id_endereco bigint primary key default nextval ('endereco_id_seq'),
cep varchar(10) , logradouro varchar(50), complemento varchar(30), bairro varchar(40),
localidade varchar(40), uf varchar(2), ibge int);

ALTER TABLE usuario ADD COLUMN id_endereco bigint,
ADD CONSTRAINT FK_ID_ENDERECO foreign key(id_endereco) REFERENCES endereco(id_endereco);
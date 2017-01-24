-- Database: sig_vendas

DROP DATABASE sig_vendas;

CREATE DATABASE sig_vendas
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;

ALTER DATABASE sig_vendas
  SET search_path = "$user", public, topology, tiger;

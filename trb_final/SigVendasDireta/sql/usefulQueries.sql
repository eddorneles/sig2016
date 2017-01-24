--USEFUL QUERIES --
SELECT version();

SELECT postgis_version();

--Verificar no BD se as extensões foram inseridas:
SELECT name, default_version, installed_version
    FROM pg_available_extensions
    WHERE name LIKE 'postgis%' OR name LIKE 'address%' OR name LIKE 'pgrou%';


--Listar todas as tabels do BD atual
SELECT *
    FROM information_schema.tables;

SELECT *
    FROM information_schema.tables
    WHERE table_name = 'bairro';

--CRIAR UM CONSULTA SQL A PARTIR DE UM SHAPEFILE --
shp2pgsql -I -S -s 4326 -g geom_bairro ./bairros bairro > shapefile.sql

--VERIFICAR BAIRROS B2 QUE TÊM INTERSEÇÃO COM B1 --
SELECT *
    FROM bairro b1, bairro b2
    WHERE ST_Intersects( b1.geom_bairro, b2.geom_bairro ) AND
        b2.cod_bairro = 'cohama' AND
        b2.cod_bairro <> b1.cod_bairro ;

-- Cambiar al contexto de la base de datos deseada
\c prime_tech_db;

-- Asegurar que el esquema existe
ALTER SCHEMA public OWNER TO postgres;
CREATE SCHEMA IF NOT EXISTS prime_tech_schema AUTHORIZATION postgres;

-- Verificar si el esquema se creó correctamente
SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'prime_tech_schema';

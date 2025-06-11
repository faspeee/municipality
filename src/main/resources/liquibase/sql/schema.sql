--liquibase formatted sql

--changeset aspeeencinaf:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


--changeset aspeeencinaf:2
CREATE TABLE IF NOT EXISTS municipality
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    region_code    VARCHAR(100),
    province_code     VARCHAR(100),
    municipality_code VARCHAR(100),
    municipality_sigle VARCHAR(100),
    municipality_name VARCHAR(100),
    region_name VARCHAR(100),
    cadastral_code VARCHAR(100),
    territorial_unit_type VARCHAR(100),
    capitals_municipality VARCHAR(100),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    altitude DOUBLE PRECISION
);
--changeset aspeeencinaf:3
CREATE INDEX IF NOT EXISTS idx_municipality_name
    ON municipality (municipality_name);
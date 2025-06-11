--liquibase formatted sql

--changeset aspeeencinaf:4
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
    region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude
) VALUES( '03', '019', '19001', '001', 'Acquanegra Cremonese', 'Lombardia', 'A039', '019', 'Cremona', 45.1679214, 9.8908104, 45.07942199707031);

--changeset aspeeencinaf:5
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
                          region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude)
VALUES( '15', '064', '64021', '021', 'Cassano Irpino', 'Campania', 'B997', '064', 'Avellino', 40.8725406, 15.0274423, 506.5948181152344);

--changeset aspeeencinaf:6
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
                          region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude
) VALUES ( '15', '065', '65139', '139', 'Serramezzana', 'Campania', 'I648', '065', 'Salerno', 40.2444768, 15.0318962, 507.3829040527344);

--changeset aspeeencinaf:7
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
                          region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude
) VALUES( '08', '034', '34025', '025', 'Noceto', 'Emilia-Romagna', 'F914', '034', 'Parma', 44.8093214, 10.178779, 75.3333969116211);

--changeset aspeeencinaf:8
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
                          region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude
) VALUES  ( '07', '010', '10047', '047', 'Recco', 'Liguria', 'H212', '210', 'Genova', 44.3624694, 9.144402, 8.798839569091797);

--changeset aspeeencinaf:9
INSERT INTO municipality (region_code, province_code, municipality_code, municipality_sigle, municipality_name,
                          region_name, cadastral_code, territorial_unit_type, capitals_municipality, latitude, longitude, altitude
) VALUES ( '05', '026', '26007', '007', 'Cappella Maggiore', 'Veneto', 'B678', '026', 'Treviso', 45.9696968, 12.3612016, 110.7504272460938);


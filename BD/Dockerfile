# Imagen oficial de PostgreSQL
FROM postgres:latest

COPY DDL_CalendarioLaboral.sql /docker-entrypoint-initdb.d/01_DDL_CalendarioLaboral.sql
COPY DML_CalendarioLaboral.sql /docker-entrypoint-initdb.d/02_DML_CalendarioLaboral.sql
-- changeset parfyonov:1
CREATE TABLE PERSONS (
    NAME varchar,
    SURNAME VARCHAR,
    AGE INT,
    PHONE_NUMBER VARCHAR,
    CITY_OF_LIVING VARCHAR,
    PRIMARY KEY (NAME, SURNAME, AGE)
);
-- rollback drop table PERSONS
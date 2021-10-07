CREATE TABLE OBSERVATIONS (
    observation_id INT AUTO_INCREMENT  PRIMARY KEY,
    source VARCHAR(250) DEFAULT NULL,
    code_list_code VARCHAR(250) DEFAULT NULL,
    code VARCHAR(250) NOT NULL,
    display_value VARCHAR(250) DEFAULT NULL,
    long_description VARCHAR(250) DEFAULT NULL,
    from_date DATE,
    to_date DATE,
    sorting_priority INTEGER
);
CREATE TABLE documents (
    code VARCHAR(250) PRIMARY KEY,
    source VARCHAR(250) DEFAULT NULL,
    code_list_code VARCHAR(250) DEFAULT NULL,
    display_value VARCHAR(250) DEFAULT NULL,
    long_description VARCHAR(250) DEFAULT NULL,
    from_date DATE DEFAULT NULL,
    to_date DATE DEFAULT NULL,
    sorting_priority INTEGER DEFAULT NULL
);
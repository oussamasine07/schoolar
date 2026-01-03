CREATE TABLE schools (
    id BIGSERIAL PRIMARY KEY,
    owner_id BIGINT NOT NULL,
    school_name VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    tax_id INTEGER,
    professional_tax INTEGER,
    cnss_affiliation INTEGER,
    commercial_register INTEGER,
    common_business_identifier BIGINT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_ready BOOLEAN NOT NULL DEFAULT FALSE
);

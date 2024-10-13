-- Create country table
CREATE TABLE IF NOT EXISTS country (
    country_code VARCHAR(2) PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL,
    country_phone_prefix VARCHAR(10)
);

-- Create address table
CREATE TABLE IF NOT EXISTS address (
    address_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    country_code VARCHAR(2) NOT NULL,
    street_address VARCHAR(50) NOT NULL,
    postal_code VARCHAR(25) NOT NULL,
    municipality VARCHAR(25) NOT NULL,
    state_province VARCHAR(25),
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (country_code) REFERENCES country(country_code)
);

-- Create organization table
CREATE TABLE IF NOT EXISTS organization (
    organization_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    organization_number VARCHAR(25) UNIQUE NOT NULL,
    organization_type VARCHAR(25) NOT NULL,
    organization_name VARCHAR(50) UNIQUE NOT NULL,
    address_id UUID NOT NULL,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    organization_id UUID NOT NULL,
    social_identity_number VARCHAR(13) UNIQUE NOT NULL,
    user_type VARCHAR(25) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(25),
    email VARCHAR(50) UNIQUE NOT NULL, -- Email is username
    user_description VARCHAR(500),
    address_id UUID NOT NULL,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (organization_id) REFERENCES organization(organization_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- Create role table
CREATE TABLE IF NOT EXISTS role (
    role_id SERIAL PRIMARY KEY,
    role VARCHAR(25) NOT NULL
);

-- Create user_role table
CREATE TABLE IF NOT EXISTS user_role (
    user_id UUID NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Create request table (before answer table)
CREATE TABLE IF NOT EXISTS request (
    request_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    answer_approved_id UUID,
    request_type VARCHAR(25) NOT NULL,
    request_description VARCHAR(255),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    answer_expiration_time TIMESTAMP,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create answer table (after request table)
CREATE TABLE IF NOT EXISTS answer (
    answer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    request_id UUID NOT NULL,
    user_id UUID NOT NULL,
    answer_type VARCHAR(10) NOT NULL,
    updated_time TIMESTAMP,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (request_id) REFERENCES request(request_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create organization_relationship table
CREATE TABLE IF NOT EXISTS organization_relationship (
    contractor_id UUID NOT NULL,
    subcontractor_id UUID NOT NULL,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (contractor_id, subcontractor_id),
    CHECK (contractor_id != subcontractor_id),
    FOREIGN KEY (contractor_id) REFERENCES organization(organization_id),
    FOREIGN KEY (subcontractor_id) REFERENCES organization(organization_id)
);

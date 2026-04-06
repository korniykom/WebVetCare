CREATE TABLE users
(
    id    VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles
(
    user_id VARCHAR(255) NOT NULL REFERENCES users (id),
    role    VARCHAR(50)  NOT NULL,
    PRIMARY KEY (user_id, role)
);

CREATE TABLE doctor_profiles
(
    user_id        VARCHAR(255) PRIMARY KEY REFERENCES users (id),
    specialization VARCHAR(255) NOT NULL
);

CREATE TABLE patient_profiles
(
    user_id              VARCHAR(255) PRIMARY KEY REFERENCES users (id),
    contact_phone_number VARCHAR(255) NOT NULL
);
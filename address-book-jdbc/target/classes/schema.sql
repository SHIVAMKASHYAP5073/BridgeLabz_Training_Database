DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          phone VARCHAR(20) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          address TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
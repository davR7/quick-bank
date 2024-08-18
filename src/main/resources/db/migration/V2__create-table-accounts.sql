CREATE TYPE accType AS ENUM ('CHECKING', 'SAVINGS');

CREATE TABLE accounts (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    acc_number VARCHAR(255) NOT NULL,
    acc_type accType NOT NULL,
    balance DOUBLE NOT NULL,
    owner_id UUID,
    FOREIGN KEY (owner_id) REFERENCES customers(id) ON DELETE CASCADE
);
CREATE SCHEMA IF NOT EXISTS main;

DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'main_user') THEN
        CREATE USER main_user WITH PASSWORD 'main_password';
    END IF;
END $$;

GRANT USAGE ON SCHEMA main TO main_user;
GRANT CREATE, CONNECT ON DATABASE postgres_db TO main_user;
GRANT ALL PRIVILEGES ON SCHEMA main TO main_user;
ALTER SCHEMA main OWNER TO main_user;

CREATE SEQUENCE IF NOT EXISTS main.customer_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS main.customer (
    customer_id BIGINT PRIMARY KEY DEFAULT nextval('main.customer_id_seq'),
    customer_identifier BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    type_customer CHAR(1) NOT NULL,
    reference_date TIMESTAMP NOT NULL
);
ALTER SEQUENCE main.customer_id_seq OWNED BY main.customer.customer_id;

CREATE TABLE IF NOT EXISTS main.customer_hist (
    customer_id_hist BIGINT PRIMARY KEY DEFAULT nextval('main.customer_id_seq'),
    customer_id BIGINT NOT NULL,
    customer_identifier BIGINT,
    name VARCHAR(50),
    type_customer CHAR(1),
    reference_date TIMESTAMP,
    operation_type VARCHAR(10) NOT NULL,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS main.invoice_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS main.invoice (
    invoice_id BIGINT PRIMARY KEY DEFAULT nextval('main.invoice_id_seq'),
    customer_id BIGINT NOT NULL REFERENCES main.customer(customer_id) ON DELETE CASCADE,
    transaction_id BIGINT NOT NULL,
    type_invoice VARCHAR(50) NOT NULL,
    amount DECIMAL(17,2) NOT NULL
);
ALTER SEQUENCE main.invoice_id_seq OWNED BY main.invoice.invoice_id;

CREATE TABLE IF NOT EXISTS main.invoice_hist (
    invoice_id_hist BIGINT PRIMARY KEY DEFAULT nextval('main.invoice_id_seq'),
    invoice_id BIGINT NOT NULL,
    customer_id BIGINT,
    transaction_id BIGINT,
    type_invoice VARCHAR(50),
    amount DECIMAL(17,2),
    operation_type VARCHAR(10) NOT NULL,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION main.log_customer_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO main.customer_hist (customer_id, customer_identifier, name, type_customer, reference_date, operation_type)
        VALUES (OLD.customer_id, OLD.customer_identifier, OLD.name, OLD.type_customer, OLD.reference_date, 'DELETE');
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO main.customer_hist (customer_id, customer_identifier, name, type_customer, reference_date, operation_type)
        VALUES (OLD.customer_id, OLD.customer_identifier, OLD.name, OLD.type_customer, OLD.reference_date, 'UPDATE');
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER customer_trigger
AFTER UPDATE OR DELETE ON main.customer
FOR EACH ROW
EXECUTE FUNCTION main.log_customer_changes();

CREATE OR REPLACE FUNCTION main.log_invoice_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO main.invoice_hist (invoice_id, customer_id, transaction_id, type_invoice, amount, operation_type)
        VALUES (OLD.invoice_id, OLD.customer_id, OLD.transaction_id, OLD.type_invoice, OLD.amount, 'DELETE');
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO main.invoice_hist (invoice_id, customer_id, transaction_id, type_invoice, amount, operation_type)
        VALUES (OLD.invoice_id, OLD.customer_id, OLD.transaction_id, OLD.type_invoice, OLD.amount, 'UPDATE');
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER invoice_trigger
AFTER UPDATE OR DELETE ON main.invoice
FOR EACH ROW
EXECUTE FUNCTION main.log_invoice_changes();

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA main TO main_user;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA main TO main_user;

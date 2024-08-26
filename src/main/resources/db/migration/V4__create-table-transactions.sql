CREATE TABLE transactions (
    id BIGSERIAL NOT NULL,
    product_id BIGINT NOT NULL,
    amount INT NOT NULL,
    type_transaction VARCHAR(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_transactions_product_id FOREIGN KEY (product_id) REFERENCES produtos (id)
);

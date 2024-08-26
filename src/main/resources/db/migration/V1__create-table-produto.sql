CREATE TABLE produtos (
    id BIGSERIAL NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    quant_estoque INT NOT NULL,
    quant_minimo INT NOT NULL,
    valor_custo DECIMAL(10,2) NOT NULL,
    valor_venda DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

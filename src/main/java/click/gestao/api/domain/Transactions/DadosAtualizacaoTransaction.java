package click.gestao.api.domain.Transactions;

import click.gestao.api.domain.produto.Produto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record DadosAtualizacaoTransaction(
        @NotNull(message = "O id da transação é obrigatório")
        Long id,

        @PositiveOrZero(message = "Quantidade escolhida deve ser maior ou igual a zero")
        Integer amount,

        @Enumerated(EnumType.STRING)
        TypeTransaction type_transaction,

        LocalDateTime date,

        Long product_id
) {
    public DadosAtualizacaoTransaction(Transaction transaction) {
        this(transaction.getId(), transaction.getAmount(), transaction.getType_transaction(), transaction.getDate(), transaction.getProduto().getId());
    }
}

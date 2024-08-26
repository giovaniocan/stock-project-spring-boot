package click.gestao.api.domain.Transactions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosCadastroTransaction(

        @NotNull(message = "O id do produto é obrigatório")
        Long product_id,

        @NotNull(message = "Quantidade é obrigatória")
        @PositiveOrZero(message = "Quantidade escolhida deve ser maior ou igual a zero")
        Integer amount,

        @NotNull(message = "Tipo de transação é obrigatório")
        TypeTransaction type_transaction
) {}

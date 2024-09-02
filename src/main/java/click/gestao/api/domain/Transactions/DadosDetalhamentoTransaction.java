package click.gestao.api.domain.Transactions;

import java.time.LocalDateTime;

public record DadosDetalhamentoTransaction(Long id, Long product_id, Integer amount, TypeTransaction type_transaction, LocalDateTime date) {

    public DadosDetalhamentoTransaction(Transaction transaction) {
        this(transaction.getId(), transaction.getProduto().getId(), transaction.getAmount(), transaction.getType_transaction(), transaction.getDate());
    }
}

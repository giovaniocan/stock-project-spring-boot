package click.gestao.api.domain.Transactions;

import java.time.LocalDateTime;

public record DadosDetalhamentoTransaction(Long id, Long product_id, String product_description, Integer amount, TypeTransaction type_transaction, LocalDateTime date) {

    public DadosDetalhamentoTransaction(Transaction transaction) {
        this(transaction.getId(), transaction.getProduto().getId(), transaction.getProduto().getDescricao(), transaction.getAmount(), transaction.getType_transaction(), transaction.getDate());
    }
}

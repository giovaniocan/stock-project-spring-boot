package click.gestao.api.domain.Transactions;

public record DadosDetalhamentoTransaction(Long id, Long product_id, Integer amount, TypeTransaction type_transaction) {

    public DadosDetalhamentoTransaction(Transaction transaction) {
        this(transaction.getId(), transaction.getProduto().getId(), transaction.getAmount(), transaction.getType_transaction());
    }
}

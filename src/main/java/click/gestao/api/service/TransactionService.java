package click.gestao.api.service;

import click.gestao.api.domain.Transactions.DadosCadastroTransaction;
import click.gestao.api.domain.Transactions.DadosDetalhamentoTransaction;
import click.gestao.api.domain.Transactions.Transaction;
import click.gestao.api.domain.Transactions.TypeTransaction;
import click.gestao.api.domain.ValidacaoException;
import click.gestao.api.repository.ProdutoRepository;
import click.gestao.api.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    ProdutoService productService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public DadosDetalhamentoTransaction createTransaction(DadosCadastroTransaction dados) {

        var product = produtoRepository.findById(dados.product_id()).orElseThrow(() -> new EntityNotFoundException("Produto com id " + dados.product_id() + " não encontrado"));

        if(!product.getAtivo()){
            throw new ValidacaoException("Produto com id " + dados.product_id() + " não está ativo");
        }

        productService.validateStock(dados.type_transaction(), product, dados.amount());

        var transaction = new Transaction(dados, product);

        var newTransaction = transactionRepository.save(transaction);

        productService.makeTransctionInStock(product.getId(), dados.amount(), dados.type_transaction());

        var detalhamento = new DadosDetalhamentoTransaction(newTransaction);

        return detalhamento;
    }
}

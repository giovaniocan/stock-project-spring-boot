package click.gestao.api.service;

import click.gestao.api.domain.Transactions.*;
import click.gestao.api.domain.ValidacaoException;
import click.gestao.api.repository.ProdutoRepository;
import click.gestao.api.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
            throw new HttpMessageNotReadableException("Produto com id " + dados.product_id() + " não está ativo");
        }

        productService.validateStock(dados.type_transaction(), product, dados.amount());

        var transaction = new Transaction(dados, product);

        var newTransaction = transactionRepository.save(transaction);

        productService.makeTransctionInStock(product.getId(), dados.amount(), dados.type_transaction());

        var detalhamento = new DadosDetalhamentoTransaction(newTransaction);

        return detalhamento;
    }

    public Page<DadosListagemTransaction> list(Pageable paginacao) {
        return transactionRepository.findAll(paginacao).map(DadosListagemTransaction::new);
    }

    public void deleteTransaction(Long id) {
        var transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação com id " + id + " não encontrada"));

        var newType = transaction.getType_transaction() == TypeTransaction.SAIDA ? TypeTransaction.ENTRADA : TypeTransaction.SAIDA;

        productService.makeTransctionInStock(transaction.getProduto().getId(), transaction.getAmount(), newType);

        transactionRepository.deleteById(id);
    }
}

package click.gestao.api.controller;

import click.gestao.api.domain.Transactions.DadosAtualizacaoTransaction;
import click.gestao.api.domain.Transactions.DadosCadastroTransaction;
import click.gestao.api.domain.Transactions.DadosDetalhamentoTransaction;
import click.gestao.api.domain.Transactions.DadosListagemTransaction;
import click.gestao.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTransaction> createTransaction(@RequestBody @Valid DadosCadastroTransaction dados, UriComponentsBuilder uriBuilder) {
        var transaction = transactionService.createTransaction(dados);

        var uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.id()).toUri();

        return ResponseEntity.created(uri).body(transaction);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransaction>> listTransactions(@PageableDefault(size = 25, sort = {"date"},direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = transactionService.list(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid DadosAtualizacaoTransaction dados){
        var transactionUpdated = transactionService.update(dados);

        return ResponseEntity.ok(transactionUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);

        return ResponseEntity.noContent().build();
    }

}

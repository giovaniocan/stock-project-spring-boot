package click.gestao.api.controller;

import click.gestao.api.domain.Transactions.DadosCadastroTransaction;
import click.gestao.api.domain.Transactions.DadosDetalhamentoTransaction;
import click.gestao.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

}

package click.gestao.api.domain.Transactions;

import click.gestao.api.domain.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "transactions")
@Entity(name = "Transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Produto produto;


    private Integer amount;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type_transaction;

    private LocalDateTime date;

    public Transaction(@Valid DadosCadastroTransaction dados, Produto produto) {
        this.amount = dados.amount();
        this.type_transaction = dados.type_transaction();
        this.produto = produto;
        this.date = dados.date();
    }

    public void updateInfo(DadosAtualizacaoTransaction dados, Produto product){
        if(dados.amount() != null) {
            this.amount = dados.amount();
        }
        if(dados.type_transaction() != null) {
            this.type_transaction = dados.type_transaction();
        }
        if(dados.date() != null) {
            this.date = dados.date();
        }
        if(dados.product_id() != null) {
            this.produto = product;
        }
    }
}

package click.gestao.api.domain.Transactions;

import click.gestao.api.domain.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Transaction(@Valid DadosCadastroTransaction dados, Produto produto) {
        this.amount = dados.amount();
        this.type_transaction = dados.type_transaction();
        this.produto = produto;
    }
}

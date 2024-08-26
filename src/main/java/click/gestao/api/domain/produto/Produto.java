package click.gestao.api.domain.produto;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Integer quant_estoque;
    private Double valor_custo;
    private Double valor_venda;
    private Integer quant_minimo;
    private Boolean ativo;

    public Produto(DadosCadastroProduto dados) {
        this.descricao = dados.descricao();
        this.quant_estoque = dados.quant_estoque();
        this.quant_minimo = dados.quant_minimo();
        this.valor_custo = dados.valor_custo();
        this.valor_venda = dados.valor_venda();
        this.ativo = true;
    }

    public void atualizarInfo(DadosAtualizacaoProduto dados) {
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.quant_estoque() != null) {
            this.quant_estoque = dados.quant_estoque();
        }
        if(dados.quant_minimo() != null) {
            this.quant_minimo = dados.quant_minimo();
        }
        if(dados.valor_custo() != null) {
            this.valor_custo = dados.valor_custo();
        }
        if(dados.valor_venda() != null) {
            this.valor_venda = dados.valor_venda();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

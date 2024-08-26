package click.gestao.api.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull
        Long id,
        String descricao,
        Integer quant_estoque,
        Integer quant_minimo,
        Double valor_custo,
        Double valor_venda
) {
}

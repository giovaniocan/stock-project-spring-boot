package click.gestao.api.domain.produto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

// por causa do record, não é necessário criar os getters e setters ou ate mesmo o construtor
public record DadosCadastroProduto(
    // quando é uma string eu posso usar o not blank, mas quando não for eu uso o not null mesmo
        //verifica se não é nulo e nem vazio
        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @PositiveOrZero(message = "Quantidade em estoque deve ser maior ou igual a zero")
        Integer quant_estoque,

        @NotNull(message = "Quantidade mínima é obrigatória")
        @PositiveOrZero(message = "Quantidade mínima deve ser maior ou igual a zero")
        Integer quant_minimo,

        @NotNull(message = "Valor de custo é obrigatório")
        @PositiveOrZero(message = "Valor de custo deve ser maior ou igual a zero")
        Double valor_custo,

        @NotNull(message = "Valor de venda é obrigatório")
        @PositiveOrZero(message = "Valor de venda deve ser maior ou igual a zero")
        Double valor_venda
) {
}

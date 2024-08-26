package click.gestao.api.domain.produto;

public record DadosListagemProduto(Long id, String descricao, Integer quant_estoque, Integer quant_minimo, Double valor_custo, Double valor_venda ) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getDescricao(), produto.getQuant_estoque(), produto.getQuant_minimo(), produto.getValor_custo(), produto.getValor_venda());
    }
}

package click.gestao.api.domain.produto;

public record DadosDetalhamentoProduto(Long id, String descricao, Integer quant_estoque, Integer quant_minimo, Double valor_custo, Double valor_venda, boolean ativo) {

    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getId(), produto.getDescricao(), produto.getQuant_estoque(), produto.getQuant_minimo(), produto.getValor_custo(), produto.getValor_venda(), produto.getAtivo());

    }

}

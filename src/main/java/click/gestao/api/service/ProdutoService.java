package click.gestao.api.service;

import click.gestao.api.domain.Transactions.TypeTransaction;
import click.gestao.api.domain.ValidacaoException;
import click.gestao.api.domain.produto.*;
import click.gestao.api.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public DadosDetalhamentoProduto createProduct(DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        var newProduct = repository.save(new Produto(dados));

        var produtoDelhado = new DadosDetalhamentoProduto(newProduct);

        return produtoDelhado ;
    }

    public Page<DadosListagemProduto> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
    }

    // aqui em nenhum momento a gente chama o repository, pois a gente nao precisa chamar o repository no final, quando a gente ja muda as coisas ele reflete automaticamente no banco de dados, pois tem a tag @Transctional
    @Transactional
    public DadosDetalhamentoProduto atualizar(DadosAtualizacaoProduto dados) {
        var produto = repository.getReferenceById(dados.id());

        produto.atualizarInfo(dados);

        return new DadosDetalhamentoProduto(produto);
    }

    @Transactional
    public void deletar(Long id) {
        var produto = repository.getReferenceById(id);
        produto.excluir();
    }

    public DadosDetalhamentoProduto detailProduct(Long id) {
        var product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto com id " + id + " não encontrado"));


        return new DadosDetalhamentoProduto(product);
    }

    public void validateStock(TypeTransaction typeTransaction, Produto product, Integer amount) {
        if(typeTransaction == TypeTransaction.SAIDA && product.getQuant_estoque() < amount){
            throw new ValidacaoException("Quantidade em estoque insuficiente");
        }
    }

    public void makeTransctionInStock(Long product_id, Integer amount, TypeTransaction typeTransaction) {
        var product = repository.getReferenceById(product_id);

        if(typeTransaction == TypeTransaction.ENTRADA){
            product.atualizarEstoque (true, amount);
        } else {
            product.atualizarEstoque(false, amount);
        }
    }
}

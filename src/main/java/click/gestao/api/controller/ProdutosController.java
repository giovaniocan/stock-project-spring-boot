package click.gestao.api.controller;

import click.gestao.api.domain.produto.*;
import click.gestao.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoProduto> createProduto(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = produtoService.createProduct(dados);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    // esse @PageableDefault serve para paginar o que estamos buscando, e os parametros size e sort sao opcionais, mas se nao passar nada ele vai pegar o valor padrao
    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 25, sort = {"id"}) Pageable paginacao) {
        var page = produtoService.listar(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados){
        var medicoAtualizado = produtoService.atualizar(dados);

        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detailProduct(@PathVariable Long id) {
        var product = produtoService.detailProduct(id);

        return ResponseEntity.ok(product);
    }


}

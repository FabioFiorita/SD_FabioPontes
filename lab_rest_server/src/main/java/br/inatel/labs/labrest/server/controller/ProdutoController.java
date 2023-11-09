package br.inatel.labs.labrest.server.controller;

import br.inatel.labs.labrest.server.model.Produto;
import br.inatel.labs.labrest.server.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> getProdutos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Long produtoId) {
        return service.findById(produtoId).orElseThrow(() -> {
            String msg = String.format("Nenhum produto encontrado com id [%s]", produtoId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        });
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto p) {
        Produto produtoCriado = service.create(p);
        return produtoCriado;
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void putProduto(@RequestBody Produto p) {
        service.update(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable("id") Long produtoId) {
        Produto produtoEncontrado = getProdutoById(produtoId);
        service.remove(produtoEncontrado);
    }
}

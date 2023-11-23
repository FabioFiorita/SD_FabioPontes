package br.inatel.labs.labrest.server;

import br.inatel.labs.labrest.server.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabRestServerApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void dadoProdutoInexistente_quandoDeleteProduto_entaoRespondeComStatusNotFound() {
        Long produtoIdInexistente = 99L;

        webTestClient.delete()
                .uri("/produto/" + produtoIdInexistente)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void dadoProdutoExistente_quandoDeleteProduto_entaoRespondeComStatusNoContent() {
        Long produtoIdExistente = 2L;

        webTestClient.delete()
                .uri("/produto/" + produtoIdExistente)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    void dadoProdutoExistente_quandoPutProduto_entaoRespondeComStatusNoContent() {
        Produto produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setDescricao("Furadeira a bateria");
        produtoExistente.setPreco(new BigDecimal(800));

        webTestClient.put()
                .uri("/produto")
                .bodyValue(produtoExistente)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    void dadoProdutoValido_quandoPostProduto_entaoRespondeComProdutoCriadoEProdutoValido() {
        Produto novoProduto = new Produto();
        novoProduto.setDescricao("Tupia de mesa");
        novoProduto.setPreco(new BigDecimal(9000));

        Produto produtoCriado = webTestClient.post()
                .uri("/produto")
                .bodyValue(novoProduto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Produto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(produtoCriado);
        assertNotNull(produtoCriado.getId());
    }

    @Test
    void dataProdutoIdInvalido_quandoGetProdutoPeloId_entaoRespondeComStatusNotFound() {
        Long idInvalido = 99L;

        webTestClient.get()
                .uri("/produto/" + idInvalido)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void dadoProdutoIdValido_quandoGetProdutoPeloId_entaoRespondeComProdutoValido() {
        Long produtoIdValido = 1L;

        Produto produtoRespondido = webTestClient.get()
                .uri("/produto/" + produtoIdValido)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Produto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(produtoRespondido);
        assertEquals(produtoIdValido, produtoRespondido.getId());
    }

    @Test
    void deveListarProdutos() {
        webTestClient.get()
                .uri("/produto")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .returnResult();
    }

}

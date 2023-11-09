package br.inatel.labs.lab_rest_client;

import br.inatel.labs.lab_rest_client.model.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class WebClientGetMonoProdutoPeloId {

    public static void main(String[] args) {
        try {
            Mono<ProdutoDTO> monoProduto = WebClient.create(Constantes.BASE_URL)
                    .get()
                    .uri("/produto/4")
                    .retrieve()
                    .bodyToMono(ProdutoDTO.class);

            monoProduto.subscribe();

            ProdutoDTO produtoRetornado =  monoProduto.block();

            System.out.println("Produto");
            System.out.println(produtoRetornado);
        } catch (WebClientResponseException e) {
            System.out.println("Status code" + e.getStatusCode());
            System.out.println("Message" + e.getMessage());
        }

    }
}

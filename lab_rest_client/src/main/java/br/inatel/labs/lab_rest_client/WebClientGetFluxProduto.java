package br.inatel.labs.lab_rest_client;

import br.inatel.labs.lab_rest_client.model.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static br.inatel.labs.lab_rest_client.Constantes.BASE_URL;

public class WebClientGetFluxProduto {

    public static void main(String[] args) {
        List<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();

        Flux<ProdutoDTO> fluxProduto = WebClient.create(BASE_URL)
                .get()
                .uri("/produto")
                .retrieve()
                .bodyToFlux(ProdutoDTO.class);

        fluxProduto.subscribe(listaProduto::add);

        fluxProduto.blockLast();

        System.out.println("Lista de produtos");
        System.out.println(listaProduto);
    }
}

package br.inatel.labs.lab_rest_client;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientDeleteProdutoPeloId {
    public static void main(String[] args) {
        ResponseEntity<Void> responseEntity = WebClient.create(Constantes.BASE_URL)
                .delete()
                .uri("/produto/2")
                .retrieve()
                .toBodilessEntity()
                .block();

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        System.out.println("Produto removido");
        System.out.println("Status da resposta: " + statusCode);
    }
}

package br.inatel.labs.labjpa;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.service.NotaCompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class LoadingDemo {
    @Autowired
    private NotaCompraService service;

    @Test
    public void demoPlanejandoConsulta() {
        try {
            NotaCompra nota = service.buscarNotaCompraPeloIdComListaItem(1L);

            List<NotaCompraItem> listaNotaCompraItem = nota.getListaNotaCompraItem();
            listaNotaCompraItem.forEach(System.out::println);

            System.out.println("Número de itens da nota: " + listaNotaCompraItem.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void demoLazyLoading() {
        try {
            NotaCompra nota = service.buscarNotaCompraPeloId(1L);

            List<NotaCompraItem> listaNotaCompraItem = nota.getListaNotaCompraItem();
            int nroDeItens = listaNotaCompraItem.size();

            System.out.println("Número de itens da nota: " + nroDeItens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void demoEagerLoading() {
        try {
            NotaCompraItem item = service.buscarNotaCompraItemPeloId(1L);
            LocalDate dataEmissao = item.getNotaCompra().getDataEmissao();

            System.out.println("Data de emissão da nota: " + dataEmissao);

            System.out.println("Aconteceu o carregamento EAGER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

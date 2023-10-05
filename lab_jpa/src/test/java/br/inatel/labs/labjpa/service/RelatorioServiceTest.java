package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.dto.TotalCompradoPorFornecedorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RelatorioServiceTest {

    @Autowired
    private RelatorioService relatorioService;

    @Test
    void test() {
        List<TotalCompradoPorFornecedorDTO> listaDTO = relatorioService.pesquisarTotalCompradoPorFornecedor();

        assertFalse(listaDTO.isEmpty());

        System.out.println("FornecedorRazaoSocial : TotalComprada");
        listaDTO.forEach(System.out::println);
    }
}

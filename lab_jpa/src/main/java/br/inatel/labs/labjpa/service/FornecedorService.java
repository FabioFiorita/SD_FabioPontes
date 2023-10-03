package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.entity.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FornecedorService {
    @PersistenceContext
    private EntityManager em;

    public Fornecedor salvar(Fornecedor fornecedor) {
        em.persist(fornecedor);
        return fornecedor;
    }

    public Fornecedor buscarPorId(Long id) {
        return em.find(Fornecedor.class, id);
    }

    public List<Fornecedor> listar() {
        return em.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class).getResultList();
    }

    public void remover(Fornecedor f) {
        em.remove(em.merge(f));
    }
}

package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProdutoService {
    @PersistenceContext
    private EntityManager em;

    public Produto salvar(Produto produto) {
        return em.merge(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> listar() {
        return em.createQuery("select p from Produto p", Produto.class).getResultList();
    }

    public void remover(Produto p) {
        p = em.merge(p);
        em.remove(p);
    }
}

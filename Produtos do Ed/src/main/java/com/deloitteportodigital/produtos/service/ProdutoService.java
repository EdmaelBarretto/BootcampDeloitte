package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {

        Produto produtoExistente = repository.findById(id).orElseThrow();

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());

        return repository.save(produtoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
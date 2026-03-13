package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import com.deloitteportodigital.produtos.validation.ProdutoValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final List<ProdutoValidation> validations;

    public ProdutoService(ProdutoRepository repository, List<ProdutoValidation> validations) {
        this.repository = repository;
        this.validations = validations;
    }

    public Produto salvar(Produto produto){

        for(ProdutoValidation validation : validations){
            validation.validar(produto);
        }

        return repository.save(produto);
    }

    public List<Produto> listar(){
        return repository.findAll();
    }

    public Produto buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Produto atualizar(Long id, Produto produto){
        produto.setId(id);
        return repository.save(produto);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
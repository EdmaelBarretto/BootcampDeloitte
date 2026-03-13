package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import com.deloitteportodigital.produtos.validation.ProdutoValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void setup(){

        repository = Mockito.mock(ProdutoRepository.class);

        List<ProdutoValidation> validations = List.of();

        service = new ProdutoService(repository, validations);
    }

    @Test
    void deveSalvarProduto(){

        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setPreco(3000.0);
        produto.setEstoque(10);

        Mockito.when(repository.save(produto)).thenReturn(produto);

        Produto resultado = service.salvar(produto);

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
    }
}
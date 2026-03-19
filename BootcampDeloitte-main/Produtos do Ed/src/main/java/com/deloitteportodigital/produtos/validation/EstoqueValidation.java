package com.deloitteportodigital.produtos.validation;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class EstoqueValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {

        if(produto.getEstoque() < 0){
            throw new RuntimeException("O estoque não pode ser negativo");
        }

    }

}
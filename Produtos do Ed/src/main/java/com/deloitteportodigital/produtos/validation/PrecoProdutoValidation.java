package com.deloitteportodigital.produtos.validation;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class PrecoProdutoValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {

        if(produto.getPreco() <= 0){
            throw new RuntimeException("O preço deve ser maior que zero");
        }

    }

}
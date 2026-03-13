package com.deloitteportodigital.produtos.validation;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class NomeProdutoValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {

        if(produto.getNome() == null || produto.getNome().length() < 3){
            throw new RuntimeException("Nome do produto inválido");
        }

    }

}
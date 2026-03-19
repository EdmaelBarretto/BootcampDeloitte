package com.deloitteportodigital.produtos.email;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.stereotype.Service;

public Produto salvar(Produto produto){
    return repository.save(produto);
}

@Service
public class EmailService {

    public void enviarNotificacaoNovoProduto(Produto produto){

        System.out.println("Novo produto cadastrado: " + produto.getNome());

    }

}
package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j   // gera logger: log.info(), log.error(), etc.
@Service
public class EmailService {

    public void enviarNotificacaoNovoProduto(Produto produto) {
        // Simulação — aqui você integraria com SMTP, SendGrid, etc.
        log.info("📧 Notificação: novo produto cadastrado — ID: {}, Nome: {}, Preço: R$ {}",
                produto.getId(),
                produto.getNome(),
                produto.getPreco());
    }

    public void enviarNotificacaoEstoqueBaixo(Produto produto) {
        if (produto.getEstoque() <= 5) {
            log.warn("⚠️ Estoque baixo: produto '{}' com apenas {} unidade(s).",
                    produto.getNome(), produto.getEstoque());
        }
    }
}
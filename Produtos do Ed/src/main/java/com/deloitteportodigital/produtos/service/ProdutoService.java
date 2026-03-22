package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import com.deloitteportodigital.produtos.service.EmailService;
import com.deloitteportodigital.produtos.validation.ProdutoValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final List<ProdutoValidation> validations;
    private final EmailService emailService;

    public ProdutoService(ProdutoRepository repository,
                          List<ProdutoValidation> validations,
                          EmailService emailService) {
        this.repository = repository;
        this.validations = validations;
        this.emailService = emailService;
    }

    // ✅ CREATE / UPDATE
    public Produto salvar(Produto produto) {
        validations.forEach(v -> v.validar(produto));
        Produto salvo = repository.save(produto);
        emailService.enviarNotificacaoNovoProduto(salvo); // notificação
        return salvo;
    }

    // ✅ READ - listar todos
    public List<Produto> listar() {
        return repository.findAll();
    }

    // ✅ READ - buscar por ID
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ DELETE
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
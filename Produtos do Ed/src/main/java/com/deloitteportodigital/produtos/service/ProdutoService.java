package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import com.deloitteportodigital.produtos.validation.ProdutoValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor   // gera construtor com todos os campos final (substitui @Autowired)
public class ProdutoService {

    private final ProdutoRepository repository;
    private final List<ProdutoValidation> validations;
    private final EmailService emailService;

    // ── CREATE ────────────────────────────────────────────────────
    public Produto salvar(Produto produto) {
        log.info("Salvando produto: {}", produto.getNome());

        validations.forEach(v -> v.validar(produto));

        Produto salvo = repository.save(produto);

        emailService.enviarNotificacaoNovoProduto(salvo);
        emailService.enviarNotificacaoEstoqueBaixo(salvo);

        return salvo;
    }

    // ── READ — todos ──────────────────────────────────────────────
    public List<Produto> listar() {
        log.info("Listando todos os produtos");
        return repository.findAll();
    }

    // ── READ — por ID ─────────────────────────────────────────────
    public Produto buscarPorId(Long id) {
        log.info("Buscando produto ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + id));
    }

    // ── READ — busca por nome ─────────────────────────────────────
    public List<Produto> buscarPorNome(String nome) {
        log.info("Buscando produtos com nome contendo: {}", nome);
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    // ── UPDATE ────────────────────────────────────────────────────
    public Produto atualizar(Long id, Produto produtoNovo) {
        log.info("Atualizando produto ID: {}", id);

        // Garante que o produto existe antes de atualizar
        Produto existente = buscarPorId(id);

        existente.setNome(produtoNovo.getNome());
        existente.setPreco(produtoNovo.getPreco());
        existente.setEstoque(produtoNovo.getEstoque());

        validations.forEach(v -> v.validar(existente));

        return repository.save(existente);
    }

    // ── DELETE ────────────────────────────────────────────────────
    public void deletar(Long id) {
        log.info("Deletando produto ID: {}", id);

        // Lança exceção se não existir — evita deletar ID inválido silenciosamente
        buscarPorId(id);

        repository.deleteById(id);
    }
}
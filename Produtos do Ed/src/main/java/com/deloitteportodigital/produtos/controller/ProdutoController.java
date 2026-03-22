package com.deloitteportodigital.produtos.controller;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    // ── POST /produtos ────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        log.info("POST /produtos — {}", produto.getNome());
        Produto criado = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // ── GET /produtos ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // ── GET /produtos/{id} ────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // ── GET /produtos/buscar?nome=teclado ─────────────────────────
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    // ── PUT /produtos/{id} ────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return ResponseEntity.ok(service.atualizar(id, produto));
    }

    // ── DELETE /produtos/{id} ─────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}
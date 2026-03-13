package com.deloitteportodigital.produtos.controller;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Produto produto;

    @BeforeEach
    void setup() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setPreco(3000.0);
        produto.setEstoque(10);
    }

    @Test
    void deveCriarProduto() throws Exception {
        Mockito.when(produtoService.salvar(produto)).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook"))
                .andExpect(jsonPath("$.preco").value(3000.0));
    }

    @Test
    void deveListarProdutos() throws Exception {
        Mockito.when(produtoService.listar()).thenReturn(List.of(produto));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("Notebook"));
    }

    @Test
    void deveAtualizarProduto() throws Exception {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1L);
        produtoAtualizado.setNome("Notebook Gamer");
        produtoAtualizado.setPreco(4500.0);
        produtoAtualizado.setEstoque(5);

        Mockito.when(produtoService.atualizar(1L, produtoAtualizado)).thenReturn(produtoAtualizado);

        mockMvc.perform(put("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook Gamer"))
                .andExpect(jsonPath("$.preco").value(4500.0));
    }

    @Test
    void deveDeletarProduto() throws Exception {
        Mockito.doNothing().when(produtoService).deletar(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isOk());
    }
}
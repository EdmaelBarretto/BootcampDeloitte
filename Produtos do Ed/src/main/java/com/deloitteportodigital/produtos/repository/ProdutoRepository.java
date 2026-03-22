package com.deloitteportodigital.produtos.repository;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca produtos pelo nome (ignorando maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Busca produtos com estoque acima de um valor
    List<Produto> findByEstoqueGreaterThan(Integer estoque);
}
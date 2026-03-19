package com.deloitteportodigital.produtos.repository;

import com.deloitteportodigital.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
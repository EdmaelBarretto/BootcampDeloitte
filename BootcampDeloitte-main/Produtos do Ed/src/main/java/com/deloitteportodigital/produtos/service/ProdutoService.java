package com.deloitteportodigital.produtos.service;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

```
private final ProdutoRepository repository;

public ProdutoService(ProdutoRepository repository) {
    this.repository = repository;
}

public Produto salvar(Produto produto){
    return repository.save(produto);
}

public List<Produto> listar(){
    return repository.findAll();
}

public Produto buscarPorId(Long id){
    return repository.findById(id).orElse(null);
}

public Produto atualizar(Long id, Produto produto){
    produto.setId(id);
    return repository.save(produto);
}

public void deletar(Long id){
    repository.deleteById(id);
}
```

}

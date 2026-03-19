package com.deloitteportodigital.produtos.controller;

import com.deloitteportodigital.produtos.model.Produto;
import com.deloitteportodigital.produtos.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*") 
public class ProdutoController {

```
private final ProdutoService service;

public ProdutoController(ProdutoService service) {
    this.service = service;
}

// CREATE
@PostMapping
public Produto criar(@RequestBody Produto produto){
    return service.salvar(produto);
}

// READ (LISTAR TODOS)
@GetMapping
public List<Produto> listar(){
    return service.listar();
}

// READ (BUSCAR POR ID)
@GetMapping("/{id}")
public Produto buscarPorId(@PathVariable Long id){
    Produto produto = service.buscarPorId(id);

    if (produto == null) {
        throw new RuntimeException("Produto não encontrado");
    }

    return produto;

}

// UPDATE
@PutMapping("/{id}")
public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
    produto.setId(id);
    return service.salvar(produto);
}

// DELETE
@DeleteMapping("/{id}")
public void deletar(@PathVariable Long id){
    service.deletar(id);
}
```

}

package com.malbano.redistest.controller;

import com.malbano.redistest.entity.Produto;
import com.malbano.redistest.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Procurar produto por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") Long id) {
        Produto produto = produtoService.procurarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Listar Produto")
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produto = produtoService.listarProdutos();
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Cadastra um Produto")
    @PostMapping
    public ResponseEntity insert(@RequestBody Produto request) {
        produtoService.inserirProduto(request);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Editar um Produto")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody Produto request) {
        produtoService.editarProduto(request, id);
        return ResponseEntity.accepted().build();
    }
}

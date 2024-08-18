package com.malbano.redistest.service;

import com.malbano.redistest.entity.Produto;
import com.malbano.redistest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void inserirProduto(Produto produto){
        produtoRepository.saveAndFlush(produto);
    }
}

package com.malbano.redistest.service;

import com.malbano.redistest.entity.Produto;
import com.malbano.redistest.exception.NotFoundException;
import com.malbano.redistest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void inserirProduto(Produto request){
        produtoRepository.saveAndFlush(request);
    }

    public void editarProduto(Produto request, Long produtoId){
        procurarPorId(produtoId);
        produtoRepository.saveAndFlush(request);
    }

    public void deletarProduto(Long produtoId){
        Produto produto = procurarPorId(produtoId);
        produtoRepository.delete(produto);
    }

    public Produto procurarPorId(Long produtoId){
        return produtoRepository.findById(produtoId).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    public List<Produto> listarProduto(){
        return produtoRepository.findAll();
    }
}

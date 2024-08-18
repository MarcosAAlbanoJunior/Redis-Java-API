package com.malbano.redistest.service;

import com.malbano.redistest.entity.Produto;
import com.malbano.redistest.exception.NotFoundException;
import com.malbano.redistest.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @CacheEvict(value = "produto", key = "#produtoId")
    public void inserirProduto(Produto request){
        produtoRepository.saveAndFlush(request);
        logger.info("Produto inserido e cache invalidado para ID: {}", request.getProdutoId());
    }

    @CacheEvict(value = "produto", key = "#produtoId")
    public void editarProduto(Produto request, Long produtoId){
        procurarPorId(produtoId);
        produtoRepository.saveAndFlush(request);
        logger.info("Produto editado e cache invalidado para ID: {}", produtoId);
    }

    @CacheEvict(value = "produto", key = "#produtoId")
    public void deletarProduto(Long produtoId){
        Produto produto = procurarPorId(produtoId);
        produtoRepository.delete(produto);
        logger.info("Produto deletado e cache invalidado para ID: {}", produtoId);
    }

    @Cacheable(value = "produto", key = "#produtoId")
    public Produto procurarPorId(Long produtoId){
        logger.info("Buscando produto pelo ID: {}. (Se este log aparecer, o dado não estava no cache)", produtoId);
        return produtoRepository.findById(produtoId).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    @Cacheable(value = "produtoLista")
    public List<Produto> listarProdutos(){
        logger.info("Buscando lista de produtos. (Se este log aparecer, os dados não estavam no cache)");
        return produtoRepository.findAll();
    }
}

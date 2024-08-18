package com.malbano.redistest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue()
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
}

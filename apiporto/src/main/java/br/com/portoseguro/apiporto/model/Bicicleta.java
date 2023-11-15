package br.com.portoseguro.apiporto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Bicicleta {

    private Long id;
    private String marca;
    private String modelo;
    private BigDecimal valor;
    private String cor;

    public Bicicleta() {
        
    }

    public Bicicleta(Long id, String marca, String modelo, BigDecimal valor, String cor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
        this.cor = cor;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @JsonProperty("modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @JsonProperty("valor")
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @JsonProperty("cor")
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}



package com.sirmarcodevs.candymachinemanager;

public class Pedido {

    public String nome;
    public String cpf;
    public String endereco;
    public String complemento;
    public String email;
    public String telefone;
    public String numero_pedido;
    public String produto;
    public String quantidade;
    public String valor;
    public String cep;

    public Pedido() {
    }

    public Pedido(String nome, String cpf, String endereco, String complemento, String email, String telefone, String numero_pedido, String produto, String quantidade, String valor, String cep) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.complemento = complemento;
        this.email = email;
        this.telefone = telefone;
        this.numero_pedido = numero_pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNumeropedido() {
        return numero_pedido;
    }

    public void setNumeropedido(String numeropedido) {
        this.numero_pedido = numeropedido;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

package br.senac.sc.financeiroapi.model;

public class Pessoa {

    private Long id;

    private String nome;

    private Boolean ativo;

    private Endereco endereco;

    public Pessoa() {
    }
    

    public Pessoa(Long id, String nome, Boolean ativo, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}

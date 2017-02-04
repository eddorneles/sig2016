package model;

import java.util.Date;

public class Representante {
    private int codigo;
    
    private String nome;
    private int cpf;
    private String rg;
    private Date nascimento;
    private int codPatrocinador;

    private Endereco endereco;
    
    public int getCodigo(){
        return this.codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public int getCodPatrocinador() {
        return codPatrocinador;
    }

    public void setCodPatrocinador(int codPatrocinador) {
        this.codPatrocinador = codPatrocinador;
    }
    
}

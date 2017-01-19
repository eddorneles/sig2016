package model;

public class Bairro {
    private String nome;
    private String codigo;
    
    
    public String getNome(){
        return this.nome;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public void setNome( String nome ){
        this.nome = nome;
    }
    
    public void setCodigo( String codigo ){
        this.codigo = codigo;
    }
}

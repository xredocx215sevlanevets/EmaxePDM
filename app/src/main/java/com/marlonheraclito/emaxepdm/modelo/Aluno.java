package com.marlonheraclito.emaxepdm.modelo;

public class Aluno {

    private String id;
    private String nome;
    private String numero;
    private String pc;
    private String presencia;

    public Aluno(String id, String nome, String numero, String pc, String presencia) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.pc = pc;
        this.presencia = presencia;
    }

    public Aluno(String nome, String numero, String pc, String presencia) {
        this.nome = nome;
        this.numero = numero;
        this.pc = pc;
        this.presencia = presencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPresencia() {
        return presencia;
    }

    public void setPresencia(String presencia) {
        this.presencia = presencia;
    }
}

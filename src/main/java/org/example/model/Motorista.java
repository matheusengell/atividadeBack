package org.example.model;

public class Motorista {

    private int idMotorista;
    private String nomeMotorista;
    private String cnhMotorista;
    private String veiculoMotorista;
    private String cidadeBase;


    public Motorista(String nomeMotorista, String cnhMotorista, String veiculoMotorista, String cidadeBase) {
        this.idMotorista = idMotorista;
        this.nomeMotorista = nomeMotorista;
        this.cnhMotorista = cnhMotorista;
        this.veiculoMotorista = veiculoMotorista;
        this.cidadeBase = cidadeBase;
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getCnhMotorista() {
        return cnhMotorista;
    }

    public void setCnhMotorista(String cnhMotorista) {
        this.cnhMotorista = cnhMotorista;
    }

    public String getVeiculoMotorista() {
        return veiculoMotorista;
    }

    public void setVeiculoMotorista(String veiculoMotorista) {
        this.veiculoMotorista = veiculoMotorista;
    }

    public String getCidadeBase() {
        return cidadeBase;
    }

    public void setCidadeBase(String cidadeBase) {
        this.cidadeBase = cidadeBase;
    }
}

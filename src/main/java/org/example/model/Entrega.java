package org.example.model;

import org.example.model.enums.StatusEntrega;

public class Entrega {

    private int idEntrega;
    private int idPedido;
    private int idMotorista;
    private String dataSaida;
    private String dataEntrega;
    private String status;

    public Entrega(int idEntrega, int idPedido, int idMotorista, String dataSaida, String dataEntrega, String status) {
        this.idEntrega = idEntrega;
        this.idPedido = idPedido;
        this.idMotorista = idMotorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public Entrega(int idPedido, int idMotorista, String dataSaida, String dataEntrega, String status) {
        this.idPedido = idPedido;
        this.idMotorista = idMotorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public Entrega(int idEntrega, String status) {
        this.idEntrega = idEntrega;
        this.status = status;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package org.example.model;

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


}

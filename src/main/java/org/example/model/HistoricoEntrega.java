package org.example.model;

public class HistoricoEntrega {

    private int idHistorico;
    private int idEntrega;
    private String dataEvento;
    private String descricao;

    public HistoricoEntrega(int idHistorico, int idEntrega, String dataEvento, String descricao) {
        this.idHistorico = idHistorico;
        this.idEntrega = idEntrega;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

}

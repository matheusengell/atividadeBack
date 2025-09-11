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

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

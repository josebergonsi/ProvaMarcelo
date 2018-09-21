/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_josebergonsi.model;

import java.sql.Date;



/**
 *
 * @author joseb
 */
public class Destino {

    private int codigo_destino;
    private String descricao_destino;
    private Date data_ini;
    private Date data_fim;
    private float vltotal_dest;

    public int getCodigo_destino() {
        return codigo_destino;
    }

    public void setCodigo_destino(int codigo_destino) {
        this.codigo_destino = codigo_destino;
    }

    public String getDescricao_destino() {
        return descricao_destino;
    }

    public void setDescricao_destino(String descricao_destino) {
        this.descricao_destino = descricao_destino;
    }

    public Date getData_ini() {
        return data_ini;
    }

    public void setData_ini(Date data_ini) {
        this.data_ini = data_ini;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public float getVltotal_dest() {
        return vltotal_dest;
    }

    public void setVltotal_dest(float vltotal_dest) {
        this.vltotal_dest = vltotal_dest;
    }
    


    @Override
    public String toString() {
        return "Destino{" + "codigo_destino=" + codigo_destino + ", descricao_destino="
                + descricao_destino + ", data_ini=" + data_ini + ", data_fim=" + data_fim
                + ", vltotal_dest=" + vltotal_dest + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_josebergonsi.model;

/**
 *
 * @author joseb
 */
public class Custo {

    private int codigo;
    private String descricao;
    private int tipoCusto;
    private float valorCusto;

    public Custo(int codigo, String descricao, int tipoCusto, float valorCusto) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoCusto = tipoCusto;
        this.valorCusto = valorCusto;
    }

    public Custo() {
       
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipoCusto() {
        return tipoCusto;
    }

    public void setTipoCusto(int tipoCusto) {
        this.tipoCusto = tipoCusto;
    }

    public float getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(float valorCusto) {
        this.valorCusto = valorCusto;
    }

    @Override
    public String toString() {
        return "Custo{" + "codigo=" + codigo + ", descricao=" + descricao
                + ", tipoCusto=" + tipoCusto + ", valorCusto=" + valorCusto + '}';
    }

}

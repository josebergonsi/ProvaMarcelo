/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_josebergonsi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import prova_josebergonsi.jdbc.ConnectionFactory;
import prova_josebergonsi.model.Custo;

public class CustoDAO implements Interface<Custo> {

    private Connection connection = null;

    @Override
    public void save(Custo entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into custo(cd_custo, ds_custo, tp_custo,vl_custo)")
                    .append(" values (?,?,?,?);");

            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo());
            pstm.setString(2, entity.getDescricao());
            pstm.setInt(3, entity.getTipoCusto());
            pstm.setFloat(4, entity.getValorCusto());

            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir Custo.");
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(Custo entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update custo set ds_custo = ?, ")
                    .append("tp_custo = ?, vl_custo= ?, ")
                    .append("where cd_custo = ?");

            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, entity.getDescricao());
            pstm.setInt(2, entity.getTipoCusto());
            pstm.setFloat(3, entity.getValorCusto());
            pstm.setInt(4, entity.getCodigo());

            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar Custo");
            ex.printStackTrace();
        } finally {
            connection.close();
        }

    }

    @Override
    public void delete(int id) throws SQLException {
     try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "delete from custo where cd_custo = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar Custo");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        }

    @Override
    public Custo getById(int id) throws SQLException {
        Custo custo = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from custo where cd_custo = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet ct = pstm.executeQuery();
           custo = new Custo();
            while (ct.next()) {
                custo.setCodigo(ct.getInt("cd_custo"));
                custo.setDescricao(ct.getString("ds_custo"));
                custo.setTipoCusto(ct.getInt("tp_custo"));
                custo.setValorCusto(ct.getFloat("vl_custo"));
              
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar por  ID");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return custo;
    }
    

    @Override
    public List<Custo> getByName(String name) throws SQLException {
            Custo custo = null;
        List<Custo> custoList = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from custo where upper(ds_custo) like upper('%" + name + "%')";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            custoList = new ArrayList<>();
            while (rs.next()) {
                custo = new Custo();
                custo.setCodigo(rs.getInt("cd_custo"));
                custo.setDescricao(rs.getString("ds_custo"));
                custo.setTipoCusto(rs.getInt("tp_custo"));
                custo.setValorCusto(rs.getFloat("vl_custo"));
              
                custoList.add(custo);
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar por nome");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return custoList;
    
    }

    @Override
    public List<Custo> getAll() throws SQLException {
    List<Custo> custoList = null;
        Custo custo = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from custo order by cd_custo";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
           custoList = new ArrayList<>();
            while (rs.next()) {
                custo = new Custo();
                custo.setCodigo(rs.getInt("cd_custo"));
                custo.setDescricao(rs.getString("ds_custo"));
               custo.setTipoCusto(rs.getInt("tp_custo"));
                custo.setValorCusto(rs.getFloat("vl_custo"));
                custoList.add(custo);
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todos os custos");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return custoList;   
    }


    @Override
    public int getLastId() throws SQLException {
         PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select coalesce(max(cd_custo),0)+1 as maior from custo";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao mostrar  maior ID Custo");
            ex.printStackTrace();
        } finally {
            pstm.close();
            this.connection.close();
        }
        return 1;
    }
    }



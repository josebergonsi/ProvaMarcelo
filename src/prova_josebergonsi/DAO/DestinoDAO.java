/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_josebergonsi.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prova_josebergonsi.jdbc.ConnectionFactory;
import prova_josebergonsi.model.Custo;

import prova_josebergonsi.model.Destino;

public class DestinoDAO implements Interface<Destino> {

    private Connection connection = null;

    @Override
    public void save(Destino entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into destino(cd_destino, ds_destino, dt_inicio,dt_termino,vl_total)")
                    .append(" values (?,?,?,?,?);");

            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo_destino());
            pstm.setString(2, entity.getDescricao_destino());
            pstm.setDate(3,  entity.getData_ini());
            pstm.setDate(4, entity.getData_fim());
            pstm.setFloat(5, entity.getVltotal_dest());

            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir Destino.");
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(Destino entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update destino set ds_destino = ?, ")
                    .append("dt_inicio = ?, dt_termino= ?,vl_total = ? ")
                    .append("where cd_destino = ?");

            PreparedStatement pstm = connection.prepareStatement(sql.toString());
            pstm.setInt(1, entity.getCodigo_destino());
            pstm.setString(2, entity.getDescricao_destino());
            pstm.setDate(3, entity.getData_ini());
            pstm.setDate(4, entity.getData_fim());
            pstm.setFloat(5, entity.getVltotal_dest());
            

            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar Destino");
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "delete from destino where cd_destino = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar Destino");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
    }

    @Override
    public Destino getById(int id) throws SQLException {
        Destino destino = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from destino where cd_destino = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet ct = pstm.executeQuery();
            destino = new Destino();
            while (ct.next()) {
                destino.setCodigo_destino(ct.getInt("cd_destino"));
                destino.setDescricao_destino(ct.getString("ds_destino"));
                destino.setData_ini(ct.getDate("dt_inicio"));
                destino.setData_fim(ct.getDate("dt_termino"));
                destino.setVltotal_dest(ct.getFloat("vl_total"));

            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar por  ID");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return destino;
    }

    @Override
    public List<Destino> getByName(String name) throws SQLException {
        Destino destino = null;
        List<Destino> destinoList = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from destino where upper(ds_destino) like upper('%" + name + "%')";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            destinoList = new ArrayList<>();
            while (rs.next()) {
                destino = new Destino();
                destino.setCodigo_destino(rs.getInt("cd_destino"));
                destino.setDescricao_destino(rs.getString("ds_destino"));
                destino.setData_ini(rs.getDate("dt_inicio"));
                destino.setData_fim(rs.getDate("dt_termino"));
                destino.setVltotal_dest(rs.getFloat("vl_total"));

                destinoList.add(destino);
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar por Destino");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return destinoList;

    }

    @Override
    public List<Destino> getAll() throws SQLException {
        List<Destino> destinoList = null;
        Destino destino = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select * from destino order by cd_destino";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            destinoList = new ArrayList<>();
            while (rs.next()) {
                destino = new Destino();
                destino.setCodigo_destino(rs.getInt("cd_destino"));
                destino.setDescricao_destino(rs.getString("ds_destino"));
                destino.setData_ini(rs.getDate("dt_inicio"));
                destino.setData_fim(rs.getDate("dt_termino"));
                destino.setVltotal_dest(rs.getFloat("vl_total"));
                destinoList.add(destino);
            }
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todos os Destinos");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return destinoList;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "select coalesce(max(cd_destino),0)+1 as maior from destino";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao mostrar  maior ID destino");
            ex.printStackTrace();
        } finally {
            pstm.close();
            this.connection.close();
        }
        return 1;
    }

}

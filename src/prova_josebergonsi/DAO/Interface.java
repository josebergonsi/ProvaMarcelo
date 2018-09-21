/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_josebergonsi.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author joseb
 */
public interface Interface <E>{
 public void save(E entity) throws SQLException;
    public void update(E entity) throws SQLException;
    public void delete(int id) throws SQLException;   
      public E getById(int id) throws SQLException;
    public List<E> getByName(String name) throws SQLException;
    public List<E> getAll() throws SQLException;
    public int getLastId() throws SQLException;
}

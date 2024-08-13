/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */

    public interface DAOInterface<T> {
	
	public List<T> selectAll()  throws SQLException, ClassNotFoundException;
	
	public T selectById(T t)  throws SQLException, ClassNotFoundException;
	
	
	
	
	public int delete(T t)  throws SQLException, ClassNotFoundException;
	
	public int deleteAll(List<T> arr)  throws SQLException, ClassNotFoundException;
	
	public int update(T t)  throws SQLException, ClassNotFoundException;
}


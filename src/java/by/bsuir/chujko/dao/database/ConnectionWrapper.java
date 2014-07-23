/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Artyom
 */
public final class ConnectionWrapper {
    
	private final Connection connection;

	public ConnectionWrapper(Connection connection) {
		this.connection = connection;
	}
	
	public PreparedStatement prepareStatement(String statement) throws SQLException {
		return connection.prepareStatement(statement);		
	}	
	
	public PreparedStatement prepareStatement(String statement, String[] columnNames) throws SQLException{
		return connection.prepareStatement(statement, columnNames);
	}
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}
	
        public void setAutoCommit(boolean com) throws SQLException {
		connection.setAutoCommit(com);
	}
        
	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}
	
	public void commit() throws SQLException {
		connection.commit();
	}
	
	public void close() throws SQLException {
		connection.close(); 
	}
	public Statement createStatement() throws SQLException{
		return connection.createStatement();
	}
}

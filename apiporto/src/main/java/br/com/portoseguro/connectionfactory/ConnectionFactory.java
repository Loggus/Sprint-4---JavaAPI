package br.com.portoseguro.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	final static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    final static String USER = "RM99778";
    final static String PASS = "070200";

    private static Connection conexao;

    public static Connection createConnection() throws SQLException, ClassNotFoundException{
    	try {
    		if (conexao == null){
            	Class.forName("oracle.jdbc.driver.OracleDriver");
                conexao = DriverManager.getConnection(URL, USER, PASS);
            } 
            
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	catch (SQLException e) {
			e.printStackTrace();
    	
    	
    }
		return conexao;
    
    }
}

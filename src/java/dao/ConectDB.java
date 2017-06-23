/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConectDB {
    
    public Connection conn;
    
    public ConectDB() {
        conn            = null;
        String url      = "jdbc:postgresql://localhost/"; // localizacao do servidor
        String dbName   = "dbweb";                    // nome do banco de dados
        String driver   = "org.postgresql.Driver";   // nome do driver de conexao
        String userName = "postgres";                    // nome do usuario do banco
        String password = "123456";                    // respectiva senha
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                     null,
                    "Erro no Banco de Dados!\n\nContate seu Administrador do Sistema!",
                    "Erro...",
                    JOptionPane.WARNING_MESSAGE
            );
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
      return conn;
    }
    
    //Fecha a conecção com bco de dados
    public void closeConnection() {
        try                { 
        conn.close(); 
        }
        catch(Exception e) { 
        e.printStackTrace(); 
        }
    } 

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

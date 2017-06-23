/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author regis
 */
public class UsuarioDAO {

    private static Connection conn;
    private static ConectDB db;

    private final PreparedStatement selectUserByLogin;
    private final PreparedStatement insertUser;

    public UsuarioDAO() throws SQLException {

        db = new ConectDB();
        conn = db.getConnection();

        selectUserByLogin = conn.prepareStatement("SELECT * FROM Usuario WHERE login = ?");
        insertUser = conn.prepareStatement("INSERT INTO Usuario (login,senha,email) VALUES (?,?,?)");

    }
    
    public Usuario getUser(String login) throws SQLException {

        ResultSet resultSet;
        Usuario user = null;
        boolean result;

        selectUserByLogin.setString(1, login);
        resultSet = selectUserByLogin.executeQuery();

        result = resultSet.next();
        if (result) {
            user = new Usuario();
            user.setNome(resultSet.getString("login"));
            user.setSenha(resultSet.getString("senha"));
        }

        return user;

    }
    
    public int addUser(Usuario user) throws SQLException{
        
        int results;
        
        insertUser.setString(1, user.getLogin());
        insertUser.setString(2, user.getSenha());
        insertUser.setString(3, user.getEmail());
        
        results = insertUser.executeUpdate();   
        
        return results;
        
         }

}

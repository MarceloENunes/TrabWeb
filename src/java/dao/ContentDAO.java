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
import java.util.ArrayList;
import modelo.Conteudo;
import modelo.Usuario;

/**
 *
 * @author Marcelo
 */
public class ContentDAO {
     private static Connection conn;
    private static ConectDB db;

    private final PreparedStatement selectContent;
    private final PreparedStatement insertContent;

    public ContentDAO() throws SQLException {

        db = new ConectDB();
        conn = db.getConnection();

        selectContent = conn.prepareStatement("SELECT * FROM Conteudo WHERE login = ?");
        insertContent = conn.prepareStatement("INSERT INTO Conteudo (login,texto) VALUES (?,?)");

    }
    
    public ArrayList getContent(String login) throws SQLException {

        ResultSet resultSet;
        Conteudo conteudo = null;
        ArrayList<Conteudo> cont = new ArrayList<>();
        boolean result;
        
        selectContent.setString(1, login);

        resultSet = selectContent.executeQuery();

        result = resultSet.next();
        while (result) {
            conteudo = new Conteudo();
            conteudo.setLogin(resultSet.getString("login"));
            conteudo.setTexto(resultSet.getString("texto"));
            cont.add(conteudo);
            result = resultSet.next();
        }

        return cont;

    }
    
    public int addContent(Conteudo conteudo) throws SQLException{
        
        int results;
        
        insertContent.setString(1, conteudo.getLogin());
        insertContent.setString(2, conteudo.getTexto());
        
        results = insertContent.executeUpdate();   
        
        return results;
        
         }
}

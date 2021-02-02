/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatire;

import Library.ClientObj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eliasc
 */
public class Dao {
    private Connection connection;
    private Statement statement;
    
    public void openDB() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientserverdb", "admin","admin");
        statement = connection.createStatement();
    }
    public void closeDB() throws SQLException{
        statement.close();
        connection.close();
    }
    public int add(ClientObj client) throws SQLException{
        String sql = "insert into tblstudent(clientno, name, surname, email, password) values('"+client.getClientno()+"','"+client.getName()+"','"+client.getSurname()+"','"+client.getEmail()+"','"+client.getPassword()+"')";        
        return statement.executeUpdate(sql);       
    }
    public int update(ClientObj client) throws SQLException{
        String sql = "update tblstudent set name='"+client.getName()+"', surname='"+client.getSurname()+"', email='"+client.getEmail()+"', password='"+client.getPassword()+"' where clientno="+client.getClientno();
        return statement.executeUpdate(sql); 
    }
    public int delete(int clientno) throws SQLException{
        String sql = "delete from tblstudent where clientno="+clientno;       
        return statement.executeUpdate(sql);
    }
    public ClientObj search(int clientno) throws SQLException{
        ClientObj theClient = null;        
        String sql = "select name,surname,email from tblstudent where clientno="+clientno;
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){ 
            theClient = new ClientObj();
            theClient.setName(rs.getString(1));
            theClient.setSurname(rs.getString(2));
            theClient.setEmail(rs.getString(3));
        }        
        return theClient;
    }
    public ClientObj login(ClientObj client) throws SQLException{
        ClientObj theClient = null;        
        String sql = "select name, surname, email from tblstudent where email='"+client.getEmail()+"' AND password='"+client.getPassword()+"'";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){ 
            theClient = new ClientObj();
            theClient.setName(rs.getString(1));
            theClient.setSurname(rs.getString(2));
            theClient.setEmail(rs.getString(3));
        }        
        return theClient;
    }
    
}

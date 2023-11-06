
package soen.online_store.java.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Korjon Chang-Jones
 */
public class DataManager {
    
    public static void main(String[] args){
        
        try{
        Connection conn = DatabaseConnection.getConnection();
        System.out.println("Connection complete");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

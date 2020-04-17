package Dados3;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database implements DateUtils {
    static public final String SERVER = "jdbc:mysql://localhost:3306/SGEDb";
    static public final String DRIVER = "com.mysql.jdbc.Driver";
    static public final String USER = "root";
    static public final String PASSWORD = "1992";
    
    public java.sql.Connection conexao;
    public boolean isClosed;
    
    public Database() {
        try{
            this.conexao=null;
            this.isClosed = false;
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(SERVER,USER,PASSWORD);
        }
        catch (ClassNotFoundException | SQLException ex) {
            this.isClosed = true;
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() {
        try {
            this.conexao.close();
            this.isClosed = true;
        } catch (SQLException ex) {
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isClosed() {
        return this.isClosed;
    } 
    
}
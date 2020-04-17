package Dados3;

import Negocio3.Hondt;
import Negocio3.Voto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * chave do voto => int (em java (2^31-1)) se houver 10 milhoes de votos por cada eleicao, entao
 * a chave int chega para ~215 eleicoes
        private int idVoto;
	private int idEleicao;
	private int idLista;
	private Nominal nominal;
	private Lista lista;
 */

public class VotoDAO extends VotoConstants implements Map<Integer,Voto>, Hondt{
    /*private java.sql.Connection conexao;
    private boolean isClosed;
    
    public VotoDAO() {
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
*/
// metodo default para criar collections
    static private Collection makeObjectsFromResultSet(final ResultSet rs) throws java.sql.SQLException {
        Collection result = new java.util.ArrayList();
        while (rs.next()){
            int id = rs.getInt(VOTOS_ID);
            int idEleicao = rs.getInt(VOTOS_ELEICAO);
            int idLista = rs.getInt(VOTOS_LISTAS);
            Voto aux = new Voto(id,idEleicao,idLista);
            result.add(aux);
        }
        return result;
    }
/*    
// fecha conexao
    public void close() {
        try {
            this.conexao.close();
            this.isClosed = true;
        } catch (SQLException ex) {
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
// conexao encerrada?
    public boolean isClosed() {
        return this.isClosed;
    }  
*/
// implements => Map<k,v>
    @Override
    public int size() {
	
        int result = 0;
        ResultSet rs = null;
	PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("select distinct(count(*)) from ");
            sbSelect.append(VOTOS_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(1);
		}
		catch (SQLException ex){
                    Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally{
                    try {
                        if (pstm != null) pstm.close();
                        if (rs != null)rs.close();
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
		return result;
	}

    @Override
    public boolean isEmpty() {
        return (this.size()==0);
    }

    @Override
    public boolean containsKey(Object key) {
        // select count(*) from Votos where idVotos = ?;
        boolean res;
        if (key==null) res=false;
        else{
            int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;
        
            StringBuilder sbSelect = new StringBuilder();
		
            try{
                sbSelect.append("select count(*) from ");
                sbSelect.append(VOTOS_TABELA);
                sbSelect.append(" WHERE ");
                sbSelect.append(VOTOS_ID);
                sbSelect.append(" = ?");
            
             pstm = conexao.prepareStatement(sbSelect.toString());
            
                pstm.setInt(1, (int) key);
            
              rs = pstm.executeQuery();
                if(rs.next()) result = rs.getInt(1);
            }
            catch (SQLException ex){
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            res=(result==1);
        }
        return res;
    }
    
    @Override
    public boolean containsValue(Object value) {
        boolean res;
        if(value==null) res=false;
        else{
            Voto aux = (Voto) value;
            int key = aux.getIdVoto();
            Voto comp = this.get(key);
            if(comp==null){
                res = false;
            }
            else{
                res=aux.equals(comp);
            }
        }
        return res;
    }

    @Override
    public Voto get(Object key) {
        if (key == null) throw new NullPointerException("id parameter");
		
        Voto result = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(VOTOS_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(VOTOS_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, (int) key);
            
            rs = pstm.executeQuery();
            Collection c = makeObjectsFromResultSet(rs);
            if (c.size() != 1){ // throw new VotoNotFoundException("id = " + id);
            }
            
            Iterator iter = c.iterator();
            try{
                result = (Voto) iter.next();
            }
            catch(NoSuchElementException ex){
                result = null;
            }
        }
	catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    @Override
    public Collection<Voto> values() {
        Collection res = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(VOTOS_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            res = makeObjectsFromResultSet(rs);
        }
        catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    
    public int putVoto(int idEleicao, int idVoto){
        if (idEleicao <= 0) throw new NullPointerException("idEleicao <= 0 ");
        if (idVoto <=0) throw new NullPointerException("idVoto <= 0 ");
        PreparedStatement pstmIn = null;
        int value=-1;
        try{
            StringBuilder sbInsert = new StringBuilder();

            sbInsert.append("INSERT INTO ");
            sbInsert.append(VOTOS_TABELA);
            sbInsert.append(" ("+VOTOS_ELEICAO+","+VOTOS_LISTAS+")");
            sbInsert.append(" VALUES (? ,?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setInt(1, idEleicao);
            pstmIn.setInt(2, idVoto);
            
            
            int rows = pstmIn.executeUpdate();
            value = rows;
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return value;
    }
    
    @Override
    public Voto put(Integer key, Voto value) {
    if (((int)key) < 0) throw new NullPointerException("key parameter");
    if (value==null) throw new NullPointerException("value parameter");
    
    value.setIdVoto((int) key);
    PreparedStatement pstmIn = null;
    
    try{
        StringBuilder sbInsert = new StringBuilder();
        
        sbInsert.append("INSERT INTO ");
        sbInsert.append(VOTOS_TABELA);
        sbInsert.append(" ("+VOTOS_ID+","+VOTOS_ELEICAO+","+VOTOS_LISTAS+")");
        sbInsert.append(" VALUES (? ,?, ?)");
        
        pstmIn = conexao.prepareStatement(sbInsert.toString());
        
        pstmIn.setInt(1, value.getIdVoto());
        pstmIn.setInt(2, value.getIdEleicao());
        pstmIn.setInt(3, value.getIdLista());
        
        int rows = pstmIn.executeUpdate();
        if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
    }
    catch (SQLException ex) {
        Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
        try {
            if (pstmIn != null) pstmIn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return value;
    }
    
   @Override
    public void putAll(Map<? extends Integer, ? extends Voto> m) {
        
        for(Entry<? extends Integer, ? extends Voto> entry : m.entrySet()) 
            put(entry.getKey(),entry.getValue());
    }

    @Override
    public Voto remove(Object key) {
        // DELETE FROM `SGEDb`.`Votos` WHERE `idVotos`= ?;
        Voto res = this.get((int)key);
        if(res!=null){
            
            PreparedStatement pstmIn = null;
          
            try{
                StringBuilder sbInsert = new StringBuilder();
                
                sbInsert.append("DELETE FROM ");
                sbInsert.append(VOTOS_TABELA);
                sbInsert.append(" WHERE ");
                sbInsert.append(VOTOS_ID);
                sbInsert.append(" = ?");
        
                pstmIn = conexao.prepareStatement(sbInsert.toString());
        
                pstmIn.setInt(1, (int)key);
                
                int rows = pstmIn.executeUpdate();
                
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstmIn != null) pstmIn.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    
    @Override
    public void clear() {
        // TRUNCATE TABLE tablename;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("TRUNCATE TABLE ");
            sbSelect.append(VOTOS_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            int rows = pstm.executeUpdate();
        }
	catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    // UnsupportedOperation -> Impossivel garantir a transitividade do set numa Database
    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Set<Entry<Integer, Voto>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //double []votes =  new double[]  {12000, 7500, 4500, 3000}; -> DOUBLE
    //String[] party = {"A","B","C","D"}; -> INTEGER
    public Map<Integer,Double> votosPorListaPorCirculo(int idCirculo, int idEleicao){
        Map<Integer,Double> result = new HashMap<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            //SELECT idLista,(count(idVotos)) from (votos join lista on Lista_idLista = cabeça_lista) 
            //join eleitores on cabeça_lista = idEleitor 
            //where Circulo_idCirculoEleitoral = 13 group by idLista;
            
            /*sbSelect.append("SELECT ");sbSelect.append(LISTAS_CABECA);
            sbSelect.append(" , (count(");sbSelect.append(VOTOS_ID);
            sbSelect.append(")) FROM ((");
            sbSelect.append(VOTOS_TABELA);sbSelect.append(" JOIN ");
            sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON ");sbSelect.append(VOTOS_LISTAS);
            sbSelect.append(" = ");
            sbSelect.append(LISTAS_CABECA);sbSelect.append(") JOIN ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_CABECA);
            sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(VOTOS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE ");sbSelect.append(ELEITORES_CIRCULO);
            sbSelect.append(" = ? AND ");
            sbSelect.append(ELEICOES_ID);sbSelect.append(" = ? GROUP BY "); 
            sbSelect.append(LISTAS_ID);
            */
            
            sbSelect.append("SELECT ");sbSelect.append(LISTAS_CABECA);sbSelect.append(" , (COUNT(");
            sbSelect.append(VOTOS_ID);sbSelect.append(")) FROM ((SELECT ");sbSelect.append(VOTOS_ID);
            sbSelect.append(" , ");sbSelect.append(VOTOS_ELEICAO);sbSelect.append(" , ");sbSelect.append(VOTOS_LISTAS);
            sbSelect.append(" FROM ");sbSelect.append(VOTOS_TABELA);sbSelect.append(" JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(VOTOS_ELEICAO);sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE ");sbSelect.append(ELEICOES_ID);sbSelect.append(" = ?) AS fuck JOIN ");
            sbSelect.append(LISTAS_TABELA);sbSelect.append(" ON ");sbSelect.append(VOTOS_LISTAS);
            sbSelect.append(" = ");sbSelect.append(LISTAS_CABECA);sbSelect.append(") JOIN ");
            sbSelect.append(ELEITORES_TABELA);sbSelect.append(" ON ");sbSelect.append(LISTAS_CABECA);
            sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_CIRCULO);sbSelect.append(" = ? GROUP BY ");
            sbSelect.append(LISTAS_CABECA);
            
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            pstm.setInt(2, idCirculo);
           
           
           
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int lista = rs.getInt(1);
                double votos = rs.getInt(2);
                result.put(lista, votos);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    public int getMaxDeputadosCirculo(int idCirculo){
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(CIRCULO_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(CIRCULO_ID);
            sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idCirculo);
                
                rs = pstm.executeQuery();
                if(rs.next()) {
                    result = rs.getInt(CIRCULO_CADEIRAS);
                }
                    }
                    catch (SQLException ex){
                        Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        try {
                            if (pstm != null) pstm.close();
                            if (rs != null)rs.close();
                        } 
                        catch (SQLException ex) {
                            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    return result;
    }
    
    public Map<Integer,Integer> resultadosPresidencial(int idEleicao){
        Map<Integer,Integer> result = new HashMap<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            /*SELECT nome_candidato, (count(nome_candidato)) FROM 
            ((SELECT idVotos, Lista_idLista  FROM votos JOIN eleicoes 
            on Eleicoes_idEleicao = idEleicao where idEleicao = 3) as fuck 
            JOIN lista on Lista_idLista = idLista)
            JOIN eleitores on cabeça_lista = idEleitor
            GROUP BY nome_candidato
            ORDER BY (count(nome_candidato)) DESC;
            */
            
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" , ");
            sbSelect.append("(count(");sbSelect.append(ELEITORES_NOME_C);sbSelect.append(")) FROM ");
            sbSelect.append("((SELECT ");sbSelect.append(VOTOS_ID);sbSelect.append(" , ");
            sbSelect.append(VOTOS_LISTAS);sbSelect.append(" FROM ");sbSelect.append(VOTOS_TABELA);
            sbSelect.append(" JOIN ");sbSelect.append(ELEICOES_TABELA);sbSelect.append(" ON ");
            sbSelect.append(VOTOS_ELEICAO);sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE ");sbSelect.append(ELEICOES_ID);sbSelect.append(" = ?");
            sbSelect.append(") AS kcuf JOIN ");sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON ");sbSelect.append(VOTOS_LISTAS);
            sbSelect.append(" = ");sbSelect.append(LISTAS_ID);
            sbSelect.append(") JOIN ");sbSelect.append(ELEITORES_TABELA);sbSelect.append(" ON ");
            sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(" GROUP BY ");sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" ORDER BY (COUNT(");sbSelect.append(ELEITORES_NOME_C);sbSelect.append(")) DESC");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int candidato = rs.getInt(1);
                int votos = rs.getInt(2);
                result.put(candidato, votos);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }  
    
    
    public int votosEfectuadosNaEleicao(int idEleicao) {
	
        int result = -1;
        ResultSet rs = null;
	PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            // SELECT count(idVotos) FROM Votos JOIN Eleicoes ON Eleicoes_idEleicao = idEleicao WHERE idEleicao = 5
            sbSelect.append("SELECT COUNT(");
            sbSelect.append(VOTOS_ID);
            sbSelect.append(") FROM ");
            sbSelect.append(VOTOS_TABELA);
            sbSelect.append(" JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(VOTOS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ?"); 
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(1);
        }catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int votosEfectuadosNaLista(int idLista) {
	
        int result = -1;
        ResultSet rs = null;
	PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            // SELECT count(idVotos) FROM Votos JOIN Eleicoes ON Eleicoes_idEleicao = idEleicao WHERE idEleicao = 5
            sbSelect.append("SELECT COUNT(");
            sbSelect.append(VOTOS_ID);
            sbSelect.append(") FROM ");
            sbSelect.append(VOTOS_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(VOTOS_LISTAS);
            sbSelect.append(" = ?"); 
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idLista);
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(1);
        }catch (SQLException ex){
            Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(VotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    // Map<partido,cadeirasganhas> a
    // Map<circulos,a> total
}

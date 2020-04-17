package Dados3;

import Negocio3.Candidato;
import Negocio3.CirculoEleitoral;
import Negocio3.Eleitor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
        private String nome;
	private String dt_nascimento;
	private CirculoEleitoral circulo;
	private Canditato canditato;
 */
public class EleitorDAO extends EleitorConstants implements Map<Integer,Eleitor>{
    
    public CirculoEleitoral getCirculo(int idCirculo){
        CirculoEleitoral result = new CirculoEleitoral();
         if (idCirculo < 0) throw new NullPointerException("idCirculo parameter");
		
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
            
            pstm.setInt(1,idCirculo);
            
            rs = pstm.executeQuery();
            rs.next();
            int id = rs.getInt(CIRCULO_ID);
            String nome = rs.getString(CIRCULO_NOME);
            int deputados = rs.getInt(CIRCULO_CADEIRAS);
            result.setIdCirculo(id);
            result.setDesignacao(nome);
            result.setDeputados(deputados);   
        }
        catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    

    public void setCandidato(int idEleitor, boolean estado){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_CANDIDATO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1, estado);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //UPDATE `sgedb`.`Eleitores` SET `candidato`='1' WHERE `idEleitor`='28';

    public boolean candidatoJaExiste(int idEleitor){
        boolean result = false;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_CANDIDATO); sbSelect.append(" FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1,idEleitor);
            
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getBoolean(1);
            
        }
        catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;  
    }
    
    public Candidato loginComoCandidato(int idEleitor){
      Candidato result = new Candidato();
        if (idEleitor < 0) throw new NullPointerException("idEleitor parameter");

        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1,idEleitor);
            
            rs = pstm.executeQuery();
            rs.next();
            
            result.setEstado(true);
            result.setNome(ELEITORES_NOME_C);
            
        }
        catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;  
    }
    
    public Candidato getCandidato(int idEleitor){
        Candidato result = new Candidato();
        if (idEleitor < 0) throw new NullPointerException("idEleitor parameter");

        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1,idEleitor);
            
            rs = pstm.executeQuery();
            rs.next();
            
            if(rs.getBoolean(ELEITORES_CANDIDATO)){
                result.setEstado(true);
                result.setNome(ELEITORES_NOME_C);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    } 
    
    public int getIdEleitor(String cartao_cidadao){
        int res = -1; // nao existe
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
                try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_CC);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setString(1,cartao_cidadao);
            
            rs = pstm.executeQuery();
            rs.next();
            
            // TESTAR SE Cartao de cidadao é unico (tem de ser garantido pela DB)
            res = rs.getInt(ELEITORES_ID);
        }
        //catch (SQLException ex){
         //   Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        //}
                catch (Exception e){
                    // FUCK IT!
                }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return res;
    }
    
    private Collection makeObjectsFromResultSet(final ResultSet rs) throws java.sql.SQLException {
        Collection result = new java.util.ArrayList();
        String nome; LocalDate dtn; CirculoEleitoral ce; Candidato cand;
        Eleitor aux;
        while (rs.next()){
            int id = rs.getInt(ELEITORES_ID);
            nome = rs.getString(ELEITORES_NOME);
            dtn = asLocalDate(rs.getDate(ELEITORES_DT_N));
            ce = getCirculo(rs.getInt(ELEITORES_CIRCULO));
            if(rs.getBoolean(ELEITORES_CANDIDATO)) {
                //cand = getCandidato(rs.getInt(id));
                cand = new Candidato(rs.getString(ELEITORES_NOME_C));
                aux = new Eleitor(id,nome,rs.getString(ELEITORES_CC),dtn,ce,rs.getBoolean(ELEITORES_VOTO),cand);
            }
            else {
               // cand = new Candidato();
               aux = new Eleitor(id,nome,rs.getString(ELEITORES_CC),dtn,ce,rs.getBoolean(ELEITORES_VOTO));
            }
            // Eleitor(int id, String nome, String cartao_cidadao, LocalDate nascimento, CirculoEleitoral circulo, boolean voto)
            
            
            /*Eleitor aux = new Eleitor(nome,dtn,ce,cand);
            aux.setId(id); 
            aux.setVoto(rs.getBoolean(ELEITORES_VOTO));
            aux.setCartao_cidadao(rs.getString(ELEITORES_CC));*/
            
            
            
            // tenho de ver como tratar a classe/superclasse/relaçao
            // entre o utilizador/eleitor/candidato
            // aux.getCandidato().setId(id);
            
            if(rs.getBoolean(ELEITORES_ADMIN)) aux.setAdmin(true);
            else aux.setAdmin(false);
            
  
            
            result.add(aux);
        }
        return result;
    }    
    
    @Override
    public int size() {
        int result = 0;
        ResultSet rs = null;
	PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT (COUNT(");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(")) FROM ");
            sbSelect.append(ELEITORES_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(1);
		}
		catch (SQLException ex){
                    Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally{
                    try {
                        if (pstm != null) pstm.close();
                        if (rs != null)rs.close();
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean res;
        if (key==null) res=false;
        else{
            int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;
        
            StringBuilder sbSelect = new StringBuilder();
		
            try{
                sbSelect.append("select count(*) from ");
                sbSelect.append(ELEITORES_TABELA);
                sbSelect.append(" WHERE ");
                sbSelect.append(ELEITORES_ID);
                sbSelect.append(" = ?");
            
             pstm = conexao.prepareStatement(sbSelect.toString());
             
             pstm.setInt(1,(Integer) key);
             
             rs = pstm.executeQuery();
             if(rs.next()) result = rs.getInt(1);
            }
            catch (SQLException ex){
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Eleitor aux = (Eleitor) value;
            int key = aux.getId();
            Eleitor comp = this.get(key);
            if(comp==null){
                res = false;
            }
            else{
                res=aux.equals(comp);
            }
        }
        return res; }

    @Override
    public Eleitor get(Object key) {
        if (key == null) throw new NullPointerException("id parameter");
		
        Eleitor result = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, (int) key);
            
            rs = pstm.executeQuery();
            Collection c = makeObjectsFromResultSet(rs);
            if (c.size() != 1){ // throw new VotoNotFoundException("id = " + id);
            }
            
            Iterator iter = c.iterator();
            try{
                result = (Eleitor) iter.next();
            }
            catch(NoSuchElementException ex){
                result = null;
            }
        }
	catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    @Override
    public Eleitor put(Integer key, Eleitor value) {
        if (((int)key) < 0) throw new NullPointerException("key parameter");
        if (value==null) throw new NullPointerException("value parameter");
    
    value.setId((int) key);
    PreparedStatement pstmIn = null;
    
    try{
        StringBuilder sbInsert = new StringBuilder();
        
        sbInsert.append("INSERT INTO ");
        sbInsert.append(ELEITORES_TABELA);
        sbInsert.append(" ("+ELEITORES_ID+","+ELEITORES_ADMIN+","+ELEITORES_NOME+","+ELEITORES_CC+","
                + ""+ELEITORES_NOME_C+","+ELEITORES_CANDIDATO+","+ELEITORES_DT_N+","+ELEITORES_CIRCULO+","
                + ""+ELEITORES_VOTO+")");
        sbInsert.append(" VALUES (?,?,?,?,?,?,?,?,?)");
        
        pstmIn = conexao.prepareStatement(sbInsert.toString());
        
        pstmIn.setInt(1, value.getId());
        pstmIn.setBoolean(2,value.isAdmin());
        pstmIn.setString(3, value.getNome());
        pstmIn.setString(4,value.getCartao_cidadao());
        pstmIn.setString(5, value.getCandidato().getNome());
        pstmIn.setBoolean(6, value.isCandidato());
        pstmIn.setDate(7, asSQLDate(asDate(value.getDt_nascimento())));
        pstmIn.setInt(8,value.getCirculo().getIdCirculo());
        pstmIn.setBoolean(9,value.getVoto());
        
        int rows = pstmIn.executeUpdate();
        if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
    }
    catch (SQLException ex) {
        Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
        try {
            if (pstmIn != null) pstmIn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return value;
    }

    @Override
    public Eleitor remove(Object key) {
        Eleitor res = this.get((int)key);
        if(res!=null){
            
            PreparedStatement pstmIn = null;
          
            try{
                StringBuilder sbInsert = new StringBuilder();
                
                sbInsert.append("DELETE FROM ");
                sbInsert.append(ELEITORES_TABELA);
                sbInsert.append(" WHERE ");
                sbInsert.append(ELEITORES_ID);
                sbInsert.append(" = ?");
        
                pstmIn = conexao.prepareStatement(sbInsert.toString());
        
                pstmIn.setInt(1, (int)key);
                
                int rows = pstmIn.executeUpdate();
                
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstmIn != null) pstmIn.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Eleitor> m) {
       for(Entry<? extends Integer, ? extends Eleitor> entry : m.entrySet()) 
            put(entry.getKey(),entry.getValue());
    }

    @Override
    public Collection<Eleitor> values() {
        Collection res = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            res = makeObjectsFromResultSet(rs);
        }
        catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    public int setPreRequisitos(int idEleitor, String req1, String req2, String req3){
        if (idEleitor <= 0) throw new NullPointerException("ID do Eleitor é <=0");
        if (req1==null) throw new NullPointerException("Requisito 1 é null");
        if (req2==null) throw new NullPointerException("Requisito 2 é null");
        if (req3==null) throw new NullPointerException("Requisito 3 é null");
        
        
        int rows = -1;
        PreparedStatement pstmIn = null;
        StringBuilder sbInsert = new StringBuilder();
    
        try{

            // INSERT INTO `SGEDb`.`Requisitos` (`requisito1`, `requisito2`, `requisito3`, `Eleitores_idEleitor`) VALUES (NULL, NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(REQUISITOS_TABELA);
            sbInsert.append(" ("+REQUISITOS_1+","+REQUISITOS_2+","+REQUISITOS_3+","+REQUISITOS_ID+") ");
            sbInsert.append(" VALUES ( ?, ?, ?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setString(1, req1);
            pstmIn.setString(2, req2);
            pstmIn.setString(3, req3);
            pstmIn.setInt(4, idEleitor);
            

            rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
        }
    
    // Unsupported Operations
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported! Table Eleitores is essential to SGE!");
    }
    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Set<Entry<Integer, Eleitor>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setNomeCandidato(String Cartao_cidadao, String nome){
        int idEleitor; 
        try {
            idEleitor = this.getIdEleitor(Cartao_cidadao);
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setString(1, nome);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     // devolve o nome do eleitor
    public String getNomeEleitor(int idEleitor){
          String result = null;
        try {
            ResultSet rs = null;
            PreparedStatement pstm = null;
            
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_NOME);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE "); 
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleitor);
            
            rs = pstm.executeQuery();

            if (rs.next()){
                result = rs.getString(1);
                if (rs.next()){
                    result = "ERRO AO PESQUISAR NOME";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    
    public void setNomeCandidatoID(int idEleitor, String nome){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setString(1, nome);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCirculoEleitoralProvisorio(int idEleitor, int idIlha){
        {
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            //UPDATE `sgedb`.`eleitores` SET `Circulo_idCirculoEleitoral`='31' WHERE `idEleitor`='80';

            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_CIRCULO_PROVISORIO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idIlha);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public void setCirculoEleitoral(int idEleitor, int circulo){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            //UPDATE `sgedb`.`eleitores` SET `Circulo_idCirculoEleitoral`='31' WHERE `idEleitor`='80';

            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_CIRCULO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, circulo);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCirculoEleitoralEleitor(int idEleitor){
        if (idEleitor <= 0) throw new NullPointerException("idEleitor<=0");
        int result=-1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" JOIN "); sbSelect.append(CIRCULO_TABELA);
            sbSelect.append(" ON ");sbSelect.append(ELEITORES_CIRCULO);
            sbSelect.append(" = ");sbSelect.append(CIRCULO_ID);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, idEleitor);
            
            rs = pstm.executeQuery();
            if(rs.next()){
                result=rs.getInt(CIRCULO_ID);
                if(rs.next()) result = -1;
            }
        }
	catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
    public void setEleitorVoto(int idEleitor, boolean voto){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            //UPDATE `sgedb`.`eleitores` SET `Circulo_idCirculoEleitoral`='31' WHERE `idEleitor`='80';

            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_VOTO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1, voto);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getEleitorVoto(int idEleitor){
        if (idEleitor <= 0) throw new NullPointerException("idEleitor<=0");
        boolean result=false;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, idEleitor);
            
            rs = pstm.executeQuery();
            if(rs.next()){
                result=rs.getBoolean(ELEITORES_VOTO);
            }
        }
	catch (SQLException ex){
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
  
    // Todos os eleitores passam a nao ter votos na BD
    public void clearVotos(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_VOTO);
            sbSelect.append(" = ? "); 
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1, false);
            
            int rows = pstm.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
     // Usada para obter Collection<ids> (ints) de quem votou (funcao auxiliar para repor circulo eleitoral madeira)
    public Collection getIdsVotou(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            sbSelect.append("SELECT ");sbSelect.append(ELEITORES_ID); 
            sbSelect.append(" FROM ");sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");sbSelect.append(ELEITORES_VOTO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1, true);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                result.add(id);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    public int getCirculoProvisorio(int idEleitor){
        int result = -1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            sbSelect.append("SELECT ");sbSelect.append(ELEITORES_CIRCULO_PROVISORIO); 
            sbSelect.append(" FROM ");sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleitor);
            
            rs = pstm.executeQuery();

            if (rs.next()){
                result = rs.getInt(1);
                if(rs.next()){
                    result = -1;
                    System.err.println("Base de Dados inconsistente! IDEleitores duplicado");
                }

            }
        }
        catch (SQLException ex){
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    
    }
    
    public void clearCirculoProvisorio(int idEleitor){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("UPDATE "); sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEITORES_CIRCULO_PROVISORIO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 0);
            pstm.setInt(2, idEleitor);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

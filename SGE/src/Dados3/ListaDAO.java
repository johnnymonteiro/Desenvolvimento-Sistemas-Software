/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados3;

import Negocio3.Lista;
import Negocio3.Partido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListaDAO extends ListaConstants implements Map<Integer,Lista>{

     /*private Collection makeObjectsFromResultSet(final ResultSet rs) throws java.sql.SQLException {
        Collection result = new java.util.ArrayList();
        
        
        // PARTIDO
        String nomePartido, passe;
        Partido aux;
        
        //
        
        while (rs.next()){
            int idPartido = rs.getInt(PARTIDOS_ID);
            nomePartido = rs.getString(PARTIDOS_NOME);
            passe = rs.getString(PARTIDOS_PW);
            
            int idLista = rs.getInt(LISTAS_ID);
            int idCabeca = rs.getInt(LISTAS_CABECA);
            int idEleicao = rs.getInt(LISTAS_ELEICAO);
            boolean estado = rs.getBoolean(LISTAS_ESTADO);
            
            int id
            
            passe = rs.getString(LISTAS_PW);
            aux =  new Partido(int idPartido, , nome);
            
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
            
            
            //Eleitor aux = new Eleitor(nome,dtn,ce,cand);
            //aux.setId(id); 
            //aux.setVoto(rs.getBoolean(ELEITORES_VOTO));
            //aux.setCartao_cidadao(rs.getString(ELEITORES_CC));
            
            
            
            // tenho de ver como tratar a classe/superclasse/relaçao
            // entre o utilizador/eleitor/candidato
            // aux.getCandidato().setId(id);
            
            if(rs.getBoolean(ELEITORES_ADMIN)) aux.setAdmin(true);
            else aux.setAdmin(false);
            
  
            
            result.add(aux);
        }
        return result;
    }    */
    
    
    public int sizePartido() {

            int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                sbSelect.append("select distinct(count(*)) from ");
                sbSelect.append(PARTIDOS_TABELA);

                pstm = conexao.prepareStatement(sbSelect.toString());

                rs = pstm.executeQuery();
                if(rs.next()) result = rs.getInt(1);
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

   
    
    public Partido putPartidoByKey(Integer key, String nome, String codigo) {
        if (((int)key) < 0) throw new NullPointerException("key parameter");
        if (nome==null) throw new NullPointerException("nome parameter");
        if (codigo==null) throw new NullPointerException("codigo parameter");
        
        PreparedStatement pstmIn = null;
        Partido value = null; 

        try{
            StringBuilder sbInsert = new StringBuilder();
            // INSERT INTO `SGEDb`.`Partidos` (`idPartidos`, `designacao`, `codigo_secreto`) VALUES (NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(PARTIDOS_TABELA);
            sbInsert.append(" ("+PARTIDOS_ID+","+PARTIDOS_NOME+","+PARTIDOS_PW+")");
            sbInsert.append(" VALUES (? ,?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setInt(1, key);
            pstmIn.setString(2, nome);
            pstmIn.setString(3, codigo);
            
            value = new Partido(key,nome,codigo);

            int rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return value;
        }
    
    /*public Partido putPartido(String nome, String codigo_secreto){
       // if (value==null) throw new NullPointerException("value parameter");
        
        PreparedStatement pstmIn = null;
    
        try{
            StringBuilder sbInsert = new StringBuilder();
            // INSERT INTO `SGEDb`.`Partidos` (`idPartidos`, `designacao`, `codigo_secreto`) VALUES (NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(PARTIDOS_TABELA);
            sbInsert.append(" ("+PARTIDOS_NOME+","+PARTIDOS_PW+")");
            sbInsert.append(" VALUES (?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());
            
            pstmIn.setString(1, nome);
            pstmIn.setString(2, codigo_secreto);

            int rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Partido(this.getIdPartido(nome),nome,codigo_secreto);
    }*/
    
 
     // Usada para fazer String no jList
    public Collection listaAbertas(int idPartido){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            // SELECT * from 
                   //(SELECT * from lista join eleitores on cabeça_lista = idEleitor) as le 
                   //join circuloeleitoral on Circulo_idCirculoEleitoral = idCirculoEleitoral
                   //where idLista = ?;
            sbSelect.append("SELECT * FROM ( ");
            sbSelect.append(LISTAS_TABELA);sbSelect.append(" JOIN ");sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(") JOIN ");
            sbSelect.append(CIRCULO_TABELA); sbSelect.append(" ON ");
            sbSelect.append(ELEITORES_CIRCULO); sbSelect.append(" = ");sbSelect.append(CIRCULO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(LISTAS_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idPartido);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String ce = rs.getString(CIRCULO_NOME);
                String id = Integer.toString(rs.getInt(LISTAS_CABECA));
                String cr = Integer.toString(rs.getInt(CIRCULO_CADEIRAS)-this.sizeLista(rs.getInt(LISTAS_CABECA))-1);
                String fi = "ID:"+id+" - Círculo eleitoral "+ce+" com cadeiras restantes:"+cr+" disponiveis.";

                result.add(fi);

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
    
    
    // Partido já existe 
    public  boolean partidoJaExiste(String nomePartido){
        boolean result = true;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            // Select * from eleicoes 
            //join lista on idEleicao = Eleicoes_idEleicao 
            //join partidos on Partidos_idPartidos = idPartidos 
            //where designacao = 'toto';
            sbSelect.append("SELECT * FROM (");
            sbSelect.append(ELEICOES_TABELA);sbSelect.append(" JOIN ");sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_ID);sbSelect.append(" = ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(") JOIN ");
            sbSelect.append(PARTIDOS_TABELA); sbSelect.append(" ON ");
            sbSelect.append(LISTAS_PARTIDO); sbSelect.append(" = ");sbSelect.append(PARTIDOS_ID);
            sbSelect.append(" WHERE "); sbSelect.append(PARTIDOS_NOME);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setString(1, nomePartido);
            
            rs = pstm.executeQuery();

            if (rs.next()) result = false;
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
        return !result; 
    }
    
    //Verifica se o idPartido e idLista existem
    public boolean autenticaLista(int idPartido, int idLista){
        boolean result = false;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            // SELECT * from 
                   //(SELECT * from lista join eleitores on cabeça_lista = idEleitor) as le 
                   //join circuloeleitoral on Circulo_idCirculoEleitoral = idCirculoEleitoral
                   //where idLista = ?;
            sbSelect.append("SELECT * FROM ( ");
            sbSelect.append(LISTAS_TABELA);sbSelect.append(" JOIN ");sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(") JOIN ");
            sbSelect.append(CIRCULO_TABELA); sbSelect.append(" ON ");
            sbSelect.append(ELEITORES_CIRCULO); sbSelect.append(" = ");sbSelect.append(CIRCULO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(LISTAS_ID);
            sbSelect.append(" = ? AND "); sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idPartido);
            pstm.setInt(2, idLista);
            
            rs = pstm.executeQuery();
            if(rs.next()) {
                result = true;
                if(rs.next()) result = false;
            }
        }
        catch (Exception e){
            // FUCK IT
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
    
    
    //Verifica se pode adicionar mais candidatos na Lista
    public boolean listIsNotFull(int idPartido, int idLista){
        boolean res=false;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            // SELECT * from 
                   //(SELECT * from lista join eleitores on cabeça_lista = idEleitor) as le 
                   //join circuloeleitoral on Circulo_idCirculoEleitoral = idCirculoEleitoral
                   //where idLista = ?;
            sbSelect.append("SELECT * FROM ( ");
            sbSelect.append(LISTAS_TABELA);sbSelect.append(" JOIN ");sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);
            sbSelect.append(") JOIN ");
            sbSelect.append(CIRCULO_TABELA); sbSelect.append(" ON ");
            sbSelect.append(ELEITORES_CIRCULO); sbSelect.append(" = ");sbSelect.append(CIRCULO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(LISTAS_ID);
            sbSelect.append(" = ? AND ");sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idPartido);
            pstm.setInt(2, idLista);
            
            rs = pstm.executeQuery();

            if(rs.next()){
                int crDisponiveis = rs.getInt(CIRCULO_CADEIRAS); System.out.println(crDisponiveis);
                int crOcupados = this.sizeLista(idLista); System.out.println(crOcupados);
                if(crDisponiveis>crOcupados+1) res = true;
                if(rs.next()) res =false;
                
            }
        } catch(Exception e){
            // FUCK IT
        }
        return res;
    }
    
// devolve -1 se houver erro
    public int sizeLista(int idCabeca){
        int result = -1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        StringBuilder sbSelect = new StringBuilder();
        
        try{
            //SELECT cabeça_lista,(count(idLista)) from lista join listacandidatos 
                    //on cabeça_lista = Lista_cabeça_lista  
                    //where cabeça_lista = ? 
                    //group by cabeça_lista;
            sbSelect.append("SELECT ");sbSelect.append(LISTAS_CABECA);sbSelect.append(", COUNT(");sbSelect.append(LISTAS_ID);sbSelect.append(")");
            sbSelect.append(" FROM ");sbSelect.append(LISTAS_TABELA);sbSelect.append(" JOIN ");sbSelect.append(LISTAC_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ");sbSelect.append(LISTAC_LISTA_CABECA);
            sbSelect.append(" WHERE ");sbSelect.append(LISTAS_CABECA);sbSelect.append(" = ?");
            sbSelect.append(" GROUP BY ");sbSelect.append(LISTAS_CABECA);
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idCabeca);
            
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(2);
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            // FUCK IT
        } finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    
    //Adiciona candidato a lista
    // idLista == idCirculo // idPartido = idDoGajoQueCriouOPartido
    public int adicionarCandidatoLista(int idCandidato, int idLista, int idPartido){
        if (idCandidato <= 0) throw new NullPointerException("idCandidato <=0");
        if (idLista <=0) throw new NullPointerException("idLista <=0");
        if (idPartido <=0) throw new NullPointerException("idPartido <=0");
        
        int rows=-1;
        PreparedStatement pstmIn = null;
        try{
            StringBuilder sbInsert = new StringBuilder();
            // INSERT INTO `SGEDb`.`ListaCandidatos` (`Lista_idLista`, `Lista_cabeça_lista`, `Eleitores_idEleitor`) VALUES (NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(LISTAC_TABELA);
            sbInsert.append(" ("+LISTAC_LISTA_ID+","+LISTAC_LISTA_CABECA+","+LISTAC_CANDIDATO+")");
            // idPartido,idLista,idCandidato
            sbInsert.append(" VALUES (? ,?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setInt(1, idPartido);
            pstmIn.setInt(2, idLista);
            pstmIn.setInt(3, idCandidato);

            rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
        }
    
    public int getIdPartido(String nomePartido){
        int res = -1; // nao existe
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(PARTIDOS_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(PARTIDOS_NOME);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setString(1,nomePartido);
            
            rs = pstm.executeQuery();
            rs.next();
            
            // TESTAR SE Cartao de cidadao é unico (tem de ser garantido pela DB)
            res = rs.getInt(PARTIDOS_ID);
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
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return res;
    }
    
     
    public int setPreRequisitosPartidos(int idPartido, String req1, String req2, String req3){
        if (idPartido <= 0) throw new NullPointerException("ID do Partido é <=0");
        if (req1==null) throw new NullPointerException("Requisito 1 é null");
        if (req2==null) throw new NullPointerException("Requisito 2 é null");
        if (req3==null) throw new NullPointerException("Requisito 3 é null");
        
        
        int rows = -1;
        PreparedStatement pstmIn = null;
        StringBuilder sbInsert = new StringBuilder();
    
        try{

            //INSERT INTO `SGEDb`.`RequisitosPartidos` (`requisito1`, `requisito2`, `requisito3`, `Partidos_idPartidos`) VALUES (NULL, NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(REQUISITOS_PARTIDO_TABELA);
            sbInsert.append(" ("+REQUISITOS_1+","+REQUISITOS_2+","+REQUISITOS_3+","+REQUISITOS_PARTIDO_ID+") ");
            sbInsert.append(" VALUES ( ?, ?, ?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setString(1, req1);
            pstmIn.setString(2, req2);
            pstmIn.setString(3, req3);
            pstmIn.setInt(4, idPartido);
            

            rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
        }
    
    public int setPreRequisitos(int idPartido, String req1, String req2, String req3){
        if (idPartido <= 0) throw new NullPointerException("ID do Partido é <=0");
        if (req1==null) throw new NullPointerException("Requisito 1 é null");
        if (req2==null) throw new NullPointerException("Requisito 2 é null");
        if (req3==null) throw new NullPointerException("Requisito 3 é null");
        
        
        int rows = -1;
        PreparedStatement pstmIn = null;
        StringBuilder sbInsert = new StringBuilder();
    
        try{

            //INSERT INTO `SGEDb`.`RequisitosPartidos` (`requisito1`, `requisito2`, `requisito3`, `Partidos_idPartidos`) VALUES (NULL, NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(REQUISITOS_TABELA);
            sbInsert.append(" ("+REQUISITOS_1+","+REQUISITOS_2+","+REQUISITOS_3+","+REQUISITOS_ID+") ");
            sbInsert.append(" VALUES ( ?, ?, ?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setString(1, req1);
            pstmIn.setString(2, req2);
            pstmIn.setString(3, req3);
            pstmIn.setInt(4, idPartido);
            

            rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
        }
  
    public boolean autenticaPartido(int idPartido, String codigo_secreto){
         if (idPartido == 0) throw new NullPointerException("idPartido = 0");
         if (codigo_secreto == null) throw new NullPointerException("codigo_secreto é null");
         
        ResultSet rs = null;
        PreparedStatement pstm = null;
	boolean res=false;	
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(PARTIDOS_PW);
            sbSelect.append(" FROM ");
            sbSelect.append(PARTIDOS_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(PARTIDOS_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, idPartido);
            
            rs = pstm.executeQuery();
            rs.next();
            
            String pw = rs.getString(1);
            
            res = pw.equals(codigo_secreto);
        }
	catch (Exception e){
            // FUCK IT!
            System.err.println("ERRO SQL");
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
        
        return res;
    }
    
   
    public int getIdCirculo(String circulo_eleitoral){
        //CirculoEleitoral result = new CirculoEleitoral();
        int res = -1;
        if (circulo_eleitoral == null) throw new NullPointerException("Circulo é null");
		
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(CIRCULO_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(CIRCULO_NOME);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setString(1,circulo_eleitoral);
            
            rs = pstm.executeQuery();
            rs.next();
            res = rs.getInt(CIRCULO_ID);
            
            //int id = rs.getInt(CIRCULO_ID);
           /// String nome = rs.getString(CIRCULO_NOME);
            //int deputados = rs.getInt(CIRCULO_CADEIRAS);
            //result.setIdCirculo(id);
            //result.setDesignacao(nome);
            //result.setDeputados(deputados);   
        }
        catch (Exception e){
           // FUCK IT!
            
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
        return res;
        //return result;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lista get(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lista put(Integer key, Lista value){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    // id da lista == id do circulo
    public void putLista(int idLista, int idPartido, int idCabeca, boolean estado, int idEleicao) {
        if (idLista<=0 || idPartido<=0 || idCabeca<=0 ) throw new NullPointerException("parametros é null");
        
        PreparedStatement pstmIn = null;

        try{
            StringBuilder sbInsert = new StringBuilder();
            // INSERT INTO `SGEDb`.`Lista` (`idLista`, `Partidos_idPartidos`, `cabeça_lista`, `Eleicoes_idEleicao`, `estado`) VALUES (NULL, NULL, NULL, NULL, NULL);
            sbInsert.append("INSERT INTO ");
            sbInsert.append(LISTAS_TABELA);
            sbInsert.append(" ("+LISTAS_ID+","+LISTAS_PARTIDO+","+LISTAS_CABECA+","+LISTAS_ELEICAO+","+LISTAS_ESTADO+") ");
            sbInsert.append(" VALUES (? ,?, ?, ?, ?)");

            pstmIn = conexao.prepareStatement(sbInsert.toString());

            pstmIn.setInt(1, idLista);
            pstmIn.setInt(2, idPartido);
            pstmIn.setInt(3, idCabeca);
            pstmIn.setInt(4, idEleicao);
            pstmIn.setBoolean(5, estado);

            int rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Lista remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Lista> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Lista> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Lista>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setListaAprovada(int idLista, boolean estado){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" SET "); sbSelect.append(LISTAS_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(LISTAS_CABECA); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1,estado );
            pstm.setInt(2, idLista);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPartidoAprovado(int idEleicao, boolean estado){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" SET "); sbSelect.append(LISTAS_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(LISTAS_CABECA); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setBoolean(1,estado );
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// devolve a collection<int> ids das listas de um partido
    public Collection getIdListasPartidos(int idEleicao, int idPartido){
        
            Collection result = null;
            try {
            result = new java.util.ArrayList();
            ResultSet rs = null;
            PreparedStatement pstm = null;
            
            StringBuilder sbSelect = new StringBuilder();
            
            
            sbSelect.append("SELECT * "); 
            sbSelect.append(" FROM (");
            sbSelect.append(PARTIDOS_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON "); sbSelect.append(PARTIDOS_ID);
            sbSelect.append(" = "); sbSelect.append(LISTAS_PARTIDO);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ? AND ");sbSelect.append(LISTAS_PARTIDO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            pstm.setInt(2, idPartido);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int id =rs.getInt(LISTAS_CABECA);
                result.add(id);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        }
   
    // devolve o nome do candidato a presidente com o ID correspondente
    public String getNomeCandidato(int idCandidato){
          String result = null;
        try {
            ResultSet rs = null;
            PreparedStatement pstm = null;
            
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE "); 
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idCandidato);
            
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
    
// devolve o nome do partido com o ID correspondente
    public String getNomePartido(int idLista){
        String result = null;
        try {
            ResultSet rs = null;
            PreparedStatement pstm = null;
            
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("SELECT * "); 
            sbSelect.append(" FROM ");
            sbSelect.append(PARTIDOS_TABELA);sbSelect.append(" JOIN ");
            sbSelect.append(LISTAS_TABELA);sbSelect.append(" ON ");
            sbSelect.append(PARTIDOS_ID);sbSelect.append(" = ");
            sbSelect.append(LISTAS_PARTIDO);
            sbSelect.append(" WHERE "); 
            sbSelect.append(LISTAS_CABECA);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idLista);
            
            rs = pstm.executeQuery();

            if (rs.next()){
                result = rs.getString(PARTIDOS_NOME);
                if (rs.next()){
                    result = "ERRO AO PESQUISAR NOME";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

// devolve o nome do circulo com o ID correspondente    
    public String geNomeCirculo(int idCirculo){
         String result = null;
        try {
            ResultSet rs = null;
            PreparedStatement pstm = null;
            
            StringBuilder sbSelect = new StringBuilder();
            
            sbSelect.append("SELECT * "); 
            sbSelect.append(" FROM ");
            sbSelect.append(CIRCULO_TABELA);
            sbSelect.append(" WHERE "); 
            sbSelect.append(CIRCULO_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idCirculo);
            
            rs = pstm.executeQuery();

            if (rs.next()){
                result = rs.getString(CIRCULO_NOME);
                if (rs.next()){
                    result = "ERRO AO PESQUISAR NOME";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Set o estado da lista do partido para aprovada
    public void aprovaPartido(int idEleicao, int idPartido){
            Collection list = this.getIdListasPartidos(idEleicao, idPartido);
            for(Object aux: list){
                this.setListaAprovada((int) aux, true);
            }
    }
    
    // Set o estado do candidato para aprovada
    public void aprovaCandidato(int idEleicao, int idCandidato){
        // getListaPartidos serve igual aki! nao é typo error
        this.setListaAprovada(idCandidato, true);
    }
    
    
    public int getIdCandidatoParaVoto(String nomeCandidato){
        int res = -1; // nao existe
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
                try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setString(1,nomeCandidato);
            
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
    
    public int getIdListaParaVoto(int idEleicao, String partido, int idCirculo){
            int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                sbSelect.append("SELECT *");
                sbSelect.append(" FROM ((");sbSelect.append(LISTAS_TABELA);
                sbSelect.append(" JOIN ");sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
                sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);sbSelect.append(") JOIN ");
                sbSelect.append(ELEITORES_TABELA);sbSelect.append(" ON ");sbSelect.append(LISTAS_CABECA);
                sbSelect.append(" = ");sbSelect.append(ELEITORES_ID);sbSelect.append(") JOIN ");
                sbSelect.append(PARTIDOS_TABELA);sbSelect.append(" ON ");sbSelect.append(LISTAS_PARTIDO);
                sbSelect.append(" = ");sbSelect.append(PARTIDOS_ID);sbSelect.append(" WHERE ");
                sbSelect.append(ELEICOES_ID);sbSelect.append(" = ? AND ");sbSelect.append(PARTIDOS_NOME);
                sbSelect.append(" = ? AND ");sbSelect.append(ELEITORES_CIRCULO);sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);
                pstm.setString(2, partido);
                pstm.setInt(3, idCirculo);

                rs = pstm.executeQuery();
                if(rs.next()) {
                    result = rs.getInt(LISTAS_ID);
                    if(rs.next()) result = -1;
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
    
    // devolve o numero de partidos com listas criadas numa dada eleicao
    public int getNumeroPartidos(int idEleicao){
        int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                //SELECT count(distinct(designacao)) 
                //FROM (lista join eleicoes on Eleicoes_idEleicao = idEleicao) 
                //join partidos on Partidos_idPartidos = idPartidos 
                //where idEleicao = 5;
                
                sbSelect.append("SELECT COUNT(DISTINCT(");sbSelect.append(PARTIDOS_NOME);
                sbSelect.append(")) FROM (");sbSelect.append(LISTAS_TABELA);
                sbSelect.append(" JOIN ");sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
                sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);sbSelect.append(") JOIN ");
                sbSelect.append(PARTIDOS_TABELA);sbSelect.append(" ON ");
                sbSelect.append(LISTAS_PARTIDO); sbSelect.append(" = ");sbSelect.append(PARTIDOS_ID);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);

                rs = pstm.executeQuery();
                if(rs.next()) {
                    result = rs.getInt(1);
                    if(rs.next()) result = -1;
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
}


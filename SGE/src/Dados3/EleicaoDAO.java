package Dados3;

import Negocio3.Eleicao;
import Negocio3.Eleitor;
import Negocio3.Vencedor;
import Negocio3.Voto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/** *        FALTA IR BUSCAR OS VALORES DO TIPO DE ELEICAO A TABELA (TIPOSDEELEICAO)
        private int idEleicao;
	private LocalDate dt_iniFase_eleitoral;
	private LocalDate dt_fimFase_eleitoral;
	private LocalDate dt_iniCandidatura;
	private LocalDate dt_fimCandidatura;
	private LocalDate dt_eleicao;
	private int fase;
	private boolean regiao_autonoma;
	private int tipo;
	private int[] resultadoVotos;
	private Map<Integer,Vencedor> vencedores;
	private Map<Integer,GestorDeEleicao> admins;
	private Map<Integer,Eleitor> eleitores;
	private Map<Integer,Voto> votos;
*/

public class EleicaoDAO extends EleicaoConstants implements Map<Integer,Eleicao> {
    
    
    // Usada para fornecer String do jList -> Consulta de eleições por datas
    public Collection eleicoesHistoricoData(int tipo, Date dt_inicio, Date dt_fim){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            //select designacao,dt_eleicao 
            //from eleicoes join tipoeleicoes on idTipoEleicoes = TipoEleicoes_idTipoEleicoes  
            //where dt_inicio>='2000-01-01' AND dt_fim<='2017-01-01' AND estado=6;
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);sbSelect.append(" , ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_E);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? AND ");sbSelect.append(ELEICOES_DT_I);
            sbSelect.append(" >= ? AND ");sbSelect.append(ELEICOES_DT_F);
            sbSelect.append(" <= ? AND ");sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 6);
            pstm.setDate(2,this.asSQLDate(dt_inicio));
            pstm.setDate(3,this.asSQLDate(dt_fim));
            pstm.setInt(4,tipo);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_E));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String fi = "Eleição "+nome+" "+date+" - ID:"+id;
                
                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para fazer String no jList ADMIN 
    public Collection eleicoesAdmin(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);sbSelect.append(" , ");
            sbSelect.append(ESTADO_NOME);
            sbSelect.append(" FROM (");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(") JOIN "); sbSelect.append(ESTADO_TABELA);
            sbSelect.append(" ON ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ");sbSelect.append(ESTADO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO); 
            sbSelect.append(" = ? OR "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 1);
            pstm.setInt(2, 2);
            pstm.setInt(3, 3);
            pstm.setInt(4, 4);
            pstm.setInt(5, 5);
            pstm.setInt(6, 6);
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String estadoNome = rs.getString(ESTADO_NOME);
                String fi;
                fi ="ID:"+id+" - "+ nome+" "+date+" "+estadoNome;
                result.add(fi);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
     // Usada para fazer String no jList -> ELEICOES COM PERIODO DE CANDIDATURA ABERTA E RECEM-CRIADAS
    public Collection eleicoesRecemCriadas(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);sbSelect.append(" , ");
            sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO); 
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 1);
            pstm.setInt(2, 2);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                int estado = rs.getInt(ELEICOES_ESTADO);
                String fi;
                if(estado==1){
                    fi = nome+" "+date+" - ID:"+id+" Estado: Período de Candidatura por Iniciar";
                }
                else {
                    fi = nome+" "+date+" - ID:"+id+" Estado: Período de Candidatura Iniciado";
                }

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para obter IDs das eleicoes recem-criadas
    public Collection eleicoesRecemCriadasID(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? OR ");sbSelect.append(ELEICOES_ESTADO); 
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 1);
            pstm.setInt(2, 2);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int fi = rs.getInt(1);
                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    
    
    
    // Usada para fazer String no jList -> ELEICOES COM PERIODO DE CANDIDATURA ABERTA
    public Collection eleicoesAberta(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 2);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String fi = nome+" "+date+" - ID:"+id;

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para obter IDs das eleicoesAbertas
    public Collection eleicoesAbertaID(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 2);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int fi = rs.getInt(1);
                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }

    
    // Usada para fazer String no jList -> ELEICOES COM PERIODO DE CANDIDATURA ABERTA
    public Collection eleicoesAbertaAVotos(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 5);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String fi = nome+" "+date+" - ID:"+id;

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para fazer String no jList -> ELEICOES COM PERIODO DE CANDIDATURA FECHADO
    public Collection eleicoesProntaAVoto(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 4);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String fi = nome+" "+date+" - ID:"+id;

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    //IDs das eleiçãos prontos a iniciar, ou seja, periodo de candidatura fechado
    // Usada para fazer String no jList -> ELEICOES COM PERIODO DE CANDIDATURA FECHADO
    public Collection eleicoesProntaAVotoID(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 4);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int fi = rs.getInt(1);

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    
    //Eleições com peiriodo de candidatura fechada
    public Collection eleicoesFechadaCandidatura(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(TIPOELEICOES_NOME);
            sbSelect.append(" , "); sbSelect.append(ELEICOES_DT_I); sbSelect.append( " , ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 3);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(TIPOELEICOES_NOME);
                LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_I));
                String date = Integer.toString(dte.getYear());
                String id = Integer.toString(rs.getInt(ELEICOES_ID));
                String fi = nome+" "+date+" - ID:"+id;

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para obter IDs das eleições com periodo de candidatura fechada
    public Collection eleicoesFechadaCandidaturaID(){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(TIPOELEICOES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_TIPO);
            sbSelect.append(" = "); sbSelect.append(TIPOELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 3);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                int fi = rs.getInt(1);

                result.add(fi);

            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para fazer String no Jlist para fornecer Candidatos a Presidencial no acto do Voto
    public Collection getListaNominalEleicaoVotos(){
        int ide = this.getIdEleicaoAbertaAVotos();
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" FROM (");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON "); sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = "); sbSelect.append(LISTAS_CABECA);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ? AND ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, ide);
            pstm.setInt(2, 5);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(1);
                //String id = Integer.toString(rs.getInt(1));
                
                
                String fi = nome;
                result.add(fi);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    // Usada para fazer String no jList para fornecer Candidatos
    public Collection getListaCandidatos(int idEleicao){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" , "); sbSelect.append(ELEITORES_NOME_C);
            sbSelect.append(" , "); sbSelect.append(LISTAS_ESTADO);
            sbSelect.append(" FROM (");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" ON "); sbSelect.append(ELEITORES_ID);
            sbSelect.append(" = "); sbSelect.append(LISTAS_CABECA);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                String id = Integer.toString(rs.getInt(1));
                boolean est = rs.getBoolean(3);
                String ti;
                if (est) {
                    ti = "Aprovado!";
                }
                else {
                    ti = "Não está aprovado!";
                }
                String fi = nome+"  - ID:"+id+" ESTADO: "+ti;
                result.add(fi);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    
    // Usada para fazer String no jList para fornecer Partidos
    public Collection getListaPartidos(int idEleicao){
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT DISTINCT(");
            sbSelect.append(PARTIDOS_ID);
            sbSelect.append(") , "); sbSelect.append(PARTIDOS_NOME);
            sbSelect.append(" , "); sbSelect.append(LISTAS_ESTADO);
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
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                String id = Integer.toString(rs.getInt(1));
                boolean est = rs.getBoolean(3);
                String ti;
                if (est) {
                    ti = "Aprovado!";
                }
                else {
                    ti = "Não está aprovado!";
                }
                String fi = nome+"  - ID:"+id+" ESTADO: "+ti;
                result.add(fi);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    public Collection getListaPartidosEleicaoVotos(){
        int ide = this.getIdEleicaoAbertaAVotos();
        Collection result = new java.util.ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT DISTINCT(");
            sbSelect.append(PARTIDOS_ID);
            sbSelect.append(") , "); sbSelect.append(PARTIDOS_NOME);
            sbSelect.append(" , "); sbSelect.append(LISTAS_ESTADO);
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
            sbSelect.append(" = ? AND ");sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? AND ");sbSelect.append(LISTAS_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, ide);
            pstm.setInt(2, 5);
            pstm.setBoolean(3, true);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                String nome = rs.getString(2);
                //String id = Integer.toString(rs.getInt(1));
                
                
                String fi = nome;
                result.add(fi);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
   //Fornece o id da única eleição em aberto para voto e -1 caso contrario
    public int idEleicaoAbertaAVtoto(){
        int res = -1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(ESTADO_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = "); sbSelect.append(ESTADO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 5);
            
            rs = pstm.executeQuery();
            if(rs.next()) res = rs.getInt(1);
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res; 
    }
   
    //Fornce quantas eleições em recem-criadas
    //Fornece o id da única eleição em aberto para voto e -1 caso contrario
    public int quantasEleicoesPeriodoCandidaturaAberta(){
        int res = -1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT COUNT(");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(") FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 2);
            
            rs = pstm.executeQuery();
            rs.next();
            res = rs.getInt(1);
            if(rs.next()) res = -1;
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res; 
    }
    
    //Fornece o id da única eleição em aberto para candidatura e -1 caso contrario
    public int idEleicaoAberta(){
        int res = -1;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(ESTADO_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = "); sbSelect.append(ESTADO_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 2);
            
            rs = pstm.executeQuery();
            rs.next();
            res = rs.getInt(1);
            if(rs.next()) res = -1;
        }
        catch (SQLException ex){
            //Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res; 
    }
    
    static private Map<Integer,Voto> getVotos(int idEleicao){
       // return new HashMap<>();
       HashMap<Integer,Voto> res = new HashMap<>();
       VotoDAO votoDb = new VotoDAO();
       Collection<Voto> votos = votoDb.values();
       for (Voto v : votos){
           res.put(v.getIdVoto(), v);
       }
       return res;
    }
    static private Map<Integer,Eleitor> getEleitores(int idEleicao){
        return new HashMap<>();
    }
    static private Map<Integer,Vencedor> getVencedores(int idEleicao){
        return new HashMap<>();
    }
    
    private Collection makeObjectsFromResultSet(final ResultSet rs) throws java.sql.SQLException {
        Collection result = new java.util.ArrayList();
       // Date aux;
        while (rs.next()){
            int id = rs.getInt(ELEICOES_ID);
            
            //asLocalDate(rs.getDate(ELEITORES_DT_N))
            //aux = rs.getDate(ELEICOES_DT_I);
            //LocalDate dti = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dti = asLocalDate(rs.getDate(ELEICOES_DT_I));
            
            //aux = rs.getDate(ELEICOES_DT_F);
            //LocalDate dtf = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dtf = asLocalDate(rs.getDate(ELEICOES_DT_F));
            
            //aux = rs.getDate(ELEICOES_DT_IC);
            //LocalDate dtic = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dtic = asLocalDate(rs.getDate(ELEICOES_DT_IC));
            
            //aux = rs.getDate(ELEICOES_DT_FC);
            //LocalDate dtfc = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dtfc = asLocalDate(rs.getDate(ELEICOES_DT_FC));
            
            //aux = rs.getDate(ELEICOES_DT_E);
            //LocalDate dte = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dte = asLocalDate(rs.getDate(ELEICOES_DT_E));
            
            int fase = rs.getInt(ELEICOES_FASE);
            int ra = rs.getInt(ELEICOES_AUTONOMA);
            int tipo = rs.getInt(ELEICOES_TIPO);
            int[] votos = {rs.getInt(ELEICOES_VOTOS_B),rs.getInt(ELEICOES_VOTOS_N),rs.getInt(ELEICOES_VOTOS_A),0};
            
            Map<Integer,Voto> k = getVotos(id);
            Map<Integer,Eleitor> c = getEleitores(id);
            Map<Integer,Vencedor> f = getVencedores(id);
            
            int e = rs.getInt(ELEICOES_ESTADO);
            Eleicao res = new Eleicao(id,dti,dtf,dtic,dtfc,dte,fase,ra,tipo,votos,f,c,k,e);
            result.add(res);
        }
        return result;
    }
    
    public int getTipoEleicao(int idEleicao){
        Eleicao aux = this.get(idEleicao);
        return aux.getTipo();
    }
    
    @Override
    public int size() {
        int result = 0;
        ResultSet rs = null;
	PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("select distinct(count(*)) from ");
            sbSelect.append(ELEICOES_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            if(rs.next()) result = rs.getInt(1);
		}
		catch (SQLException ex){
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally{
                    try {
                        if (pstm != null) pstm.close();
                        if (rs != null)rs.close();
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
          // select count(*) from Eleicoes where idEleicao = ?;
        boolean res;
        if (key==null) res=false;
        else{
            int result = 0;
            ResultSet rs = null;
            PreparedStatement pstm = null;
        
            StringBuilder sbSelect = new StringBuilder();
		
            try{
                sbSelect.append("select count(*) from ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE ");
                sbSelect.append(ELEICOES_ID);
                sbSelect.append(" = ?");
            
             pstm = conexao.prepareStatement(sbSelect.toString());
            
                pstm.setInt(1, (int) key);
            
              rs = pstm.executeQuery();
                if(rs.next()) result = rs.getInt(1);
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Eleicao aux = (Eleicao) value;
            int key = aux.getIdEleicao();
            Eleicao comp = this.get(key);
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
    public Eleicao get(Object key) {
        if (key == null) throw new NullPointerException("id parameter");
		
        Eleicao result = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            pstm.setInt(1, (int) key);
            
            rs = pstm.executeQuery();
            Collection c = makeObjectsFromResultSet(rs);
            if (c.size() != 1){ // throw new VotoNotFoundException("id = " + id);
            }
            
            Iterator iter = c.iterator();
            try{
                result = (Eleicao) iter.next();
            }
            catch(NoSuchElementException ex){
                result = null;
            }
        }
	catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    @Override
    public Eleicao put(Integer key, Eleicao value) {
        
        if (((int)key) < 0) throw new NullPointerException("key parameter");
        if (value==null) throw new NullPointerException("value parameter");
        
        value.setIdEleicao((int) key);
        PreparedStatement pstmIn = null;
        Date daux;
        
        try{
            StringBuilder sbInsert = new StringBuilder();
            
            sbInsert.append("INSERT INTO ");
            sbInsert.append(ELEICOES_TABELA);
            sbInsert.append(" ("+ELEICOES_ID+","+ELEICOES_DT_I+","+ELEICOES_DT_F+","+ELEICOES_DT_IC+","+ELEICOES_DT_FC+","
                    + ""+ELEICOES_DT_E+","+ELEICOES_FASE+","+ELEICOES_AUTONOMA+","+ELEICOES_TIPO+","
                    + ""+ELEICOES_VOTOS_B+","+ELEICOES_VOTOS_N+","+ELEICOES_VOTOS_A+","+ELEICOES_ESTADO+" )");
            sbInsert.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmIn = conexao.prepareStatement(sbInsert.toString());
            pstmIn.setInt(1, value.getIdEleicao());
            
            daux = asDate(value.getDt_iniFase_eleitoral());
            pstmIn.setDate(2, asSQLDate(daux));
            daux = asDate(value.getDt_fimFase_eleitoral());
            pstmIn.setDate(3,asSQLDate(daux));
            daux = asDate(value.getDt_iniCandidatura());
            pstmIn.setDate(4, asSQLDate(daux));
            daux = asDate(value.getDt_fimCandidatura());
            pstmIn.setDate(5, asSQLDate(daux));
            daux = asDate(value.getDt_eleicao());
            pstmIn.setDate(6, asSQLDate(daux));
            
            pstmIn.setInt(7, value.getFase());
            pstmIn.setInt(8, value.getRegiao_autonoma());
            pstmIn.setInt(9, value.getTipo());
            
            int[] votos = value.getResultadoVotos();
            pstmIn.setInt(10,votos[0]);
            pstmIn.setInt(11, votos[1]);
            pstmIn.setInt(12, votos[2]);
            pstmIn.setInt(13, value.getEstado());
            
            VotoDAO votoDb = new VotoDAO();
            votoDb.putAll(value.getVotos());
            
            EleitorDAO eleitorDb = new EleitorDAO();
            eleitorDb.putAll(value.getEleitores());
            
            //GestorDeEleicaoDAO adminDb = new GestorDeEleicaoDAO();
            //adminDb.putAll(value.getAdmins());
            
            //VencedorDAO vencedorDb = new VencedorDAO();
            //vencedorDb.putAll(value.getVencedores());
            
            int rows = pstmIn.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
        }
        catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstmIn != null) pstmIn.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return value;
    }

    @Override
    public Eleicao remove(Object key) {
        Eleicao res = this.get((int)key);
        if(res!=null){

            PreparedStatement pstmIn = null;
          
            try{
                StringBuilder sbInsert = new StringBuilder();
                
                sbInsert.append("DELETE FROM ");
                sbInsert.append(ELEICOES_TABELA);
                sbInsert.append(" WHERE ");
                sbInsert.append(ELEICOES_ID);
                sbInsert.append(" = ?");
        
                pstmIn = conexao.prepareStatement(sbInsert.toString());
        
                pstmIn.setInt(1, (int)key);
                
                int rows = pstmIn.executeUpdate();
                
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstmIn != null) pstmIn.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Eleicao> m) {
             
        for(Entry<? extends Integer, ? extends Eleicao> entry : m.entrySet()) 
            put(entry.getKey(),entry.getValue());
    }

    @Override
    public void clear() {
      // TRUNCATE TABLE tablename;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("TRUNCATE TABLE ");
            sbSelect.append(ELEICOES_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            int rows = pstm.executeUpdate();
        }
	catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Eleicao> values() {
       Collection res = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT * FROM ");
            sbSelect.append(ELEICOES_TABELA);
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            
            rs = pstm.executeQuery();
            res = makeObjectsFromResultSet(rs);
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res; 
    }
    
    // Unsupported Operations! 
    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, Eleicao>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void setEstadoEleicaoAbrirCandidatura(int idEleicao){
        {
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 2);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    public int getVotosBranco(int idEleicao){
          int res = -1;
            ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                sbSelect.append("SELECT ");
                sbSelect.append(ELEICOES_VOTOS_B);
                sbSelect.append(" FROM ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
                sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);

                rs = pstm.executeQuery();
                rs.next();
                res = rs.getInt(1);
                if(rs.next()) res = -1;
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res; 
    }
    
    public void setVotoBranco(int idEleicao, int votos){
        {
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_VOTOS_B);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, votos);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public void setEstadoEleicaoAprovada(int idEleicao){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 4);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEstadoEleicaoFecharCandidatura(int idEleicao){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 3);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEstadoEleicaoAbertaVotos(int idEleicao){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 5);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEstadoEleicaoEncerrada(int idEleicao){
        {
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_ESTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, 6);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public int getIdEleicaoAbertaAVotos(){
            int res = -1;
            ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                sbSelect.append("SELECT ");
                sbSelect.append(ELEICOES_ID);
                sbSelect.append(" FROM ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ESTADO);
                sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, 5);

                rs = pstm.executeQuery();
                rs.next();
                res = rs.getInt(1);
                if(rs.next()) res = -1;
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res; 

            }
    
    public int[] getVotosBrancoENulos(int idEleicao){
        int[] res = new int[] {0,0};
         ResultSet rs = null;
            PreparedStatement pstm = null;

            StringBuilder sbSelect = new StringBuilder();

            try{
                sbSelect.append("SELECT * FROM ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
                sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);

                rs = pstm.executeQuery();
                if(rs.next()){
                    res[0] = rs.getInt(ELEICOES_VOTOS_B);
                    res[1] = rs.getInt(ELEICOES_VOTOS_N);
                    if(rs.next()){
                        System.err.println("Tabela de Eleições está insconsistente!");
                    }
                }
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res; 
    }
    
    public void encerraEleicaoPutAbestencao(int idEleicao,int votosAbestencao){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_VOTOS_A);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, votosAbestencao);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void encerraEleicaoPutHistorico(int idEleicao, String historico){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_RESULTADO);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setString(1, historico);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void encerraEleicaoPutHistorico2(int idEleicao, String historico2){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("UPDATE "); sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" SET "); sbSelect.append(ELEICOES_RESULTADO2);
            sbSelect.append(" = ? "); sbSelect.append(" WHERE ");
            sbSelect.append(ELEICOES_ID); sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setString(1, historico2);
            pstm.setInt(2, idEleicao);
            
            int rows = pstm.executeUpdate();
            if (rows != 1) throw new SQLException("executeUpdate return value: "+ rows);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// ENCERRA ELEIÇAO CLEAR TABLES

    public void dropPreRequisitoPartido(){
        try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(REQUISITOS_PARTIDO_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dropPreRequisito(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(REQUISITOS_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dropPartidos(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(PARTIDOS_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dropListaCandidatos(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(LISTAC_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dropVotos(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(VOTOS_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dropLista(){
         try {
            PreparedStatement pstm = null;
            StringBuilder sbSelect = new StringBuilder();
           
            sbSelect.append("DELETE");
            sbSelect.append(" FROM "); sbSelect.append(LISTAS_TABELA);
           
            
            pstm = conexao.prepareStatement(sbSelect.toString());
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 // COLLECTION DE INTEIROS
    private Collection getIdCabecas(int idEleicao){
        Collection result = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT ");
            sbSelect.append(ELEITORES_ID);
            sbSelect.append(" FROM (");
            sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(ELEITORES_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(LISTAS_CABECA);
            sbSelect.append(" = "); sbSelect.append(ELEITORES_ID);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                result.add(rs.getInt(1));
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    
    private Collection getIdCandidatosListas(int idEleicao){
        {
        Collection result = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstm = null;
		
        StringBuilder sbSelect = new StringBuilder();
		
        try{
            sbSelect.append("SELECT DISTINCT(");
            sbSelect.append(LISTAC_CANDIDATO);
            sbSelect.append(") FROM ("); sbSelect.append(LISTAS_TABELA);
            sbSelect.append(" JOIN ");
            sbSelect.append(LISTAC_TABELA);
            sbSelect.append(" ON ");
            sbSelect.append(LISTAS_ID);
            sbSelect.append(" = "); sbSelect.append(LISTAC_LISTA_ID);
            sbSelect.append(") JOIN ");sbSelect.append(ELEICOES_TABELA);
            sbSelect.append(" ON ");sbSelect.append(LISTAS_ELEICAO);
            sbSelect.append(" = ");sbSelect.append(ELEICOES_ID);
            sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
            sbSelect.append(" = ?");
            
            pstm = conexao.prepareStatement(sbSelect.toString());
            pstm.setInt(1, idEleicao);
            
            rs = pstm.executeQuery();

            while (rs.next()){
                result.add(rs.getInt(1));
            }
        }
        catch (SQLException ex){
            Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (pstm != null) pstm.close();
                if (rs != null)rs.close();
            }
            catch (SQLException ex) {
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result; 
    }
    }
    
    // Devolve todos OS IDS DOS CANDIDATOS ENVOLVIDOS NUMA ELEICAO PARA ASSEMBLEIA
    public Collection getIdCandidatosDaEleicaoAssembleia(int idEleicao){
        Collection res = this.getIdCabecas(idEleicao);
        Collection aux = this.getIdCandidatosListas(idEleicao);
        
        for(Object id : aux){
            res.add(id);
        }
        return res;
    }
   
    // Devolve todos os ids dos candidtados envolvdidos numa eleicao para presidente
    public Collection getIdCandidatosDaEleicaoPresidencial(int idEleicao){
        return this.getIdCabecas(idEleicao);
    }
    
    
    // HISTORICO DAS ELEIÇÂO ENCERRADA
    public String getHistorico1(int idEleicao){
        String res = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
        try{
                sbSelect.append("SELECT ");sbSelect.append(ELEICOES_RESULTADO);
                sbSelect.append(" FROM ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
                sbSelect.append(" = ? AND ");sbSelect.append(ELEICOES_ESTADO);
                sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);
                pstm.setInt(2,6);

                rs = pstm.executeQuery();
                if(rs.next()){
                    res = rs.getString(1);
                    if(rs.next()){
                        System.err.println("Tabela de Eleições está insconsistente!");
                    }
                }
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res; 
    }
    
    public String getHistorico2(int idEleicao){
        String res = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        
        StringBuilder sbSelect = new StringBuilder();
        try{
                sbSelect.append("SELECT ");sbSelect.append(ELEICOES_RESULTADO2);
                sbSelect.append(" FROM ");
                sbSelect.append(ELEICOES_TABELA);
                sbSelect.append(" WHERE "); sbSelect.append(ELEICOES_ID);
                sbSelect.append(" = ?"); sbSelect.append(ELEICOES_ESTADO);
                sbSelect.append(" = ?");

                pstm = conexao.prepareStatement(sbSelect.toString());
                pstm.setInt(1, idEleicao);
                pstm.setInt(2, 6);

                rs = pstm.executeQuery();
                if(rs.next()){
                    res = rs.getString(1);
                    if(rs.next()){
                        System.err.println("Tabela de Eleições está insconsistente!");
                    }
                }
            }
            catch (SQLException ex){
                Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    if (pstm != null) pstm.close();
                    if (rs != null)rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EleicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res; 
    }
    
    
    
}

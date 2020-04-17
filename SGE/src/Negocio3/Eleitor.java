package Negocio3;

import Dados3.EleitorDAO;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Eleitor extends Utilizador  {
	private String nome;
	private LocalDate dt_nascimento;
	private CirculoEleitoral circulo;
	private Candidato candidato;
        EleitorDAO eleitorDB;
        Map<Integer,Eleitor> logins;

    public Eleitor(int id, String nome, String cartao_cidadao, LocalDate nascimento, CirculoEleitoral circulo, boolean voto, Candidato candidato){
        super(id, cartao_cidadao, false, true, voto);
        this.candidato = null;
        this.nome = nome;
        this.dt_nascimento = nascimento;
        this.circulo = circulo;
        this.candidato = candidato;
    }   
    
    public Eleitor(int id, String nome, String cartao_cidadao, LocalDate nascimento, CirculoEleitoral circulo, boolean voto) {
        super(id, cartao_cidadao, false, false, voto);
        this.candidato = new Candidato();
        this.nome = nome;
        this.dt_nascimento = nascimento;
        this.circulo = circulo;        
    }

    public Eleitor() {
        super(0, null, false, false, false);
        this.eleitorDB = new EleitorDAO();
        this.logins = new HashMap<>();
    }
    
    

 
  

    /*public Eleitor(String nome, LocalDate dt_nascimento, CirculoEleitoral circulo, Candidato candidato) {
        this.nome = nome;
        this.dt_nascimento = dt_nascimento;
        this.circulo = circulo;
        this.candidato = candidato;
    }*/

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDt_nascimento() {
		return this.dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.nome != null ) {
			lHashCode += this.nome.hashCode();
		}
		if ( this.dt_nascimento != null ) {
			lHashCode += this.dt_nascimento.hashCode();
		}
		if ( this.circulo != null ) {
			lHashCode += this.circulo.hashCode();
		}
		if ( this.candidato != null ) {
			lHashCode += this.candidato.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

        @Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Eleitor) {
			Eleitor lEleitorObject = (Eleitor) object;
			boolean lEquals = true;
			lEquals &= ((this.nome.equals(lEleitorObject.nome))
				|| (this.nome != null && this.nome.equals(lEleitorObject.nome)));
			lEquals &= ((this.dt_nascimento == lEleitorObject.dt_nascimento)
				|| (this.dt_nascimento != null && this.dt_nascimento.equals(lEleitorObject.dt_nascimento)));
			lEquals &= ((this.circulo == lEleitorObject.circulo)
				|| (this.circulo != null && this.circulo.equals(lEleitorObject.circulo)));
			lEquals &= ((this.candidato == lEleitorObject.candidato)
				|| (this.candidato != null && this.candidato.equals(lEleitorObject.candidato)));
			return lEquals;
		}
		return false;
	}

    public CirculoEleitoral getCirculo() {
        return circulo;
    }

    public void setCirculo(CirculoEleitoral circulo) {
        this.circulo = circulo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCanditato(Candidato candidato) {
        this.candidato = candidato;
    }
    
    public  int loginCandidato(String cartao_cidadao){
        int res;
        try{
            int id = eleitorDB.getIdEleitor(cartao_cidadao);
            Candidato novo = this.loginComoCandidato(id);
            res = id;
        }
        catch(Exception e){
            res = 0;
        }
        return res;
    }
    
    public Candidato loginComoCandidato(int idEleitor){
        return this.eleitorDB.loginComoCandidato(idEleitor);
    }
    
    public int getIdEleitor(String cartao_cidadao){
        EleitorDAO aux = new EleitorDAO();
        return aux.getIdEleitor(cartao_cidadao);
    }
    
    public boolean isAdmin(int idEleitor){
        return this.eleitorDB.get(idEleitor).isAdmin();
        
    }
    
    public Eleitor get(int idEleitor){
        return this.eleitorDB.get(idEleitor);
    }
    public boolean containsEleitor(Object key){
        return this.eleitorDB.containsKey(key);
    }
    
    // Login 0 - admin, 1 - candidato, 2 - eleitor
    public void login(String cartao_cidadao, int modo_acesso){
        int id = eleitorDB.getIdEleitor(cartao_cidadao);
        if (id == -1){
            System.err.println("Dados inválidos");
        }
        else {
            Eleitor eaux = eleitorDB.get(id);
            
            if (logins.containsKey(id)){
                System.err.println("Utilizador já efectou login");
            }
            else {
                if (eaux.isAdmin() && modo_acesso == 0){
                    // JANELA ADMIN
                    logins.put(id, eaux);
                } else {
                if (eaux.isCandidato() && modo_acesso == 1){
                    // JANELA CANDIDATO
                    logins.put(id, eaux);

                } else {
                   // JANELA ELEITOR
                   logins.put(id, eaux);
                    }
                }
            }
        }
    }
    
    public void logout(String cartao_cidadao){
        // candidato em processo de candidatura || eleitor em processo de voto
        logins.remove(eleitorDB.getIdEleitor(cartao_cidadao));
    }
    
    
    //Criar entrada na tabela requisitos do eleitor
    public int createPreRequisitos(int idEleitor, String req1, String req2, String req3){
        return eleitorDB.setPreRequisitos(idEleitor, req1, req2, req3);
    }
    
    //Candidato já existe
    public boolean candidatoJaExiste(int idEleitor){
        return this.eleitorDB.candidatoJaExiste(idEleitor);
    }
    
    //Retorna o boolean relactivo ao já votou do eleitor
    public boolean eleitorVotou(int idEleitor){
        return this.eleitorDB.getEleitorVoto(idEleitor);
    }
    
    //Altera o eleitor para candidato
    public void setCandidato(int idEleitor, boolean estado){
        this.eleitorDB.setCandidato(idEleitor, estado);
    }
    
    //Altera o estado de voto do Candidato
    public void setEleitorVoto(int idEleitor, boolean voto){
        this.eleitorDB.setEleitorVoto(idEleitor, voto);
    }
    
    //Altera o nome do Candidato
    public void setNomeCandidato(String Cartao_cidadao, String nome){
        this.eleitorDB.setNomeCandidato(Cartao_cidadao, nome);
    }
    
    //Altera o nome do Candidato pelo ID
    public void setNomeCandidatoID(int idEleitor, String nome){
        this.eleitorDB.setNomeCandidatoID(idEleitor, nome);
    }
    
    //Altera o circulo eleitoral do candidato
    public void setCirculoEleitoral(int idEleitor, int circulo){
        this.eleitorDB.setCirculoEleitoral(idEleitor, circulo);
    }
    
    //Altera provisoriamente o circulo eleitoral do candidato (eleicoes para regioes autonomas)
     public void setCirculoEleitoralProvisorio(int idEleitor, int idIlha){
         this.eleitorDB.setCirculoEleitoralProvisorio(idEleitor, idIlha);
     }
     
     //devolve um Objecto Eleitor
    public Eleitor getEleitor(int idEleitor){
        return this.eleitorDB.get(idEleitor);
    }
    
     // devolve o nome do eleitor
    public String getNomeEleitor(int idEleitor){
        return this.eleitorDB.getNomeEleitor(idEleitor);
    }
    
    //dado o idEleitor devolve o seu idCirculo correspondente.
     public int getCirculoEleitoralEleitor(int idEleitor){
          return this.eleitorDB.getCirculoEleitoralEleitor(idEleitor);
     }
     
     // devolve o numero de eleitors da BD
      public int numeroEleitores(){
        return this.eleitorDB.size();
      }
      
      // LIMPA VOTOS
    public void clearVotos(){
        this.eleitorDB.clearVotos();
    }
    
    public int getCirculoProvisorio(int idEleitor){
        return this.eleitorDB.getCirculoProvisorio(idEleitor);
    }
    public Collection getIdsVotou(){
        return this.eleitorDB.getIdsVotou();
    }
    public void clearCirculoProvisorio(int idEleitor){
        this.eleitorDB.clearCirculoProvisorio(idEleitor);
    }
    
    public boolean connectionIsClosed(){
        return this.eleitorDB.isClosed();
    }
    
    public int setPreRequisitos (int idEleitor, String req1, String req2, String req3){
        return this.eleitorDB.setPreRequisitos(idEleitor, req1, req2, req3);
    }
    
    public boolean getEleitorVoto(int idEleitor){
        return this.eleitorDB.getEleitorVoto(idEleitor);
    }
    
    public int size(){
        return this.eleitorDB.size();
    }
}
    
    
package Negocio3;

import Dados3.ListaDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Lista {
	private int idLista;
	private boolean estado;
	private CirculoEleitoral circulo;
	private ArrayList<Candidato> candidatos;
	private Candidato cabeça;
        ListaDAO listaDB;

    public Lista(int idLista, boolean estado, CirculoEleitoral circulo, ArrayList<Candidato> candidatos, Candidato cabeça) {
        this.idLista = idLista;
        this.estado = estado;
        this.circulo = circulo;
        this.candidatos = candidatos;
        this.cabeça = cabeça;
    }

    public Lista(int idLista, boolean estado, CirculoEleitoral circulo, Candidato cabeça) {
        this.idLista = idLista;
        this.estado = estado;
        this.circulo = circulo;
        this.cabeça = cabeça;
        this.candidatos = new ArrayList<>();
    }

    public Lista() {
        this.listaDB = new ListaDAO();
    }
 
    
        
        
        
	public int getIdLista() {
		return this.idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

        public CirculoEleitoral getCirculo() {
            return circulo;
        }

        public void setCirculo(CirculoEleitoral circulo) {
            this.circulo = circulo;
        }

        public ArrayList<Candidato> getCandidatos() {
            return candidatos;
        }

        public void setCandidatos(ArrayList<Candidato> candidatos) {
            this.candidatos = candidatos;
        }

        public Candidato getCabeça() {
            return cabeça;
        }

        public void setCabeça(Candidato cabeça) {
            this.cabeça = cabeça;
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idLista;
        hash = 89 * hash + (this.estado ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.circulo);
        hash = 89 * hash + Objects.hashCode(this.cabeça);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lista other = (Lista) obj;
        if (this.idLista != other.idLista) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.circulo, other.circulo)) {
            return false;
        }
        if (!Objects.equals(this.candidatos, other.candidatos)) {
            return false;
        }
        if (!Objects.equals(this.cabeça, other.cabeça)) {
            return false;
        }
        return true;
    }
    
    public boolean listIsNotFull(int idPartido, int idLista){
        return this.listaDB.listIsNotFull(idPartido, idLista);
    }
        
     //Size da tabela Partido
    public int sizePartido(){
        return this.listaDB.sizePartido();
    } 
    
    //Criar partido na BD map<k,v>
    public Partido putPartidoByKey(Integer key, String nome, String codigo){
        return this.listaDB.putPartidoByKey(key,nome,codigo);
    }
    
    //Retorna o ID do partido na tabela PARTIDO
    public int getIdPartido(String nomePartido){
        return this.listaDB.getIdPartido(nomePartido);
    }
    
    //Criar Pre-requisitos dos partidos
    public int createPreRequisitosPartidos(int idPartido, String req1, String req2, String req3){
        return this.listaDB.setPreRequisitosPartidos(idPartido, req1, req2, req3);
    }
    
    //Autentica partido
    public boolean autenticaPartido(int idPartido, String codigo_secreto){
        return this.listaDB.autenticaPartido(idPartido, codigo_secreto);
    }
    
    //Autentica lista
     public boolean autenticaLista(int idPartido, int idLista){
         return this.listaDB.autenticaLista(idPartido, idLista);
     }
     
     // dado o nome do circulo eleitoral devolve o id
    public int getIdCirculo(String circulo_eleitoral){
        return this.listaDB.getIdCirculo(circulo_eleitoral);
    }
    
    // adiciona uma entrada na lista
    public void putLista(int idLista, int idPartido, int idCabeca, boolean estado, int idEleicao){
        this.listaDB.putLista(idLista, idPartido, idCabeca, estado, idEleicao);
    }
    
     // devolve -1 se houver erro
    public int sizeLista(int idCabeca){
        return this.listaDB.sizeLista(idCabeca);
    }
    
     // Usada para fazer String no jList
    public Collection listaAbertas(int idPartido){
        return this.listaDB.listaAbertas(idPartido);
    }
    
    //adicionar candiato a uma lista de um dado partido
    public int adicionarCandidatoLista(int idCandidato, int idLista, int idPartido){
        return this.listaDB.adicionarCandidatoLista(idCandidato, idLista, idPartido);
    }
    
    // altera o estado de uma, e apenas uma lista. 
    public void setListaAprovada(int idLista, boolean estado){
        this.listaDB.setListaAprovada(idLista, estado);
    }
    // aprova TODO um partido, ou seja, todas as suas listas
    public void aprovaPartido(int idEleicao, int idPartido){
        this.listaDB.aprovaPartido(idEleicao, idPartido);
    }
    
    // dado o nome do candidato devolve o seu idEleitor para voto correspondente
    public int getIdCandidatoParaVoto(String nomeCandidato){
        return this.listaDB.getIdCandidatoParaVoto(nomeCandidato);
    }
    
    // devolve a collection<int> ids das listas de um partido
    public Collection getIdListasPartidos(int idEleicao, int idPartido){
        return this.listaDB.getIdListasPartidos(idEleicao, idPartido);
    }
    
    public int getIdListaParaVoto(int idEleicao, String partido, int idCirculo){
          return this.listaDB.getIdListaParaVoto(idEleicao, partido, idCirculo);
      }
    
     // Nome do candidato
    public String getNomeCandidato(int idCandidato){
        return this.listaDB.getNomeCandidato(idCandidato);
    }
    
    //Nome do partido
    public String getNomePartido(int idPartido){
        return this.listaDB.getNomePartido(idPartido);
    }
    
    //Nome do circulo
    public String geNomeCirculo(int idCirculo){
        return this.listaDB.geNomeCirculo(idCirculo);
    }
    
    // Devolve o numero de partitods para uma dada eleicao
      public int getNumeroPartidos(int idEleicao){
          return this.listaDB.getNumeroPartidos(idEleicao);
      }
      
      public int setPreRequisitosPartidos(int idPartido, String req1, String req2, String req3){
          return this.listaDB.setPreRequisitos(idPartido, req1, req2, req3);
      }
      
      // aprova o candidato a uma eleição nominal, apenas ele e a sua MONO LISTA!
    public void aprovaCandidato(int idEleicao, int idCandidato){
        // getListaPartidos serve igual aki! nao é typo error
        this.setListaAprovada(idCandidato, true);
    }
    
    public boolean partidoJaExiste(String nomePartido){
        return this.listaDB.partidoJaExiste(nomePartido);
    }
}
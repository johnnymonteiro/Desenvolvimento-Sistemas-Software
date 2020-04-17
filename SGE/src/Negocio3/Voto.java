package Negocio3;

import Dados3.VotoDAO;
import java.util.HashMap;
import java.util.Map;

public class Voto{
	private int idVoto;
	private int idEleicao;
	private int idLista;
	private Lista lista;
        VotoDAO votoDB;
        
        public Voto(int idVoto, int idEleicao, int idLista){
        this.idVoto = idVoto;
        this.idEleicao = idEleicao;
        this.idLista = idLista;
        this.lista = null;
        this.votoDB = new VotoDAO();
    }

    public Voto() {
         this.votoDB = new VotoDAO();
    }

        
	public int getIdVoto() {
		return this.idVoto;
	}

	public void setIdVoto(int idVoto) {
		this.idVoto = idVoto;
	}

	public int getIdEleicao() {
		return this.idEleicao;
	}

	public void setIdEleicao(int idEleicao) {
		this.idEleicao = idEleicao;
	}

	public int getIdLista() {
		return this.idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

          

    

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
        
        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.lista != null ) {
			lHashCode += this.lista.hashCode();
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
		} else if (object instanceof Voto) {
			Voto lVotoObject = (Voto) object;
			boolean lEquals = true;
			lEquals &= this.idVoto == lVotoObject.idVoto;
			lEquals &= this.idEleicao == lVotoObject.idEleicao;
			lEquals &= this.idLista == lVotoObject.idLista;
			lEquals &= ((this.lista == lVotoObject.lista)
				|| (this.lista != null && this.lista.equals(lVotoObject.lista)));
			return lEquals;
		}
		return false;
	}
        
        //voto para DB
     public int putVoto(int idEleicao, int idLista){
         return this.votoDB.putVoto(idEleicao,idLista);
     }
     
      //RESULTADOS
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaRepublica(int idEleicao){
        return this.resultadosAssembleiaRepublica(idEleicao);
    }
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaAçores(int idEleicao){
        return this.resultadosAssembleiaAçores(idEleicao);
    }
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaMadeira(int idEleicao){
        return this.resultadosAssembleiaMadeira(idEleicao);
    }
    
    //     IDCANDIDATO  VOTOS
    public Map<Integer,Integer> getResultadosPresidencial(int idEleicao){
        return this.resultadosPresidencial(idEleicao);
    }
    
    // devolve o numero de votos efectuados numa eleicao, e que nao sao nulos nem em branco!
      public int votosEfectuadosNaEleicao(int idEleicao){
          return this.votoDB.votosEfectuadosNaEleicao(idEleicao);
      }
      
      // devolve o numero de votos efectuados numa determinada lista, sem considerar nulos e branco!
      public int votosEfectuadosNaLista(int idLista){
          return this.votoDB.votosEfectuadosNaLista(idLista);
      }
      
      
      public Map<Integer,Integer> resultadosPresidencial(int idEleicao){
          return this.votoDB.resultadosPresidencial(idEleicao);
      }
      
      
     public Map<Integer,Map<Integer,Integer>> resultadosAssembleiaRepublica(int idEleicao){
        double[]votes;
        int[] party;
        int nParty;
        int j=0;
        // integer -> circulo , int[]-> resultado de hondt
        //Map<Integer,int[]> resultado = new HashMap<>();
       
        Map<Integer,Integer> resultadosDoCirculo;
        Map<Integer,Map<Integer,Integer>> resultado = new HashMap<>();
        
        //percorrer os circulos
        for(int i=1; i<=20;i++){
            //double []votes =  new double[]  {12000, 7500, 4500, 3000}; -> DOUBLE
            //String[] party = {"A","B","C","D"}; -> INTEGER
          
            Map<Integer,Double> votosPorCirculo = this.votoDB.votosPorListaPorCirculo(i,idEleicao);
            votes = new double[votosPorCirculo.size()];
            party = new int[votosPorCirculo.size()];
            nParty = votosPorCirculo.size();
            j=0;
            for(Map.Entry<Integer,Double> entry : votosPorCirculo.entrySet()){
                votes[j] = entry.getValue();
                party[j] = entry.getKey();
                j++;
            }
            
            int[]results = this.votoDB.resultados(votes,this.votoDB.getMaxDeputadosCirculo(i));
            
            resultadosDoCirculo = new HashMap<>();
            for(int k=0; k<nParty; k++){
                resultadosDoCirculo.put(party[k] ,results[k]);
            }
            
            resultado.put(i, resultadosDoCirculo);
            
            
            }
       
        return resultado;
    }
    
    public Map<Integer,Map<Integer,Integer>> resultadosAssembleiaAçores(int idEleicao){
        double[]votes;
        int[] party;
        int nParty;
        int j=0;
        // integer -> circulo , int[]-> resultado de hondt
        //Map<Integer,int[]> resultado = new HashMap<>();
       
        Map<Integer,Integer> resultadosDoCirculo;
        Map<Integer,Map<Integer,Integer>> resultado = new HashMap<>();
        
        //percorrer os circulos
        for(int i=22; i<=30;i++){
            //double []votes =  new double[]  {12000, 7500, 4500, 3000}; -> DOUBLE
            //String[] party = {"A","B","C","D"}; -> INTEGER
          
            Map<Integer,Double> votosPorCirculo = this.votoDB.votosPorListaPorCirculo(i,idEleicao);
            votes = new double[votosPorCirculo.size()];
            party = new int[votosPorCirculo.size()];
            nParty = votosPorCirculo.size();
            j=0;
            for(Map.Entry<Integer,Double> entry : votosPorCirculo.entrySet()){
                votes[j] = entry.getValue();
                party[j] = entry.getKey();
                j++;
            }
            
            int[]results = this.votoDB.resultados(votes,this.votoDB.getMaxDeputadosCirculo(i));
            
            resultadosDoCirculo = new HashMap<>();
            for(int k=0; k<nParty; k++){
                resultadosDoCirculo.put(party[k] ,results[k]);
            }
            
            resultado.put(i, resultadosDoCirculo);
            
            
            }
       
        return resultado;
    }
    
    public Map<Integer,Map<Integer,Integer>> resultadosAssembleiaMadeira(int idEleicao){
        double[]votes;
        int[] party;
        int nParty;
        int j=0;
        // integer -> circulo , int[]-> resultado de hondt
        //Map<Integer,int[]> resultado = new HashMap<>();
       
        Map<Integer,Integer> resultadosDoCirculo;
        Map<Integer,Map<Integer,Integer>> resultado = new HashMap<>();
        
        //percorrer os circulos
        for(int i=21; i<=21;i++){
            //double []votes =  new double[]  {12000, 7500, 4500, 3000}; -> DOUBLE
            //String[] party = {"A","B","C","D"}; -> INTEGER
          
            Map<Integer,Double> votosPorCirculo = this.votoDB.votosPorListaPorCirculo(i,idEleicao);
            votes = new double[votosPorCirculo.size()];
            party = new int[votosPorCirculo.size()];
            nParty = votosPorCirculo.size();
            j=0;
            for(Map.Entry<Integer,Double> entry : votosPorCirculo.entrySet()){
                votes[j] = entry.getValue();
                party[j] = entry.getKey();
                j++;
            }
            
            int[]results = this.votoDB.resultados(votes,this.votoDB.getMaxDeputadosCirculo(i));
            
            resultadosDoCirculo = new HashMap<>();
            for(int k=0; k<nParty; k++){
                resultadosDoCirculo.put(party[k] ,results[k]);
            }
            
            resultado.put(i, resultadosDoCirculo);
            
            
            }
       
        return resultado;
    }
    
}
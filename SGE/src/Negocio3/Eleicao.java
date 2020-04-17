package Negocio3;

import Dados3.EleicaoDAO;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Eleicao {
	private int idEleicao;
	private LocalDate dt_iniFase_eleitoral;
	private LocalDate dt_fimFase_eleitoral;
	private LocalDate dt_iniCandidatura;
	private LocalDate dt_fimCandidatura;
	private LocalDate dt_eleicao;
	private int fase;
	private int regiao_autonoma;
	private int tipo;
	private int[] resultadoVotos;
	private Map<Integer,Vencedor> vencedores;
	private Map<Integer,Eleitor> eleitores;
	private Map<Integer,Voto> votos;
        private int estado;
        EleicaoDAO eleicaoDB;
     
    public Eleicao(int idEleicao, LocalDate dt_iniFase_eleitoral, LocalDate dt_fimFase_eleitoral, LocalDate dt_iniCandidatura, LocalDate dt_fimCandidatura, LocalDate dt_eleicao, int fase, int regiao_autonoma, int tipo, int[] resultadoVotos, Map<Integer, Vencedor> vencedores, Map<Integer, Eleitor> eleitores, Map<Integer, Voto> votos, int estado) {
        this.idEleicao = idEleicao;
        this.dt_iniFase_eleitoral = dt_iniFase_eleitoral;
        this.dt_fimFase_eleitoral = dt_fimFase_eleitoral;
        this.dt_iniCandidatura = dt_iniCandidatura;
        this.dt_fimCandidatura = dt_fimCandidatura;
        this.dt_eleicao = dt_eleicao;
        this.fase = fase;
        this.regiao_autonoma = regiao_autonoma;
        this.tipo = tipo;
        this.resultadoVotos = resultadoVotos;
        this.vencedores = vencedores;
        this.eleitores = eleitores;
        this.votos = votos;
        this.estado = estado;
    }

    public Eleicao() {
        this.eleicaoDB = new EleicaoDAO();
    }
    
    

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }
        
        public int getIdEleicao() {
		return this.idEleicao;
	}

	public void setIdEleicao(int idEleicao) {
		this.idEleicao = idEleicao;
	}

	public LocalDate getDt_iniFase_eleitoral() {
		return this.dt_iniFase_eleitoral;
	}

	public void setDt_iniFase_eleitoral(LocalDate dt_iniFase_eleitoral) {
		this.dt_iniFase_eleitoral = dt_iniFase_eleitoral;
	}

	public LocalDate getDt_fimFase_eleitoral() {
		return this.dt_fimFase_eleitoral;
	}

	public void setDt_fimFase_eleitoral(LocalDate dt_fimFase_eleitoral) {
		this.dt_fimFase_eleitoral = dt_fimFase_eleitoral;
	}

	public LocalDate getDt_iniCandidatura() {
		return this.dt_iniCandidatura;
	}

	public void setDt_iniCandidatura(LocalDate dt_iniCandidatura) {
		this.dt_iniCandidatura = dt_iniCandidatura;
	}

	public LocalDate getDt_fimCandidatura() {
		return this.dt_fimCandidatura;
	}

	public void setDt_fimCandidatura(LocalDate dt_fimCandidatura) {
		this.dt_fimCandidatura = dt_fimCandidatura;
	}

	public LocalDate getDt_eleicao() {
		return this.dt_eleicao;
	}

	public void setDt_eleicao(LocalDate dt_eleicao) {
		this.dt_eleicao = dt_eleicao;
	}

	public int getFase() {
		return this.fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getRegiao_autonoma() {
		return this.regiao_autonoma;
	}

	public void setRegiao_autonoma(int regiao_autonoma) {
		this.regiao_autonoma = regiao_autonoma;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int[] getResultadoVotos() {
		return this.resultadoVotos;
	}

	public void setResultadoVotos(int[] resultadoVotos) {
		this.resultadoVotos = resultadoVotos;
	}

        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.dt_iniFase_eleitoral != null ) {
			lHashCode += this.dt_iniFase_eleitoral.hashCode();
		}
		if ( this.dt_fimFase_eleitoral != null ) {
			lHashCode += this.dt_fimFase_eleitoral.hashCode();
		}
		if ( this.dt_iniCandidatura != null ) {
			lHashCode += this.dt_iniCandidatura.hashCode();
		}
		if ( this.dt_fimCandidatura != null ) {
			lHashCode += this.dt_fimCandidatura.hashCode();
		}
		if ( this.dt_eleicao != null ) {
			lHashCode += this.dt_eleicao.hashCode();
		}
		if ( this.vencedores != null ) {
			lHashCode += this.vencedores.hashCode();
		}
		if ( this.eleitores != null ) {
			lHashCode += this.eleitores.hashCode();
		}
		if ( this.votos != null ) {
			lHashCode += this.votos.hashCode();
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
		} else if (object instanceof Eleicao) {
			Eleicao lEleicaoObject = (Eleicao) object;
			boolean lEquals = true;
			lEquals &= this.idEleicao == lEleicaoObject.idEleicao;
			lEquals &= ((this.dt_iniFase_eleitoral == lEleicaoObject.dt_iniFase_eleitoral)
				|| (this.dt_iniFase_eleitoral != null && this.dt_iniFase_eleitoral.equals(lEleicaoObject.dt_iniFase_eleitoral)));
			lEquals &= ((this.dt_fimFase_eleitoral == lEleicaoObject.dt_fimFase_eleitoral)
				|| (this.dt_fimFase_eleitoral != null && this.dt_fimFase_eleitoral.equals(lEleicaoObject.dt_fimFase_eleitoral)));
			lEquals &= ((this.dt_iniCandidatura == lEleicaoObject.dt_iniCandidatura)
				|| (this.dt_iniCandidatura != null && this.dt_iniCandidatura.equals(lEleicaoObject.dt_iniCandidatura)));
			lEquals &= ((this.dt_fimCandidatura == lEleicaoObject.dt_fimCandidatura)
				|| (this.dt_fimCandidatura != null && this.dt_fimCandidatura.equals(lEleicaoObject.dt_fimCandidatura)));
			lEquals &= ((this.dt_eleicao == lEleicaoObject.dt_eleicao)
				|| (this.dt_eleicao != null && this.dt_eleicao.equals(lEleicaoObject.dt_eleicao)));
			lEquals &= this.fase == lEleicaoObject.fase;
			lEquals &= this.regiao_autonoma == lEleicaoObject.regiao_autonoma;
			lEquals &= this.tipo == lEleicaoObject.tipo;
			lEquals &= this.resultadoVotos == lEleicaoObject.resultadoVotos;
			lEquals &= ((this.vencedores == lEleicaoObject.vencedores)
				|| (this.vencedores != null && this.vencedores.equals(lEleicaoObject.vencedores)));
			lEquals &= ((this.eleitores == lEleicaoObject.eleitores)
				|| (this.eleitores != null && this.eleitores.equals(lEleicaoObject.eleitores)));
			lEquals &= ((this.votos == lEleicaoObject.votos)
				|| (this.votos != null && this.votos.equals(lEleicaoObject.votos)));
			return lEquals;
		}
		return false;
        }

    public Map<Integer,Vencedor> getVencedores() {
        return vencedores;
    }

    public void setVencedores(Map<Integer,Vencedor> vencedores) {
        this.vencedores = vencedores;
    }

 

    public Map<Integer,Eleitor> getEleitores() {
        return eleitores;
    }

    public void setEleitores(Map<Integer,Eleitor> eleitores) {
        this.eleitores = eleitores;
    }

    public Map<Integer,Voto> getVotos() {
        return votos;
    }

    public void setVotos(Map<Integer,Voto> votos) {
        this.votos = votos;
    }
    
    //Criar Lista Eleicoes com base no criterio de dadas fornecido(eleicoes encerradas)
    public Collection eleicoesHistoricoData(int tipo, Date dt_inicio, Date dt_fim){
        return this.eleicaoDB.eleicoesHistoricoData(tipo,dt_inicio,dt_fim);
    }
    
    //Criar Lista Eleicoes novinhas(recém criadas e com período de candidatura aberta)
    public Collection eleicoesRecemCriadas(){
        return this.eleicaoDB.eleicoesRecemCriadas();
    }
    
    //Criar Lista Eleicoes com periodo candidatura em aberto
    public Collection eleicoesAbertas(){
        return this.eleicaoDB.eleicoesAberta();
    }
    
    // Usada para fazer String no jList -> ELEICOES ABERTA A VOTOS
    public Collection eleicoesAbertaAVotos(){
        return this.eleicaoDB.eleicoesAbertaAVotos();
    }
    
    //Criar Lista de Eleicoes com periodo de candidatura fechado
    public Collection eleicoesFechadaCandidatura(){
        return this.eleicaoDB.eleicoesFechadaCandidatura();
    }
    
    // Usada para fazer String no jList para fornecer Candidatos
    public Collection getListaCandidatos(int idEleicao){
        return this.eleicaoDB.getListaCandidatos(idEleicao);
    }
    
    //Criar Lista de Partidos de uma dada eleicao
    public Collection getListaPartidos(int idEleicao){
        return this.eleicaoDB.getListaPartidos(idEleicao);
    }
    
    //Collection apenas dos nomes dos Partidos para eleicao em aberto
    public Collection getListaPartidosEleicaoVotos(){
        return this.eleicaoDB.getListaPartidosEleicaoVotos();
    }
     

    //Collection apenas dos nomes dos candidatos nominais para eleicao em aberto
    public Collection getListaNominalEleicaoVotos(){
        return this.eleicaoDB.getListaNominalEleicaoVotos();
    }
    //devolve o int (id tipo) correspondente da eleicao
    public int getTipoEleicao(int idEleicao){
         return this.eleicaoDB.getTipoEleicao(idEleicao);
     } 
     
     //Collection para eleiçoes "prontas" para votos, ou seja, prontos a iniciar
    public Collection eleicoesProntoAVoto(){
        return this.eleicaoDB.eleicoesProntaAVoto();
    }
    
    // dá o id da eleicao em aberto -1 se não houver APENAS UMA ELEICAO em aberto
    public int idEleicaoAberta(){
        return this.eleicaoDB.idEleicaoAberta();
    }
    
    //retorna o id da eleicão aberta a votos e -1 caso contrario.
    public int idEleicaoAbertaAVtoto(){
        return this.eleicaoDB.idEleicaoAbertaAVtoto();
    }
    
    //passar o estado de uma eleição para, recém criada, para candidatura aberta
    public void setEstadoEleicaoAbrirCandidatura(int idEleicao){
        this.eleicaoDB.setEstadoEleicaoAbrirCandidatura(idEleicao);
    }
    
    // passar o estado de uma eleiçao, fechada na candidatura, de não validada
    // para validada.
    public void setEstadoEleicaoAprovada(int idEleicao){
        this.eleicaoDB.setEstadoEleicaoAprovada(idEleicao);
    }
    
     // passar o estado de uma eleiçao, aberta na candidatura, para fechada
     public void setEstadoEleicaoFecharCandidatura(int idEleicao){
         this.eleicaoDB.setEstadoEleicaoFecharCandidatura(idEleicao);
     }
     
     // passar o estado de uma eleiçao, aberta na candidatura, para fechada
     public void setEstadoEleicaoAbertaVotos(int idEleicao){
         this.eleicaoDB.setEstadoEleicaoAbertaVotos(idEleicao);
     }
     // passar o estado de uma eleicao, aberta a votos, para encerrad
     public void setEstadoEleicaoEncerrada(int idEleicao){
         this.eleicaoDB.setEstadoEleicaoEncerrada(idEleicao);
     }
     
      //devolve o numero de eleicoes com estad == 1, ou seja, recem criadas
    public int quantasEleicoesPeriodoCandidaturaAberta(){
        return this.eleicaoDB.quantasEleicoesPeriodoCandidaturaAberta();
    }
    
    //GERA RESULTADOS
     public int[] getVotosBrancoENulos(int idEleicao){
        return this.eleicaoDB.getVotosBrancoENulos(idEleicao);
    }
     
     // guarda o resultado da eleição (no caso da Assembleia é o TOTAL)
      public void encerraEleicaoPutHistorico(int idEleicao, String historico){
          this.eleicaoDB.encerraEleicaoPutHistorico(idEleicao, historico);
      }
      // guarda o resultado da eleiçao por circulo eleitoral 
       public void encerraEleicaoPutHistorico2(int idEleicao, String historico2){
          this.eleicaoDB.encerraEleicaoPutHistorico2(idEleicao, historico2);
       }
      
      // guarda o numero de eleitores que não votarem nesta eleição
      public void encerraEleicaoPutAbstencao(int idEleicao, int abstencao){
          this.eleicaoDB.encerraEleicaoPutAbestencao(idEleicao, abstencao);
      }
      
       // CLEAR TABLES
    public void dropPreRequisitoPartido(){
        this.eleicaoDB.dropPreRequisitoPartido();
    }
    public void dropPreRequisito(){
        this.eleicaoDB.dropPreRequisito();
    }
    public void dropPartidos(){
        this.eleicaoDB.dropPartidos();
    }
    public void dropListaCandidatos(){
        this.eleicaoDB.dropListaCandidatos();
    }
    public void dropVotos(){
        this.eleicaoDB.dropVotos();
    }
    public void dropLista(){
        this.eleicaoDB.dropLista();
    }
    
    // IDs Candidatos
    public Collection getIdCandidatosDaEleicaoAssembleia(int idEleicao){
        return this.eleicaoDB.getIdCandidatosDaEleicaoAssembleia(idEleicao);
    }
    public Collection getIdCandidatosDaEleicaoPresidencial(int idEleicao){
        return this.eleicaoDB.getIdCandidatosDaEleicaoPresidencial(idEleicao);
    }
    
    public String getHistoricoAssembleiaTotal(int idEleicao){
        return this.eleicaoDB.getHistorico1(idEleicao);
    }
    
    public String getHistoricoAssembleiaPorCirculo(int idEleicao){
        return this.eleicaoDB.getHistorico2(idEleicao);
    }
    
    public String getHistoricoPresidencial(int idEleicao){
        return this.eleicaoDB.getHistorico1(idEleicao);
    }
    
    public int size(){
        return this.eleicaoDB.size();
    }
    
    public Eleicao put(int id, Eleicao ele){
        return this.eleicaoDB.put(id,ele);
    }
    
    public void encerraEleicaoPutAbestencao(int idEleicao,int votosAbstencao){
         this.eleicaoDB.encerraEleicaoPutAbestencao(idEleicao, votosAbstencao);
    }
    
    public String getHistorico1(int idEleicao){
        return this.eleicaoDB.getHistorico1(idEleicao);
    }
    
    public String getHistorico2(int idEleicao){
        return this.eleicaoDB.getHistorico2(idEleicao);
    }
    
    public void setVotoBranco(int idEleicao){
        int votos = this.eleicaoDB.getVotosBranco(idEleicao);
        votos++;
        this.eleicaoDB.setVotoBranco(idEleicao,votos);
    }
    // Usada para obter IDs das eleicoesAbertas
    public Collection eleicoesAbertaID(){
        return this.eleicaoDB.eleicoesAbertaID();
    }
    // Usada para obter IDs das eleições com periodo de candidatura fechada
    public Collection eleicoesFechadaCandidaturaID(){
        return this.eleicaoDB.eleicoesFechadaCandidaturaID();
    }
    // Usada para obter IDs das eleições com periodo de candidatura fechada e validadas
    public Collection eleicoesProntaAVotoID(){
        return this.eleicaoDB.eleicoesProntaAVotoID();
    }
    // Usada para obter IDs das eleições recem-criadas
    public Collection eleicoesRecemCriadasID(){
        return this.eleicaoDB.eleicoesRecemCriadasID();
    }
    // Usada para fazer String no jList ADMIN 
    public Collection eleicoesAdmin(){
        return this.eleicaoDB.eleicoesAdmin();
    }
    
}
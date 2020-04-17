package Negocio3;

import java.time.LocalDate;

public class Utilizador {
	private int id;
	private LocalDate dt_inicio;
	private LocalDate dt_fim;
	private String cartao_cidadao;
	private boolean admin;
	private boolean candidato;
        private boolean voto;

    public Utilizador(int id, String cartao_cidadao, boolean admin, boolean candidato, boolean voto) {
        this.id = id;
        this.dt_inicio = LocalDate.now();
        this.dt_fim = LocalDate.now();
        this.cartao_cidadao = cartao_cidadao;
        this.admin = admin;
        this.candidato = candidato;
        this.voto = voto;
    }


        
        
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDt_inicio() {
		return this.dt_inicio;
	}

	public void setDt_inicio(LocalDate dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public LocalDate getDt_fim() {
		return this.dt_fim;
	}

	public void setDt_fim(LocalDate dt_fim) {
		this.dt_fim = dt_fim;
	}

	public String getCartao_cidadao() {
		return this.cartao_cidadao;
	}

	public void setCartao_cidadao(String cartao_cidadao) {
		this.cartao_cidadao = cartao_cidadao;
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean getVoto() {
		return this.voto;
	}

	public void setVoto(boolean voto) {
		this.voto = voto;
	}

	public boolean isCandidato() {
		return this.candidato;
	}

	public void setCandidato(boolean candidato) {
		this.candidato = candidato;
	}
        
	public int hashCode() {
		int lHashCode = 0;
		if ( this.dt_inicio != null ) {
			lHashCode += this.dt_inicio.hashCode();
		}
		if ( this.dt_fim != null ) {
			lHashCode += this.dt_fim.hashCode();
		}
		if ( this.cartao_cidadao != null ) {
			lHashCode += this.cartao_cidadao.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

        
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Utilizador) {
			Utilizador lUtilizadorObject = (Utilizador) object;
			boolean lEquals = true;
			lEquals &= this.id == lUtilizadorObject.id;
			lEquals &= ((this.dt_inicio == lUtilizadorObject.dt_inicio)
				|| (this.dt_inicio != null && this.dt_inicio.equals(lUtilizadorObject.dt_inicio)));
			lEquals &= ((this.dt_fim == lUtilizadorObject.dt_fim)
				|| (this.dt_fim != null && this.dt_fim.equals(lUtilizadorObject.dt_fim)));
			lEquals &= ((this.cartao_cidadao == lUtilizadorObject.cartao_cidadao)
				|| (this.cartao_cidadao != null && this.cartao_cidadao.equals(lUtilizadorObject.cartao_cidadao)));
			lEquals &= this.admin == lUtilizadorObject.admin;
			lEquals &= this.voto == lUtilizadorObject.voto;
			lEquals &= this.candidato == lUtilizadorObject.candidato;
			return lEquals;
		}
		return false;
	}
}
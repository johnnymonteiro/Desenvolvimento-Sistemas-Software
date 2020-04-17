package Negocio3;

public class CirculoEleitoral {
	private int idCirculo;
	private String designacao;
	private int deputados;

    public CirculoEleitoral() {
        this.idCirculo = 0;
        this.designacao = "FAILED";
        this.deputados = 0;
    }

    
    public CirculoEleitoral(int idCirculo, String designacao, int deputados) {
        this.idCirculo = idCirculo;
        this.designacao = designacao;
        this.deputados = deputados;
    }

        
	public int getIdCirculo() {
		return this.idCirculo;
	}

	public void setIdCirculo(int idCirculo) {
		this.idCirculo = idCirculo;
	}

	public String getDesignacao() {
		return this.designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public int getDeputados() {
		return this.deputados;
	}

	public void setDeputados(int deputados) {
		this.deputados = deputados;
	}

        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.designacao != null ) {
			lHashCode += this.designacao.hashCode();
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
		} else if (object instanceof CirculoEleitoral) {
			CirculoEleitoral lCirculoEleitoralObject = (CirculoEleitoral) object;
			boolean lEquals = true;
			lEquals &= this.idCirculo == lCirculoEleitoralObject.idCirculo;
			lEquals &= ((this.designacao == lCirculoEleitoralObject.designacao)
				|| (this.designacao != null && this.designacao.equals(lCirculoEleitoralObject.designacao)));
			lEquals &= this.deputados == lCirculoEleitoralObject.deputados;
			return lEquals;
		}
		return false;
	}
}
package Negocio3;

import java.util.ArrayList;

public class Partido {
	private int idPartido;
	private String codigo_secreto;
	private String designacao;
	private ArrayList<Lista> listas;

        
    public Partido(int idPartido, String codigo_secreto, String designacao) {
        this.idPartido = idPartido;
        this.codigo_secreto = codigo_secreto;
        this.designacao = designacao;
        this.listas = new ArrayList<>();
    }

    public Partido(int idPartido, String codigo_secreto, String designacao, ArrayList<Lista> listas) {
        this.idPartido = idPartido;
        this.codigo_secreto = codigo_secreto;
        this.designacao = designacao;
        this.listas = listas;
    }
    
           
	public int getIdPartido() {
		return this.idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getCodigo_secreto() {
		return this.codigo_secreto;
	}

	public void setCodigo_secreto(String codigo_secreto) {
		this.codigo_secreto = codigo_secreto;
	}

	public String getDesignacao() {
		return this.designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.codigo_secreto != null ) {
			lHashCode += this.codigo_secreto.hashCode();
		}
		if ( this.designacao != null ) {
			lHashCode += this.designacao.hashCode();
		}
		if ( this.listas != null ) {
			lHashCode += this.listas.hashCode();
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
		} else if (object instanceof Partido) {
			Partido lPartidoObject = (Partido) object;
			boolean lEquals = true;
			lEquals &= this.idPartido == lPartidoObject.idPartido;
			lEquals &= ((this.codigo_secreto.equals(lPartidoObject.codigo_secreto))
				|| (this.codigo_secreto != null && this.codigo_secreto.equals(lPartidoObject.codigo_secreto)));
			lEquals &= ((this.designacao.equals(lPartidoObject.designacao))
				|| (this.designacao != null && this.designacao.equals(lPartidoObject.designacao)));
			lEquals &= ((this.listas == lPartidoObject.listas)
				|| (this.listas != null && this.listas.equals(lPartidoObject.listas)));
			return lEquals;
		}
		return false;
	}

    public ArrayList<Lista> getListas() {
        return listas;
    }

    public void setListas(ArrayList<Lista> listas) {
        this.listas = listas;
    }
}
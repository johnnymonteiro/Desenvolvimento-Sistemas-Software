package Negocio3;

import java.util.ArrayList;

public class Vencedor {
	private int idVencedor;
	private float percentil;
	private String cargo;
	private ArrayList<Candidato> candidatos = new ArrayList<Candidato>();

	public int getIdVencedor() {
		return this.idVencedor;
	}

	public void setIdVencedor(int idVencedor) {
		this.idVencedor = idVencedor;
	}

	public float getPercentil() {
		return this.percentil;
	}

	public void setPercentil(float percentil) {
		this.percentil = percentil;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.cargo != null ) {
			lHashCode += this.cargo.hashCode();
		}
		if ( this.candidatos != null ) {
			lHashCode += this.candidatos.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Vencedor) {
			Vencedor lVencedorObject = (Vencedor) object;
			boolean lEquals = true;
			lEquals &= this.idVencedor == lVencedorObject.idVencedor;
			lEquals &= this.percentil == lVencedorObject.percentil;
			lEquals &= ((this.cargo == lVencedorObject.cargo)
				|| (this.cargo != null && this.cargo.equals(lVencedorObject.cargo)));
			lEquals &= ((this.candidatos == lVencedorObject.candidatos)
				|| (this.candidatos != null && this.candidatos.equals(lVencedorObject.candidatos)));
			return lEquals;
		}
		return false;
	}
}
package Negocio3;

public class Candidato {
	private String nome;
	private boolean estado;

    public Candidato() {
        this.nome = "ELEITOR";
        this.estado = false;
    }

    public Candidato(String nome) {
        this.nome = nome;
        this.estado = false;
    }
    

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

        @Override
	public int hashCode() {
		int lHashCode = 0;
		if ( this.nome != null ) {
			lHashCode += this.nome.hashCode();
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
		} else if (object instanceof Candidato) {
			Candidato lCandidatoObject = (Candidato) object;
			boolean lEquals = true;
			lEquals &= ((this.nome.equals(lCandidatoObject.nome))
				|| (this.nome != null && this.nome.equals(lCandidatoObject.nome)));
			lEquals &= this.estado == lCandidatoObject.estado;
			return lEquals;
		}
		return false;
	}
}
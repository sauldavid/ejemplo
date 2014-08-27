
public class lista {
	String etiqueta ="",instruccion = "",operando = "",comentario="";

	public lista(String etiqueta, String instruccion, String operando,
			String comentario) {
		super();
		this.etiqueta = etiqueta;
		this.instruccion = instruccion;
		this.operando = operando;
		this.comentario = comentario;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	public String getOperando() {
		return operando;
	}

	public void setOperando(String operando) {
		this.operando = operando;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}

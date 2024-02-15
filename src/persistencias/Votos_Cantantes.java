package persistencias;

public class Votos_Cantantes {
	private String nombre;
	private String pais;
	private int votos;
	
	public Votos_Cantantes(String nombre, String pais, int votos) {
		this.nombre = nombre;
		this.pais = pais;
		this.votos = votos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getVotos() {
		return votos;
	}
	public void setVotos(int votos) {
		this.votos = votos;
	}
	@Override
	public String toString() {
		return "Votos_Cantantes [nombre=" + nombre + ", pais=" + pais + ", votos=" + votos + "]";
	}
	
}

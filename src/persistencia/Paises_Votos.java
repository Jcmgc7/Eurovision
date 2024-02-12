package persistencia;

public class Paises_Votos {
	public String nombre;
	public int votos;
	public Paises_Votos(String nombre, int votos) {
		this.nombre = nombre;
		this.votos = votos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getVotos() {
		return votos;
	}
	public void setVotos(int votos) {
		this.votos = votos;
	}
	@Override
	public String toString() {
		return "Paises_Votos [nombre=" + nombre + ", votos=" + votos + "]";
	}
}

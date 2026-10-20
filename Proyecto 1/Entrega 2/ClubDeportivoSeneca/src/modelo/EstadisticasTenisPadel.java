package modelo;

public class EstadisticasTenisPadel extends EstadisticasIndividual {

	private static final long serialVersionUID = 1L;

	private int sets;

	private int juegosGanados;

	private int aces;

	private int erroresNoForzados;

	public EstadisticasTenisPadel(AbstractPartido partido, RolJugador jugador) {
		super(partido, jugador);
		this.sets = 0;
		this.juegosGanados = 0;
		this.aces = 0;
		this.erroresNoForzados = 0;
	}

	public int getSets() {
		return sets;
	}

	public int getJuegosGanados() {
		return juegosGanados;
	}

	public int getAces() {
		return aces;
	}

	public int getErroresNoForzados() {
		return erroresNoForzados;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public void setJuegosGanados(int juegosGanados) {
		this.juegosGanados = juegosGanados;
	}

	public void setAces(int aces) {
		this.aces = aces;
	}

	public void setErroresNoForzados(int erroresNoForzados) {
		this.erroresNoForzados = erroresNoForzados;
	}
}

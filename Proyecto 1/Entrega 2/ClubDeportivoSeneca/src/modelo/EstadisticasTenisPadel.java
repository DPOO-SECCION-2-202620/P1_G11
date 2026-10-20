package modelo;

public class EstadisticasTenisPadel extends EstadisticasIndividual {

	private static final long serialVersionUID = 1L;

	private int juegoGanados;

	private int Aces;

	private int erroresNoForzados;

	private int Sets;

	public EstadisticasTenisPadel() {
		this.juegoGanados = 0;
		this.Aces = 0;
		this.erroresNoForzados = 0;
		this.Sets = 0;
	}
}

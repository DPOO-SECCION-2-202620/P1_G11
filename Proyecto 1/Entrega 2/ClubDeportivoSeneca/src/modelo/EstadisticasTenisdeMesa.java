package modelo;

public class EstadisticasTenisdeMesa extends EstadisticasIndividual {

	private static final long serialVersionUID = 1L;

	private int SetGanados;

	private int Puntas;

	public EstadisticasTenisdeMesa() {
		this.SetGanados = 0;
		this.Puntas = 0;
	}
	public int getSets() {
		return SetGanados;
	}
}

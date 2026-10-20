package modelo;

public class PartidoIndividual extends AbstractPartido {

	private static final long serialVersionUID = 1L;

	private Reserva reserva;

	public PartidoIndividual(Reserva reserva) {
		super(reserva.getFecha(), reserva.getCancha().getDeporte());
		this.reserva = reserva;
	}

	public void actualizarRankings() {
		// TODO: D-17: ganador por sets, +1 / -1, nunca negativo
		throw new UnsupportedOperationException("Pendiente");
	}
}

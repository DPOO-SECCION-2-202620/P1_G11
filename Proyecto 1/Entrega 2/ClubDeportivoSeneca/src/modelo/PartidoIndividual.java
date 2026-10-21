package modelo;

import java.util.Map;

public class PartidoIndividual extends AbstractPartido {

	private static final long serialVersionUID = 1L;

	private Reserva reserva;

	public PartidoIndividual(String fecha, DepIndividual deporte, Reserva reserva) {
		super(fecha, deporte);
		this.reserva = reserva;
	}

	public void actualizarRankings() {
		Map<Jugador, AbstractEstadisticas> est = getEstadisticas();
		if (est.isEmpty()) {
			return;
		}int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (AbstractEstadisticas e : est.values()) {
			int sets = sets(e);
			max = Math.max(max, sets);
			min = Math.min(min, sets);
		}if (max == min) {
			return;
		}for (Map.Entry<Jugador, AbstractEstadisticas> par : est.entrySet()) {
			int delta = -1;
			if (sets(par.getValue()) == max) {
				delta = 1;
			}for (InscripcionDisciplina ins : par.getKey().getInscripciones()) {
				if (ins.getDeporte() == getDeporte()) {
					ins.actualizarRanking(ins.getRanking() + delta);
				}
			}
		}
	}
	private int sets(AbstractEstadisticas est) {
		if (est instanceof EstadisticasTenisPadel) {
			return ((EstadisticasTenisPadel) est).getSets();
		}return ((EstadisticasTenisdeMesa) est).getSets();
	}
	public Reserva getReserva() {
		return reserva;
	}
}

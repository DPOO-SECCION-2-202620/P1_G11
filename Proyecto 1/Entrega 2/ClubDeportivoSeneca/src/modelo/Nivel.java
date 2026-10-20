package modelo;
import java.lang.Math;

public enum Nivel {

	PRINCIPIANTE, INTERMEDIO, AVANZADO;

	public boolean esAdyacente(Nivel otro) {
		int diff = Math.abs(this.ordinal() - otro.ordinal());
		if (diff == 1 || diff == 0) {
			return true;
		} else {return false;}
	}
}

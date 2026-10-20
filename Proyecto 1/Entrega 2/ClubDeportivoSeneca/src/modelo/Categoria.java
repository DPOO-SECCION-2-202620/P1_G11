package modelo;

public enum Categoria {

	SUB13, SUB15, SUB17, MAYORES;

	public boolean admiteEdad(int edad) {
		if (edad <= 0) {
			throw new IllegalArgumentException("La edad debe ser positiva");
		}
		return switch (this) {
			case SUB13 -> edad <= 13;
			case SUB15 -> edad >= 14 && edad <= 15;
			case SUB17 -> edad >= 16 && edad <= 17;
			case MAYORES -> edad >= 18;
        };
	}
}

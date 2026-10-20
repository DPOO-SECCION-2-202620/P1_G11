package modelo;

public enum Categoria {

	SUB13, SUB15, SUB17, MAYORES;

	public boolean admiteEdad(int edad) {
		if (edad <= 0) {
			throw new IllegalArgumentException("La edad debe ser positiva");
		}

		if (this == SUB13) {
			if (edad > 13) {
				return false;
			} else {return true;}
		}

		else if (this == SUB15) {
			if (edad > 15 || edad <= 13) {
				return false;
			} else {return true;}
		}

		else if (this == SUB17) {
			if (edad > 17 || edad <= 15) {
				return false;
			} else {return true;}
		}

		else if (this == MAYORES) {
			if (edad > 22) {
				return true;
			} else {return false;}
		}
	}
}

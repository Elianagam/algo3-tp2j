package algopoly.modelos.tablero.casilleros.compania;

import algopoly.modelos.tablero.casilleros.Casillero;

public class Tren extends Compania implements Casillero {

	private static final Integer PRECIO_TREN = 38000;
	private static final Integer IMP_TREN = 450;
	private static final Integer IMP_TREN_DOBLE = 800;

	public Tren() {
		this.precio = PRECIO_TREN;
		this.impuesto = IMP_TREN;
		this.impuestoDoble = IMP_TREN_DOBLE;
		this.estado = new SinPropietario();
	}

	@Override
	public String getNombre() {
		return "TREN";
	}
}

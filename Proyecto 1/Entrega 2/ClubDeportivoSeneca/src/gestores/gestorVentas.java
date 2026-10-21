package gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.AbstractEmpleado;
import modelo.AbstractTransaccion;
import modelo.Articulo;
import modelo.Bebida;
import modelo.Cancha;
import modelo.ClubDeportivo;
import modelo.EstadoSolicitud;
import modelo.Jugador;
import modelo.Snack;
import modelo.SugerenciaArticulo;
import modelo.TransCafeteria;
import modelo.TransTienda;
import modelo.Ubicacion;
import modelo.Usuario;

public class gestorVentas {
	private ClubDeportivo club;
	public gestorVentas(ClubDeportivo club) {
		super();
		this.club = club;
	}
	public TransTienda comprarTienda(Usuario comprador, Map<Articulo, Integer> items, int puntos, String codigoDescuento) {
		validarItems(comprador, items, Ubicacion.TIENDA);
		double des = calcularDescuento(comprador, subtotal(items), puntos, codigoDescuento);
		TransTienda traTie = new TransTienda(LocalDate.now().toString(), des, puntos);
		for (Map.Entry<Articulo, Integer> par : items.entrySet()) {
			traTie.agregarCompra(par.getKey(), par.getValue());
			par.getKey().descontar(Ubicacion.TIENDA, par.getValue());
		}registrar(comprador, traTie, puntos);
		return traTie;
	}
	public TransCafeteria comprarCafeteria(Usuario comprador, Map<Articulo, Integer> items, double propina, Cancha canchaActual, int puntos, String codigoDescuento) {
		if (propina < 0) {
			throw new IllegalArgumentException("La propina no puede ser negativa");
		}validarItems(comprador, items, Ubicacion.CAFETERIA);
		for (Articulo art : items.keySet()) {
			if (art instanceof Bebida && ((Bebida) art).isCaliente() && canchaActual != null && !canchaActual.permiteBebidaCaliente()) {
				throw new IllegalStateException("No se permiten bebidas calientes en esa cancha");
			}
		}double des = calcularDescuento(comprador, subtotal(items), puntos, codigoDescuento);
		TransCafeteria traCaf = new TransCafeteria(LocalDate.now().toString(), des, puntos, propina);
		for (Map.Entry<Articulo, Integer> par : items.entrySet()) {
			traCaf.agregarCompra(par.getKey(), par.getValue());
			par.getKey().descontar(Ubicacion.CAFETERIA, par.getValue());
		}registrar(comprador, traCaf, puntos);
		return traCaf;
	}
	public void moverArticulo(Articulo articulo, Ubicacion origen, Ubicacion destino, int cantidad) {
		if (!club.getArticulos().contains(articulo)) {
			throw new IllegalArgumentException("El articulo no esta registrado");
		}articulo.mover(origen, destino, cantidad);
	}
	public void reabastecerTienda(Articulo articulo, int cantidad) {
		if (!club.getArticulos().contains(articulo)) {
			throw new IllegalArgumentException("El articulo no esta registrado");
		}articulo.reabastecer(Ubicacion.TIENDA, cantidad);
	}
	public void sugerirArticulo(AbstractEmpleado empleado, String nombre, String categoria, double precio, List<String> alergenos) {
		if (!empleado.estaActivo()) {
			throw new IllegalArgumentException("El empleado no esta activo");
		}if (nombre == null || nombre.trim().isEmpty() || categoria == null || categoria.trim().isEmpty() || precio <= 0) {
			throw new IllegalArgumentException("Faltan datos del articulo");
		}List<SugerenciaArticulo> sugerencias = club.getSugerencias();
		sugerencias.add(new SugerenciaArticulo(empleado, nombre, categoria, precio, alergenos));
	}
	public void resolverSugerencia(SugerenciaArticulo sugerencia, boolean aprobada) {
		if (!club.getSugerencias().contains(sugerencia) || sugerencia.getEstado() != EstadoSolicitud.PENDIENTE) {
			throw new IllegalStateException("La sugerencia no existe o no esta pendiente");
		}if (!aprobada) {
			sugerencia.setEstado(EstadoSolicitud.RECHAZADA);
			return;
		}for (Articulo a : club.getArticulos()) {
			if (a.getNombre().equalsIgnoreCase(sugerencia.getNombre())) {
				throw new IllegalArgumentException("El articulo ya esta registrado");
			}
		}Articulo art;
		if (sugerencia.getAlergenos() != null && !sugerencia.getAlergenos().isEmpty()) {
			art = new Snack(sugerencia.getNombre(), sugerencia.getCategoria(), sugerencia.getPrecio(), sugerencia.getAlergenos());
		}else {
			art = new Articulo(sugerencia.getNombre(), sugerencia.getCategoria(), sugerencia.getPrecio());
		}List<Articulo> articulos = club.getArticulos();
		articulos.add(art);
		sugerencia.setEstado(EstadoSolicitud.APROBADA);
	}
	public List<AbstractTransaccion> getVentas(String rubro, String desde, String hasta) {
		if (desde.compareTo(hasta) > 0) {
			throw new IllegalArgumentException("El rango de fechas esta invertido");
		}String rub = rubro.trim().toLowerCase();
		if (!rub.equals("tienda") && !rub.equals("cafeteria") && !rub.equals("cafetería") && !rub.equals("mensualidades")) {
			throw new IllegalArgumentException("Rubro desconocido");
		}List<AbstractTransaccion> rta = new ArrayList<>();
		for (Usuario u : club.getUsuarios()) {
			for (AbstractTransaccion t : u.getTransacciones()) {
				boolean esRubro = (rub.equals("tienda") && t instanceof TransTienda) || (rub.startsWith("cafeter") && t instanceof TransCafeteria);
				if (esRubro && t.getFecha().compareTo(desde) >= 0 && t.getFecha().compareTo(hasta) <= 0) {
					rta.add(t);
				}
			}
		}return rta;
	}
	public String compartirCodigo(AbstractEmpleado empleado, Jugador socio) {
		if (!empleado.estaActivo() || !socio.estaActivo()) {
			throw new IllegalArgumentException("El empleado y el socio deben estar activos");
		}return empleado.getCodigoDescuento();
	}
	private void validarItems(Usuario comprador, Map<Articulo, Integer> items, Ubicacion ubi) {
		if (!comprador.estaActivo()) {
			throw new IllegalArgumentException("El comprador no esta activo");
		}if (items.isEmpty()) {
			throw new IllegalArgumentException("El carrito esta vacio");
		}for (Map.Entry<Articulo, Integer> par : items.entrySet()) {
			if (par.getValue() <= 0) {
				throw new IllegalArgumentException("La cantidad debe ser positiva");
			}if (par.getKey().getCantidad(ubi) < par.getValue()) {
				throw new IllegalStateException("No hay cantidad suficiente de " + par.getKey().getNombre());
			}
		}
	}
	private double subtotal(Map<Articulo, Integer> items) {
		double sub = 0;
		for (Map.Entry<Articulo, Integer> par : items.entrySet()) {
			sub += par.getKey().getPrecio() * par.getValue();
		}return sub;
	}
	private double calcularDescuento(Usuario comprador, double sub, int puntos, String codigo) {
		if (puntos < 0) {
			throw new IllegalArgumentException("Los puntos no pueden ser negativos");
		}if (codigo != null && !codigo.trim().isEmpty()) {
			if (puntos > 0) {
				throw new IllegalArgumentException("No se pueden acumular el codigo de descuento y los puntos");
			}for (Usuario u : club.getUsuarios()) {
				if (u instanceof AbstractEmpleado && ((AbstractEmpleado) u).getCodigoDescuento().equals(codigo)) {
					if (u == comprador) {
						return sub * 0.15;
					}return sub * 0.08;
				}
			}throw new IllegalArgumentException("El codigo de descuento no existe");
		}if (puntos > 0) {
			if (comprador.getPuntosFidelidad() < puntos) {
				throw new IllegalArgumentException("Puntos insuficientes");
			}if (puntos > sub) {
				throw new IllegalArgumentException("Los puntos superan el valor de la compra");
			}return puntos;
		}return 0;
	}
	private void registrar(Usuario comprador, AbstractTransaccion tra, int puntos) {
		if (puntos > 0) {
			comprador.redimirPuntos(puntos);
		}comprador.acumularPuntos(tra.calcularTotal());
		comprador.agregarTransaccion(tra);
	}
}
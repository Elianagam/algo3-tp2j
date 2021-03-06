package algopoly.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import algopoly.modelos.jugador.Jugador;
import algopoly.modelos.tablero.Tablero;
import algopoly.modelos.tablero.casilleros.barrios.Barrio;
import algopoly.vistas.VistaConsola;
import algopoly.vistas.VistaInformacion;
import algopoly.vistas.VistaTablero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

public class BotonComprarCasaHandler implements EventHandler<ActionEvent> {

	private final VistaTablero vista;
	private final Tablero tablero;
	private final VistaInformacion vistaInformacion;
	private final VistaConsola vistaConsola;

	public BotonComprarCasaHandler(VistaTablero vista, Tablero tablero,
								   VistaInformacion vistaInformacion, VistaConsola vistaConsola) {
		this.vista = vista;
		this.tablero = tablero;
		this.vistaInformacion = vistaInformacion;
		this.vistaConsola = vistaConsola;
	}

	@Override
	public void handle(ActionEvent actionEvent) {
		Jugador jugador = tablero.jugadorActual();
		Collection<Barrio> barrios = jugador.getBarrios();

		List<String> choices = new ArrayList<>();
		for (Barrio barrio : barrios) {
			choices.add(barrio.getProvincia().name());
		}

		ArrayList<Barrio> barriosAux = new ArrayList<>();
		barriosAux.addAll(barrios);

		ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
		dialog.setTitle("Compra de casa");
		dialog.setHeaderText("Elija en que barrio desea comprar una casa");
		dialog.setContentText("Barrios disponibles:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			for (Barrio barrio : barriosAux) {
				if (barrio.getProvincia().name().equals(result.get())) { // compara para ver que barrio eligió
					String mensaje = barrio.construirCasa() ?
							"La casa fue comprada exitosamente." : "No se puede edificar.";

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("CompraCasa");
					alert.setHeaderText("Comprar Casa");
					alert.setContentText(mensaje);

					alert.showAndWait();


				}
			}
		}


		vista.update(); // update imagen casa
		vistaInformacion.update(); // update plata
	}

}

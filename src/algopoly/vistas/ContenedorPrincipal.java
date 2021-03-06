package algopoly.vistas;

import algopoly.controladores.*;
import algopoly.modelos.tablero.Tablero;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;


public class ContenedorPrincipal extends BorderPane {

	BarraDeMenu menuBar;
	VistaTablero vistaTablero;
	Canvas canvasCentral;
	VBox contenedorCentral;
	VistaDados vistaDados;
	VistaInformacion vistaInformacion;
	VistaConsola vistaConsola;
	MediaPlayer mediaPlayer;

	public ContenedorPrincipal(Stage stage, Tablero tablero) {
        Label etiqueta = new Label();
        this.setConsola(etiqueta);
		this.setMenu(stage);
		this.setCentro(tablero);
		this.setInformacionJugadores(tablero);
		this.setReproductor();
		this.setBotonera(tablero);
	}

	private void setReproductor(){
        this.mediaPlayer = new MediaPlayer(new Media(new File("music.wav").toURI().toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setAutoPlay(true);
    }
	private void setBotonera(Tablero tablero) {

        Button botonMusica = new Button();
        botonMusica.setText("Activar/Desactivar música");
        BotonSilenciarMusicaHandler botonSilenciarMusicaHandler = new BotonSilenciarMusicaHandler(mediaPlayer);
        botonMusica.setOnAction(botonSilenciarMusicaHandler);

		Canvas canvasDados = new Canvas(100,200);
		vistaDados = new VistaDados(tablero, canvasDados);
		
		Button botonMover = new Button();
		botonMover.setText("Próximo turno");
		BotonMoverHandler moveButtonHandler = new BotonMoverHandler(vistaTablero, tablero, vistaDados,
                vistaInformacion, vistaConsola);
		botonMover.setOnAction(moveButtonHandler);
		
		Button botonInformacion = new Button();
		botonInformacion.setText("Información jugador Actual");
		BotonInformacionHandler informacionHandler = new BotonInformacionHandler(tablero);
		botonInformacion.setOnAction(informacionHandler);
		
		Button botonVenderTerreno = new Button();
		botonVenderTerreno.setText("Vender terreno");
		BotonVenderTerrenoHandler venderTerrenoHandler = new BotonVenderTerrenoHandler(vistaTablero, tablero,
                vistaInformacion, vistaConsola);
		botonVenderTerreno.setOnAction(venderTerrenoHandler);
		
		Button botonVenderCompania = new Button();
		botonVenderCompania.setText("Vender compañia");
		BotonVenderCompaniaHandler venderCompaniaHandler = new BotonVenderCompaniaHandler(vistaTablero, tablero,
                vistaInformacion, vistaConsola);
		botonVenderCompania.setOnAction(venderCompaniaHandler);
		
		Button botonIntercambiar = new Button();
		botonIntercambiar.setText("Intercambiar barrio");
		BotonIntercambiarHandler intercambiarHandler = new BotonIntercambiarHandler(vistaTablero, tablero, vistaInformacion);
		botonIntercambiar.setOnAction(intercambiarHandler);
		
		Button botonComprarCasa = new Button();
		botonComprarCasa.setText("Comprar casa");
		BotonComprarCasaHandler comprarCasaHandler = new BotonComprarCasaHandler(vistaTablero, tablero, vistaInformacion,
				vistaConsola);
		botonComprarCasa.setOnAction(comprarCasaHandler);
		
		Button botonComprarHotel = new Button();
		botonComprarHotel.setText("Comprar hotel");
		BotonComprarHotelHandler comprarHotelHandler = new BotonComprarHotelHandler(vistaTablero, tablero, vistaInformacion);
		botonComprarHotel.setOnAction(comprarHotelHandler);
		
		Button botonPagarFianza = new Button();
		botonPagarFianza.setText("Pagar fianza");
		BotonPagarFianzaHandler botonPagarFianzaHandler = new BotonPagarFianzaHandler(vistaTablero, tablero, vistaInformacion);
		botonPagarFianza.setOnAction(botonPagarFianzaHandler);

		VBox contenedorVertical = new VBox(botonMover,botonInformacion, botonVenderTerreno, botonVenderCompania,
                botonIntercambiar, botonComprarCasa, botonComprarHotel, botonPagarFianza, canvasDados, botonMusica);
		contenedorVertical.setPadding(new Insets(30));
		contenedorVertical.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		contenedorVertical.setSpacing(10);
		
		this.setLeft(contenedorVertical);

	}

	private void setMenu(Stage stage) {
		this.menuBar = new BarraDeMenu(stage);
		this.setTop(menuBar);
	}

	private void setCentro(Tablero tablero) {
		canvasCentral = new Canvas(500, 500);
		vistaTablero = new VistaTablero(tablero, canvasCentral);
		vistaTablero.dibujar();

		contenedorCentral = new VBox(canvasCentral);
		contenedorCentral.setAlignment(Pos.CENTER);
		contenedorCentral.setSpacing(20);
		contenedorCentral.setPadding(new Insets(25));
		Image imagen = new Image("file:src/algopoly/vistas/imagenes/game.jpeg");
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		contenedorCentral.setBackground(new Background(imagenDeFondo));

		this.setCenter(contenedorCentral);
	}
	
	private void setInformacionJugadores(Tablero tablero) {
		
		Canvas canvasInfo = new Canvas(150, 500);
		vistaInformacion = new VistaInformacion(tablero, canvasInfo);
		vistaInformacion.dibujar();
		
		VBox contenedorVertical = new VBox(canvasInfo);
		
		contenedorVertical.setSpacing(10);
		contenedorVertical.setPadding(new Insets(30));
		contenedorVertical.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		this.setRight(contenedorVertical);

	}
	
	private void setConsola(Label etiqueta) {

		etiqueta.setPrefWidth(1000);
		etiqueta.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
		etiqueta.setTextFill(Color.GREEN);

        ListView<Label> panel = new ListView<Label>();
        panel.setMaxHeight(150);
        panel.setPrefHeight(150);
        panel.setStyle("-fx-control-inner-background: black;");
        panel.getItems().add(etiqueta);
        panel.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

        vistaConsola = new VistaConsola(etiqueta, panel);

        this.setBottom(panel);
	}

	public BarraDeMenu getBarraDeMenu() {
		return menuBar;
	}

}


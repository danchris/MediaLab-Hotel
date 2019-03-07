package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelMenuBar extends MenuBar {
	private static Menu Game;
	private static Menu Statistics;
	private static MenuItem Start;
	private static MenuItem Stop;
	private static MenuItem Cards;
	private static MenuItem Exit;
	private static MenuItem Hotels;
	private static MenuItem Entrances;
	private static MenuItem Profits;

	public HotelMenuBar() {
		System.out.println("HotelMenuBar.java: Create Menu Bar");
		Game = new Menu("Game");
		Statistics = new Menu("Statistics");
		Start = new MenuItem("Start");
		Stop = new MenuItem("Stop");
		Cards = new MenuItem("Cards");
		Exit = new MenuItem("Exit");
		Hotels = new MenuItem("Hotels");
		Entrances = new MenuItem("Entrances");
		Profits = new MenuItem("Profits");

		Game.getItems().addAll(Start, Stop, Cards, Exit);
		Statistics.getItems().addAll(Hotels, Entrances, Profits);
		getMenus().add(Game);
		getMenus().add(Statistics);

		Start.setOnAction(actionEvent -> {
			try {
				System.out.println("HotelMenuBar.java: Start new Game");
				HotelGame.startNewGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("HotelMenuBar.java: Start Button Pressed Exception");
				e.printStackTrace();
			}
		});
		Stop.setOnAction(actionEvent -> HotelGame.stopGame());
		Exit.setOnAction(actionEvent -> Platform.exit());
		Cards.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (HotelGame.getStopFlag() == 0) {
					List<String> choices = new ArrayList<String>();
					for (HotelCard i : HotelFileReader.getHotelsCards()) {
						choices.add(i.getName());
					}
					ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
					dialog.setGraphic(null);
					dialog.setTitle("Hotel Cards");
					dialog.setHeaderText("Hotel Cards");
					dialog.setContentText("Choose a Hotel:");
					Optional<String> result = dialog.showAndWait();
					result.ifPresent(option -> {
						for (HotelCard i : HotelFileReader.getHotelsCards()) {
							if (i.getName().equals(option)) {
								i.hotelCardDialogBox();
							}
						}

					});
				}
			}
		});
		
		Profits.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			//	if (HotelGame.getStopFlag() == 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setGraphic(null);
					alert.setTitle("Profits");
					alert.setHeaderText("Profits");
					GridPane info = new GridPane();
					info.setHgap(10); //horizontal gap in pixels => that's what you are asking for
					info.setVgap(10); //vertical gap in pixels
					info.setPadding(new Insets(10, 10, 10, 10));
					info.add(new Text("Player 1: "+HotelGame.getById(1).getMaxMLS() + " mls"),0,0);
					info.add(new Text("Player 2: "+HotelGame.getById(2).getMaxMLS()+ " mls"),0,1);
					info.add(new Text("Player 3: "+HotelGame.getById(3).getMaxMLS()+ " mls"),0,2);
					alert.getDialogPane().setContent(info);
					alert.showAndWait();
				//}
			}
		});

	}

	public Menu getGame() {
		return Game;
	}

	public Menu getStatistics() {
		return Statistics;
	}

	public MenuItem getStart() {
		return Start;
	}

	public MenuItem getStop() {
		return Stop;
	}

	public MenuItem getExit() {
		return Exit;
	}
}

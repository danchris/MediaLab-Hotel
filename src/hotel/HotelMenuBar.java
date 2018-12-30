package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ChoiceDialog;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelMenuBar extends MenuBar{
	private static Menu Game;
	private static Menu Statistics;
	private static MenuItem Start;
	private static MenuItem Stop;
	private static MenuItem Cards;
	private static MenuItem Exit;
	private static MenuItem Hotels;
	private static MenuItem Entrances;
	private static MenuItem Profits;

	public HotelMenuBar (){
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
		// Creating the mouse event handler
		Hotels.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				List<String> choices = new ArrayList<String>();
				for (HotelCard i : HotelFileReader.getHotelsCards()) {
					choices.add(i.getName());
				}
				ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
				dialog.setTitle("Choice Dialog");
				dialog.setHeaderText("Look, a Choice Dialog");
				dialog.setContentText("Choose your letter:");
				Optional<String> result = dialog.showAndWait();
				result.ifPresent(letter -> System.out.println("Your choice: " + letter));
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

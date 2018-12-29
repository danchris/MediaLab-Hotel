package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

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

	public HotelMenuBar (Stage primaryStage, AnimationTimer timer){
		System.out.println("HotelMenuBar.java: Create Menu Bar");
	//	menuBar = new MenuBar();
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
		this.getMenus().add(Game);
		this.getMenus().add(Statistics);
	
		Start.setOnAction(actionEvent -> {
			try {
				System.out.println("HotelMenuBar.java: Start new Game");
				startGame(primaryStage,timer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("HotelMenuBar.java: Start Button Pressed Exception");
				e.printStackTrace();
			}
		});
		Stop.setOnAction(actionEvent -> stopGame(primaryStage,timer));
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
	/*
	 * ksekinaei to game me to button
	 */
	public void startGame(Stage primaryStage,AnimationTimer timer) {
		//TODO Start new game Timer etc.
		System.out.println("startGame > HotelMenuBar.java: Start New Game");
		timer.stop();
		timer.start();
	}
	public void stopGame(Stage primaryStage, AnimationTimer timer) {
		//TODO Stop Game stop timer etc.
		System.out.println("startGame > HotelMenuBar.java: Stop Game");
		timer.stop();
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

package hotel;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelToolBox extends Pane{
	
	private static Button button;
	private static Button dice;
	private static Button reqBuilding;
	private static Button buyHotel;
	private static Button buyEntrance;
	private static Button bank;
	private HotelPlayer curr;
	private GridPane grid;
	private int diceNumber;
	private Text diceRes;
	private Text reqBuildingRes;
	
	public HotelToolBox() {
		setPrefWidth(200);
		grid = new GridPane();
		/*
		button = new Button();
		button.setText("Click Me");
		System.out.println(HotelGame.getCurrentPlayer());
		System.out.println(HotelGame.getNextBoardBox());
		button.setOnAction(actionEvent -> {
			HotelGame.getCurrentPlayer().setDice(1);
			HotelGame.getCurrentPlayer().move();
		});
			*/

		dice = new Button("Roll Dice");
		diceRes = new Text("Dice Result: ");

		reqBuilding = new Button("Request Building");
		reqBuildingRes = new Text("Request Result: ");
		
		buyHotel = new Button("Buy Hotel");
		buyEntrance = new Button("Buy Entrance");
		bank = new Button("Request 1000 MLS");
		
		
		dice.setOnAction(actionEvent ->{
			HotelGame.setStopFlag(1);
			diceNumber = new Random().nextInt(6) + 1;
			System.out.println("Dice selected is " + diceNumber);
			HotelGame.getCurrentPlayer().setDice(diceNumber);
			HotelMessenger.showDice(diceNumber);
			diceRes.setText("Dice Result: "+Integer.toString(diceNumber));
		});
		
		grid.add(dice, 0, 0);
		grid.add(diceRes, 0, 1);
		grid.add(reqBuilding,0,2);
		grid.add(reqBuildingRes, 0, 3);
		grid.add(buyHotel, 0, 4);
		grid.add(buyEntrance, 0, 5);
		grid.add(bank, 0, 6);
		grid.setVgap(15);

		getChildren().add(grid);
		
	}
	
	public static void enableDiceButton() {
		dice.setDisable(false);
	}
	
	public static void disableDiceButton() {
		dice.setDisable(true);
	}
	public static void enableBuyHotelButton() {
		buyHotel.setDisable(false);
	}
	
	public static void disableBuyHotelButton() {
		buyHotel.setDisable(true);
	}
	
	
}

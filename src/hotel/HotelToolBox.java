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
	private Button dice;
	private HotelPlayer curr;
	private GridPane grid;
	private int diceNumber;
	
	public HotelToolBox() {
		setPrefWidth(200);
		//setPrefSize(256,768);
		grid = new GridPane();
		button = new Button();
		button.setText("Click Me");
		System.out.println(HotelGame.getCurrentPlayer());
		System.out.println(HotelGame.getNextBoardBox());
		button.setOnAction(actionEvent -> {
			//curr = HotelGame.getCurrentPlayer();
		//	curr.move(curr.getCurrentBox().getNext(), curr.getCurrentBox());
		});
			
		dice = new Button();
		dice.setText("Roll Dice");
		
		dice.setOnAction(actionEvent ->{
			HotelGame.setStopFlag(1);
			diceNumber = new Random().nextInt(6) + 1;
			System.out.println("Dice selected is " + diceNumber);
			HotelGame.getCurrentPlayer().setDice(diceNumber);
			HotelMessenger.showDice(diceNumber);
			//button.setDisable(true);
		});
		
	//	grid.add(new Text("Move  "), 0, 0);
	//	grid.add(button, 1, 0);
		grid.add(new Text("Roll Dice  "), 0, 1);
		grid.add(dice, 1, 1);
		grid.setVgap(20);
		grid.setPadding(new Insets(50,0,0,0));

		
		getChildren().add(grid);
		
	}
	
	public static void enableButton() {
		button.setDisable(false);
	}
}

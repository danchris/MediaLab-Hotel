package hotel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelToolBox extends VBox{
	
	private Button button;
	
	public HotelToolBox() {
		
		this.setMaxSize(250,600);
		button = new Button();
		button.setText("Click Me");
		System.out.println(HotelGame.getCurrentPlayer());
		System.out.println(HotelGame.getNextBoardBox());
		button.setOnAction(actionEvent -> HotelGame.getCurrentPlayer().move(HotelGame.getNextBoardBox()));
			
		getChildren().add(button);
		
	}
	

}

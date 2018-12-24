package hotel;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class HotelBoards extends SplitPane{
	
	private static VBox GameBoard;
	private static ToolBar toolBar;
	
	public HotelBoards() {
		System.out.println("HotelBoards.java: Create Boards");
		// Boards
	
		GameBoard = new VBox();
		toolBar = new ToolBar();
		toolBar.setOrientation(Orientation.VERTICAL);
		GameBoard.setPrefSize(600, 600);
		toolBar.setPrefSize(200, 600);
		this.getItems().addAll(GameBoard, toolBar);
	}
}

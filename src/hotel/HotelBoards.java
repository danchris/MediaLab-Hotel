package hotel;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;


/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoards extends SplitPane {

	private HotelGameBoard GameBoard;
	private HotelToolBox toolBox;


	public HotelBoards(String[][] file) throws IOException {
		System.out.println("HotelBoards.java: Create Boards");
		// Boards

		GameBoard = new HotelGameBoard(file);
    	toolBox = new HotelToolBox();

		this.getItems().addAll(GameBoard, toolBox);
	}

	public HotelBoardBox getStartBox() {
		System.out.println("Edw");
		// System.out.println(startBox._getX());
		return GameBoard.getStartBox();
	}
	
	public HotelGameBoard getGameBoard() {
		return GameBoard;
	}
	
	public HotelToolBox getToolBox() {
		return toolBox;
	}

}

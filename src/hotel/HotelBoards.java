package hotel;

import java.io.IOException;
import javafx.scene.control.SplitPane;


/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoards extends SplitPane {

	private static HotelGameBoard GameBoard;
	private static HotelToolBar toolBar;


	public HotelBoards(String[][] file) throws IOException {
		System.out.println("HotelBoards.java: Create Boards");
		// Boards

		GameBoard = new HotelGameBoard(file);
		toolBar = new HotelToolBar();
		this.getItems().addAll(GameBoard, toolBar);
	}

	public HotelBoardBox getStartBox() {
		System.out.println("Edw");
		// System.out.println(startBox._getX());
		return GameBoard.getStartBox();
	}
	
	public HotelGameBoard getGameBoard() {
		return GameBoard;
	}
	
	public HotelToolBar getToolBar() {
		return toolBar;
	}

}

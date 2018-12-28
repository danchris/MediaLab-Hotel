package hotel;

import java.io.IOException;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoards extends SplitPane {

	private static Pane GameBoard;
	private static ToolBar toolBar;
	private static HotelBoardBox[][] gridBoard = new HotelBoardBox[12][15];
	private static HotelBoardBox startBox = null;

	public HotelBoards(String[][] file) throws IOException{
		System.out.println("HotelBoards.java: Create Boards");
		// Boards

		GameBoard = new Pane();
		toolBar = new ToolBar();
		toolBar.setOrientation(Orientation.VERTICAL);
		GameBoard.setPrefSize(600, 600);
		toolBar.setPrefSize(250, 600);
		HotelBoardBox a;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
				a = new HotelBoardBox(file[i][j], i, j);
				if (file[i][j].equals("S"))
					startBox = a;
				gridBoard[i][j] = a;
				GameBoard.getChildren().add(a);
			}
		}
		this.getItems().addAll(GameBoard, toolBar);
	}

	public HotelBoardBox[][] getGridBoard() {
		return gridBoard;
	}

	public void printGridBoard() {
		for (HotelBoardBox[] tmpRow : gridBoard) {
			for (HotelBoardBox tmpCol : tmpRow) {
				tmpCol.printHotelBoardBox();
			}
			System.out.println("");
		}
	}

	public HotelBoardBox getStartBox() {
		System.out.println("Edw");
		// System.out.println(startBox._getX());
		return startBox;
	}
}

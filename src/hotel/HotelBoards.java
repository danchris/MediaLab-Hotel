package hotel;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoards extends SplitPane{
	
	private static GridPane GameBoard;
	private static ToolBar toolBar;
	
	public HotelBoards(String[][] file) {
		System.out.println("HotelBoards.java: Create Boards");
		// Boards
	
		GameBoard = new GridPane();
		toolBar = new ToolBar();
		toolBar.setOrientation(Orientation.VERTICAL);
		GameBoard.setPrefSize(600, 600);
		toolBar.setPrefSize(250, 600);
		HotelBoardBox a;
		for(int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
				Text text = new Text(i,j,file[i][j]);
				text.setFill(Color.BLACK);
				a = new HotelBoardBox(file[i][j]);
				GameBoard.add(a.getHotelBoardBox(),j,i,1,1);
	            text.setTextAlignment(TextAlignment.CENTER);
				GameBoard.add(text,j,i,1,1);
			}
		}
		GameBoard.setAlignment(Pos.CENTER);
		this.getItems().addAll(GameBoard, toolBar);
	}
}

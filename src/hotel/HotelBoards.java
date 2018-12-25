package hotel;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

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
		toolBar.setPrefSize(200, 600);
		HotelBoardBox a;
		//GameBoard.add(a.getHotelBoardBox(), 0, 0);
		for(int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
		//		System.out.println(file);
			//	System.out.println("i = "+i+" j = "+ j+" => "+ file[j]);
				a = new HotelBoardBox(file[i][j]);
				
				GameBoard.add(a.getHotelBoardBox(),i,j);
			}
		}
		GameBoard.setAlignment(Pos.CENTER);
		this.getItems().addAll(GameBoard, toolBar);
	}
}

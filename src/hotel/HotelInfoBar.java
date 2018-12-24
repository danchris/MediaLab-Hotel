package hotel;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HotelInfoBar extends TilePane{
	private static Text Player1;
	private static Text Player2;
	private static Text Player3;
	private static Text AvailableHotels;
	private static Text TotalTime;

	
	public HotelInfoBar() {
		System.out.println("HotelInfoBar.java: Constructor Create Info Bar");
		this.setOrientation(Orientation.HORIZONTAL);
		this.setTileAlignment(Pos.CENTER);
		this.setHgap(40);
		Player1 = new Text("Player 1: ");
		Player2 = new Text("Player 2: ");
		Player3 = new Text("Player 3: ");
		AvailableHotels = new Text("Available Hotels: ");
		TotalTime = new Text("Total Time: ");
		Player1.setFill(Color.BLUE);
		Player2.setFill(Color.RED);
		Player3.setFill(Color.GREEN);
		// InfoBar.setSpacing(200);
		this.getChildren().add(Player1);
		this.getChildren().add(Player2);
		this.getChildren().add(Player3);
		this.getChildren().add(AvailableHotels);
		this.getChildren().add(TotalTime);
	}

	public Text getTotalTime() {
		return TotalTime;
	}
}

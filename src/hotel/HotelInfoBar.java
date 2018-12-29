package hotel;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * @author Daniel Christodoulopoulos
 */
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
		Player1 = new Text("Player 1: 12000");
		Player2 = new Text("Player 2: 12000");
		Player3 = new Text("Player 3: 12000");
		AvailableHotels = new Text("Available Hotels: ");
		TotalTime = new Text("Total Time: ");
		Player1.setFill(Color.BLUE);
		Player2.setFill(Color.RED);
		Player3.setFill(Color.GREEN);
		this.getChildren().add(Player1);
		this.getChildren().add(Player2);
		this.getChildren().add(Player3);
		this.getChildren().add(AvailableHotels);
		this.getChildren().add(TotalTime);
	}

	public Text getTotalTime() {
		return TotalTime;
	}
	
	public Text getPlayer1() {
		return Player1;
	}
	
	public Text getPlayer2() {
		return Player1;
	}
	
	public Text getPlayer3() {
		return Player1;
	}
	
	public Text getAvailableHotels() {
		return AvailableHotels;
	}
	/*
	public void setPlayerName(int id, String s) {
		if ( id == 1) Player1.setText(s);
		else if ( id == 2) Player2.setText(s);
		else if (id == 3) Player3.setText(s);
		else System.out.println("HotelInfoBar.java: Error id player");
	}
	*/
}

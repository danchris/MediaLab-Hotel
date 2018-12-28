package hotel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoardBox extends StackPane {

	private Rectangle rect;
	private int x;
	private int y;
	private String id;
	private HotelBoardBox next = null;

	private Color c;
	
	
	public HotelBoardBox(String input,int x, int y) {
		this.x = x;
		this.y = y;
		this.id = input;
		
		rect = new Rectangle(34,44);
		c = Color.web(GUI.getColors().get(input), 0.9);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
		getChildren().add(rect);
		setTranslateX(y*36);
		setTranslateY(x*46);
	}

	public int _getX() {
		return x;
	}
	
	public int _getY() {
		return y;
	}
	
	public String getID() {
		return id;
	}
	
	public HotelBoardBox getNext() {
		return next;
	}
	
	public void setNext(HotelBoardBox next) {
		this.next = next;
	}
	
	public Rectangle getHotelBoardBox() {
		return rect;
	}
	
	public void printHotelBoardBox() {
		System.out.print(this.id+" ");
	}
}

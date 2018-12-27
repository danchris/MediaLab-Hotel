package hotel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoardBox extends Rectangle {

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
		
		rect = new Rectangle();
		rect.setWidth(35);
		rect.setHeight(45);
		rect.setLayoutX(x);
		rect.setLayoutY(y);
		c = Color.web(GUI.getColors().get(input), 0.9);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
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

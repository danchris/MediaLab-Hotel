package hotel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HotelBoardBox extends Rectangle {

	private Rectangle rect;
	private static Map<String,String> colors;
	static {
		colors = new HashMap<String,String>();
		colors.put("S", "#e6194B");
		colors.put("1", "#3cb44b");
		colors.put("2", "#ffe119");
		colors.put("3","#4363d8");
		colors.put("4", "#f58231");
		colors.put("5", "#911eb4");
		colors.put("6", "#42d4f4");
		colors.put("7", "#f032e6");
		colors.put("8", "#bfef45");
		colors.put("C", "#e6194B");
		colors.put("B", "#e6194B");
		colors.put("H", "#e6194B");
		colors.put("E", "#e6194B");
		colors.put("F", "#e6194B");
	}
	private Color c;
	public HotelBoardBox(String input) {
		rect = new Rectangle();
		rect.setWidth(35);
		rect.setHeight(35);
		//System.out.println("HotelBoardBox.java: "+ input);
		c = Color.web(colors.get(input),0.9);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
	}

	public Rectangle getHotelBoardBox() {
		return rect;
	}
}

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
	private static Map<String, String> colors;
	static {
		colors = new HashMap<String, String>();
		colors.put("S", "#fffac8");
		colors.put("1", "#3cb44b");
		colors.put("2", "#ffe119");
		colors.put("3", "#4363d8");
		colors.put("4", "#f58231");
		colors.put("5", "#911eb4");
		colors.put("6", "#42d4f4");
		colors.put("7", "#aaffc3");
		colors.put("8", "#bfef45");
		colors.put("C", "#fffac8");
		colors.put("B", "#fffac8");
		colors.put("H", "#fffac8");
		colors.put("E", "#fffac8");
		colors.put("F", "#fffac8");
	}
	private Color c;

	public HotelBoardBox(String input) {
		rect = new Rectangle();
		rect.setWidth(35);
		rect.setHeight(45);
		c = Color.web(colors.get(input), 0.9);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
	}

	public Rectangle getHotelBoardBox() {
		return rect;
	}
}

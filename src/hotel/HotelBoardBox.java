package hotel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelBoardBox extends StackPane {

	private Rectangle rect;
	private int x;
	private int y;
	private String id;
	private HotelBoardBox next = null;
	private String tmpColor;

	private Color c;
//	private Image img;

	public HotelBoardBox(String input, int x, int y) throws IOException {
		System.out.println("HotelBoardBox.java: Constructor");
		this.x = x;
		this.y = y;
		this.id = input;

		rect = new Rectangle(34, 44);
		if (!GUI.getHotelsIds().contains(input) && GUI.getColors().containsKey(input)) {
			System.out.println("OLLA KALA TO INPUT EINAI"+input);
			tmpColor = GUI.getColors().get(input);
			GUI.getHotelsIds().add(input);
		}
		else {
			System.out.println("ELSE"+ input);

			for (Map.Entry<String, String> it : GUI.getColors().entrySet()) {
				if (!GUI.getHotelsIds().contains(it.getKey()) && Character.isDigit(it.getKey().charAt(0)))
					System.out.println(input);
					tmpColor = it.getValue();
					GUI.getHotelsIds().add(it.getKey());
			}
		}
		c = Color.web(tmpColor, 0.9);
		System.out.println("HotelBoardBox.java: Color picked is " + c);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
		// img = new Image(new FileInputStream("/home/daniel/Sxolh/ROH Y/7o
		// eksamino/MediaLab/project/Hotel/imgs/green.png"));
		// ImageView imageView = new ImageView(img);
		// imageView.setFitHeight(20);
		// imageView.setFitWidth(20);
		getChildren().add(rect);
		// getChildren().add(imageView);
		setTranslateX(y * 36);
		setTranslateY(x * 46);
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

	public void printHotelBoardBox() {
		System.out.print(this.id + " ");
	}
}

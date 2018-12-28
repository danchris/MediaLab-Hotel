package hotel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;

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
	private Image img;
	private Image pawn;
	private ImageView imgView;
	private ImageView pawnView;

	public HotelBoardBox(String input, int x, int y) throws IOException {
		System.out.println("HotelBoardBox.java: Constructor");
		this.x = x;
		this.y = y;
		this.id = input;

		rect = new Rectangle(35, 45);
		if (GUI.getFinalColors().containsKey(input))
			tmpColor = GUI.getFinalColors().get(input);
		else {
			tmpColor = GUI.getColors().remove();
			GUI.setFinalColors(input, tmpColor);
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

	public void setPawn(String i) {
		pawn = HotelFileReader.getPawn(i);
		pawnView = new ImageView(pawn);
		pawnView.setFitHeight(25);
		pawnView.setFitWidth(25);
		getChildren().add(pawnView);
	}

	public void setArrow() {
		img = HotelFileReader.getArrow();
		imgView = new ImageView(img);
		imgView.setFitHeight(20);
		imgView.setFitWidth(20);
		getChildren().add(imgView);
		System.out.println("HotelBoardBox.java: Finish set arrow");
	}

	public void rotateImageView(int times) {
		for(int i = 0; i < times; i++) imgView.setRotate(imgView.getRotate() + 90);
	}

	// returns true if box is available else false;
	public boolean canGo() {
		if (Character.isLetter(id.charAt(0)) && !id.equals("F")) 
			return true;
		return false;
	}
}

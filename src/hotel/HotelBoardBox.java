package hotel;


import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
	EventHandler<MouseEvent> clickBoxHandler;

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
		getChildren().add(rect);
		setTranslateX(y * 36);
		setTranslateY(x * 46);

		// Creating the mouse event handler
		clickBoxHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("HotelBoardBox.java: Clicked Box x = " + x + " y = " + y + " ID = " + id);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Hotel Box Information");
				if (Character.isDigit(id.charAt(0))) alert.setHeaderText("Hotel Name " + HotelFileReader.get(Integer.parseInt(id)).getName());
				else alert.setHeaderText(id);
				GridPane info = new GridPane();
				Text t = new Text("x = " + x + " , y = " + y);
				info.add(t, 0, 0);
				alert.getDialogPane().setContent(info);
				alert.showAndWait();
			}
		};
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, clickBoxHandler);

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
		for (int i = 0; i < times; i++)
			imgView.setRotate(imgView.getRotate() + 90);
	}

	// returns true if box is available else false;
	public boolean canGo() {
		if (Character.isLetter(id.charAt(0)) && !id.equals("F"))
			return true;
		return false;
	}
}

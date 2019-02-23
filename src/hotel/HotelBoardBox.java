package hotel;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	private HotelCard hotelCard = null;
	private HotelPlayer player = null;

	private Color c;
	private Image img;
	private Image pawn;
	private Image hammer;
	private ImageView imgView;
	private ImageView pawnView;
	private ImageView hammerView;
	EventHandler<MouseEvent> clickBoxHandler;

	public HotelBoardBox(String input, int x, int y) throws IOException {
		System.out.println("HotelBoardBox.java: Constructor");
		this.x = x;
		this.y = y;
		this.id = input;
		
		if(Character.isLetter(input.charAt(0)) && !input.equals("F")) {
			pawnView = new ImageView();
			if(input.equals("S")) 
				imgView = new ImageView();
			else if(input.equals("E")){
				hammerView = new ImageView();
			}
		}

		rect = new Rectangle(35, 45);
		if (HotelGUI.getFinalColors().containsKey(input))
			tmpColor = HotelGUI.getFinalColors().get(input);
		else {
			tmpColor = HotelGUI.getColors().remove();
			HotelGUI.setFinalColors(input, tmpColor);
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
				if (HotelGame.getStopFlag() == 0) {
					System.out.println("HotelBoardBox.java: Clicked Box x = " + x + " y = " + y + " ID = " + id);

					if (hotelCard != null) {
						hotelCard.hotelCardDialogBox();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Hotel Box Information");
						alert.setHeaderText(id);
						alert.showAndWait();
					}

				}
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

	public void setPawn(Image i) {
		pawn = i;
		pawnView.setImage(pawn);
		pawnView.setFitHeight(25);
		pawnView.setFitWidth(25);
		/*
		if (getNext()._getX() < _getX()) {
			pawnView.setScaleY(-1);
			rotateImageView(pawnView,3);
		}
		else if (getNext()._getX() > _getX()) {
		//	pawnView.setScaleY(-1);
			rotateImageView(pawnView,1);
		}
		else if (getNext()._getY() <_getY()) {
			System.out.println("Next y = "+getNext()._getY() + " curr y = " + _getY());
			//pawnView.setScaleY(-1);
			rotateImageView(pawnView,2);
		}
		*/
		getChildren().add(pawnView);
	}

	public void setArrow() {
		img = HotelFileReader.getArrow();
		imgView.setImage(img);
		imgView.setFitHeight(20);
		imgView.setFitWidth(20);
		getChildren().add(imgView);
		System.out.println("HotelBoardBox.java: Finish set arrow");
	}

	public void rotateImageView(ImageView view, int times) {
		System.out.println("HotelBoardBox.java : Kanw rotate " + times +" fores");
		for (int i = 0; i < times; i++)
			view.setRotate(view.getRotate() + 90);

	}
	
	public void setHammer() {
		hammer = HotelFileReader.getHammer();
		hammerView.setImage(hammer);
		hammerView.setFitHeight(20);
		hammerView.setFitWidth(20);
		getChildren().add(hammerView);
	}
	// returns true if box is available else false;
	public boolean canGo() {
		if (Character.isLetter(id.charAt(0)) && !id.equals("F"))
			return true;
		return false;
	}

	public void setLabel(String i) {
		getChildren().add(new Label(i));
	}

	public void setHotelCard(HotelCard h) {
		this.hotelCard = h;
	}

	public HotelCard getHotelCard() {
		return hotelCard;
	}
	
	public ImageView getImageView() {
		return imgView;
	}
	
	public ImageView getPawnView() {
		return pawnView;
	}
	
	public void removePawn() {
		getChildren().remove(pawnView);	
	}
	
	public boolean hasPlayer() {
		if(player == null ) return false;
		return true;
	}
	
	public void setPlayer(HotelPlayer p) {
		player = p;
	}
	
	public HotelPlayer getPlayer() {
		return player;
	}
}

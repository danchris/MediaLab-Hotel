package hotel;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	private HotelCard entrance = null;
	private HotelPlayer entranceOwner = null;
	private Color c;
	private Image img;
	private Image pawn;
	private Image hammer;
	private ImageView imgView;
	private ImageView pawnView;
	private ImageView hammerView;
	EventHandler<MouseEvent> clickBoxHandler;
	private Circle cirEntrance = null;

	/*
	 * @param input String from file reader to identify the box
	 * @param x integer for x coor
	 * @param y integer for y corr
	 */
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
			//	if (HotelGame.getStopFlag() == 0) {
					System.out.println("HotelBoardBox.java: Clicked Box x = " + x + " y = " + y + " ID = " + id);

					if (hotelCard != null) {
						hotelCard.hotelCardDialogBox();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Hotel Box Information");
						alert.setHeaderText(id);
						alert.showAndWait();
					}
				//}


			}
		};
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, clickBoxHandler);

	}
	
	/*
	 * @return x coordinate
	 */

	public int _getX() {
		return x;
	}

	/*
	 * @return y coordinate
	 */

	public int _getY() {
		return y;
	}

	/*
	 * @return id of box
	 */

	public String getID() {
		return id;
	}

	/*
	 * @return next box 
	 */

	public HotelBoardBox getNext() {
		return next;
	}

	/*
	 * @param next box
	 */
	public void setNext(HotelBoardBox next) {
		this.next = next;
	}

	/*
	 * @return 
	 */
	public void printHotelBoardBox() {
		System.out.print(this.id + " ");
	}

	/*
	 * @param Image of pawn/car
	 */
	public void setPawn(Image i) {
		pawn = i;
		pawnView.setImage(pawn);
		pawnView.setFitHeight(25);
		pawnView.setFitWidth(25);
		
		if (getNext()._getX() < _getX()) {
			//pawnView.setScaleY(-1);
			System.out.println("HotelBoardBox.java: 1o if");
			rotateImageView(pawnView,3);
		}
		if (getNext()._getX() > _getX()) {
		//	pawnView.setScaleY(-1);
			System.out.println("HotelBoardBox.java: 2o if");
			rotateImageView(pawnView,1);
		}
		if (getNext()._getY() <_getY()) {
			System.out.println("Next y = "+getNext()._getY() + " curr y = " + _getY());
			System.out.println("HotelBoardBox.java: 3o if");
			pawnView.setScaleY(-1);
			rotateImageView(pawnView,2);
		}
		
		getChildren().add(pawnView);
	}

	/*
	 * @return
	 */
	public void setArrow() {
		img = HotelFileReader.getArrow();
		imgView.setImage(img);
		imgView.setFitHeight(20);
		imgView.setFitWidth(20);
		getChildren().add(imgView);
		System.out.println("HotelBoardBox.java: Finish set arrow");
	}

	/*
	 * @param Image View
	 * @param times of rotate
	 */
	public void rotateImageView(ImageView view, int times) {
		System.out.println("HotelBoardBox.java : Kanw rotate " + times +" fores kai view rotate " + view.getRotate());
		if(view.getRotate()!=0.0) return;
		for (int i = 0; i < times; i++)
			view.setRotate(view.getRotate() + 90);

	}
	
	/*
	 * @return
	 */
	
	public void setHammer() {
		hammer = HotelFileReader.getHammer();
		hammerView.setImage(hammer);
		hammerView.setFitHeight(20);
		hammerView.setFitWidth(20);
		getChildren().add(hammerView);
	}
	
	/*
	 * @return true if box is available else false
	 */
	// returns true if box is available else false;
	public boolean canGo() {
		if (Character.isLetter(id.charAt(0)) && !id.equals("F"))
			return true;
		return false;
	}

	/*
	 * @param String Label
	 */
	public void setLabel(String i) {
		getChildren().add(new Label(i));
	}

	/*
	 * @param HotelCard hotel
	 */
	public void setHotelCard(HotelCard h) {
		this.hotelCard = h;
	}

	/*
	 * @return HotelCard hotel
	 */
	
	public HotelCard getHotelCard() {
		return hotelCard;
	}
	
	/*
	 * @return imageview
	 */
	public ImageView getImageView() {
		return imgView;
	}
	
	/*
	 * @return Pawn ImageView
	 */
	public ImageView getPawnView() {
		return pawnView;
	}
	
	/*
	 * @param ImageView of Pawn
	 */
	public void setPawnview(ImageView v) {
		this.pawnView = v;
	}
	
	/*
	 * @return
	 */
	public void removePawn() {
		getChildren().remove(pawnView);	
	}
	
	/*
	 * @return true if has player else false
	 */
	public boolean hasPlayer() {
		if(player == null ) return false;
		return true;
	}
	
	/*
	 * @param HotelPlayer Player
	 */
	public void setPlayer(HotelPlayer p) {
		player = p;
	}
	
	/*
	 * @return HotelPlayer Player
	 */
	public HotelPlayer getPlayer() {
		return player;
	}
	
	/*
	 * @return HotelCard Entrance
	 */
	public HotelCard getHotelEntrance() {
		return entrance;
	}
	
	/*
	 * @param HotelCard Entrance
	 */
	public void setHotelEntrance(HotelCard h) {
		this.entrance = h;
		//prostiki sximatos
		this.cirEntrance = new Circle(0,0,16);
		this.cirEntrance.setFill(Color.TRANSPARENT);
		
		if(HotelGame.getCurrentPlayer().getName().equals("Player 1"))
			this.cirEntrance.setStroke(Color.BLUE);
		else if(HotelGame.getCurrentPlayer().getName().equals("Player 2"))
			this.cirEntrance.setStroke(Color.RED);
		else 
			this.cirEntrance.setStroke(Color.GREEN);
		this.entranceOwner = HotelGame.getCurrentPlayer();	//set entranceOwner
		getChildren().add(this.cirEntrance);
	}
	
	public void deleteEntrance() {
		System.out.println("HotelBoardBox.java: Delete entrance");
		this.entranceOwner = null;
		this.entrance = null;
		getChildren().remove(this.cirEntrance);
	}
	/*
	 * @return circle image entrance
	 */
	public Circle getCirEntrance() {
		return cirEntrance;
	}
	
	/*
	 * @return Owner of Entrance
	 */
	public HotelPlayer getEntranceOwner() {
		return entranceOwner;
	}
	
	/*
	 * @param player, set owner of entrance
	 */
	public void setEntranceOwner(HotelPlayer p) {
		this.entranceOwner = p;
	}
	
	/*
	 * @return true if equal else false
	 */
	public boolean isThis(HotelCard h) {
		System.out.println("HotelBoardBox.java: isThis");
		if(this.hotelCard == null) return false;
		if(this.hotelCard.equals(h)) return true;
		
		return false;
	}
	
	/*
	 * @return 
	 */
	public void updateEntranceColor(){
		if(HotelGame.getCurrentPlayer().getName().equals("Player 1"))
			this.cirEntrance.setStroke(Color.BLUE);
		else if(HotelGame.getCurrentPlayer().getName().equals("Player 2"))
			this.cirEntrance.setStroke(Color.RED);
		else 
			this.cirEntrance.setStroke(Color.GREEN);
	}
}

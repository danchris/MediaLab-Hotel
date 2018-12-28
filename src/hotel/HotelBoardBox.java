package hotel;



import java.io.FileInputStream;
import java.io.IOException;

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

	private Color c;
//	private Image img;
	
	public HotelBoardBox(String input,int x, int y) throws IOException{
		this.x = x;
		this.y = y;
		this.id = input;
		
		rect = new Rectangle(34,44);
		c = Color.web(GUI.getColors().get(input), 0.9);
		rect.setFill(c);
		rect.setStroke(Color.BLACK);
	//	img = new Image(new FileInputStream("/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/Hotel/imgs/green.png"));
	 //   ImageView imageView = new ImageView(img); 
	  //  imageView.setFitHeight(20);
	   // imageView.setFitWidth(20);
		getChildren().add(rect);
	//	getChildren().add(imageView);
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
	
	public void printHotelBoardBox() {
		System.out.print(this.id+" ");
	}
}

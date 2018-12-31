package hotel;

import javafx.scene.image.ImageView;

public class HotelImageView extends ImageView implements Cloneable{
	
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

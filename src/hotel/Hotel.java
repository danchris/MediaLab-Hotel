package hotel;

import javafx.application.Application;

/*
 * @author Daniel Christodoulopoulos
 */
public class Hotel {

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("Hotel.java: Launch new Application");
			Application.launch(GUI.class, args);
		} catch (Exception ex) {
			System.out.println("Hotel.java: Exception catch");
			System.out.println(ex);
		}
	}
}

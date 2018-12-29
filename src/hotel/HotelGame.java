package hotel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelGame extends Application{
	
	private static GUI gui;
	private static Player player1;
	private static Player player2;
	private static Player player3;
	private static HotelTimer timer;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			try {
				System.out.println("GUI.java: Start Application");
				gui = new GUI(primaryStage);
			//	createGUI(primaryStage);
			//	startGame(primaryStage);

			} catch (Exception ex) {
				System.out.println("GUI.java: Exception throw");
				System.out.println(ex);
			}
		});
		
	}
	
	@Override
	public void stop() {
		timer.stop();
		System.out.println("GUI.java: Hotel Application Exiting...");
		Platform.exit();
	}

}

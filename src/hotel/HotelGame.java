package hotel;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelGame extends Application{
	
	private static GUI gui;
	private static HotelTimer timer;
	private static ArrayList<HotelPlayer> playerList;
	private static int startX;
	private static int startY;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			try {
				System.out.println("HotelGame.java: Start Application");
				gui = new GUI(primaryStage);
				gui.getInfoBar().getAvailableHotels().setText("Available Hotels: 0");
				startX = gui.getHotelBoards().getGameBoard().getStartBox()._getX();
				startY = gui.getHotelBoards().getGameBoard().getStartBox()._getY();
				playerList = new ArrayList<HotelPlayer>();
				for(int i = 1; i <= 3; i++) 
					playerList.add(new HotelPlayer("Player "+i,startX,startY,12000));
				Collections.shuffle(playerList);
				System.out.println("HotelGame.java: Tha arxisei prwtos o "+ playerList.get(0).getName());

				timer = new HotelTimer(gui.getInfoBar());
				timer.start();


			} catch (Exception ex) {
				System.out.println("HotelGame.java: Exception throw");
				System.out.println(ex);
			}
		});
		
	}
	
	@Override
	public void stop() {
		if (timer != null) timer.stop();
		System.out.println("HotelGame.java: Hotel Application Exiting...");
		Platform.exit();
	}
	
	public void startNewGame(Stage primaryStage) {
		// TODO Start new game Timer etc.
		System.out.println("GUI.java: Start New Game");
		timer.stop();
		timer.start();
	}

}

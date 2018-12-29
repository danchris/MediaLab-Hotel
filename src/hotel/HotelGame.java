package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelGame extends Application{
	
	private static HotelGUI gui;
	private static HotelTimer timer;
	private static ArrayList<HotelPlayer> playerList;
	private static int startX;
	private static int startY;
	private Alert alert;
	private int counter;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			try {
				System.out.println("HotelGame.java: Start Application");
				gui = new HotelGUI(primaryStage);
				gui.getInfoBar().getAvailableHotels().setText("Available Hotels: 0");
				startX = gui.getHotelBoards().getGameBoard().getStartBox()._getX();
				startY = gui.getHotelBoards().getGameBoard().getStartBox()._getY();
				playerList = new ArrayList<HotelPlayer>();
				for(int i = 1; i <= 3; i++) 
					playerList.add(new HotelPlayer("Player "+i,startX,startY,12000));
				Collections.shuffle(playerList);
				System.out.println("HotelGame.java: Tha arxisei prwtos o "+ playerList.get(0).getName());

				gameStartMessage();
				boardSelectedMessage();
				playersOrderingMessage();
				timer = new HotelTimer(gui.getInfoBar());
				timer.start();
				counter = 0;
				
				// Set starting pawn in the star box
				gui.getHotelBoards().getGameBoard().getGridBoard()[startX][startY].setPawn(playerList.get(0).getImg());


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

	public void playersOrderingMessage() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Players Ordering");
		GridPane grid = new GridPane();
		
		for (int i = 0; i < playerList.size(); i++) {
			grid.add(new Label(i+1+" : "), 0, i);
			grid.add(new Label(playerList.get(i).getName()), 1, i);
		}
        alert.getDialogPane().setContent(grid);
		alert.showAndWait();
	}
	
	public void gameStartMessage() {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Start New Game");
		alert.setHeaderText("Start New Game");
		alert.setContentText("Are you ready ?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    System.out.println("New Game will start right now !!!");
		} else {
		    System.out.println("Cancel");
		}
	}
	
	public void boardSelectedMessage() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Board Selected Randomly");
		alert.setContentText("Name of Board : " + gui.getFileReader().getFolderName());
		alert.showAndWait();
	}
}

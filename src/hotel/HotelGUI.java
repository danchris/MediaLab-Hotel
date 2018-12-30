package hotel;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelGUI {

	private static Scene scene;
	private static BorderPane rootPane;
	private VBox topContainer;
	private HotelMenuBar menuBar;
	private HotelInfoBar InfoBar;
	private HotelBoards Boards;
	private  HotelFileReader fileReader;
	private String[][] board;
	private static Map<String,String> finalColors;
	
	 
	static {
		finalColors = new HashMap<String,String>();
		finalColors.put("S", "#fffac8");
		finalColors.put("C", "#fffac8");
		finalColors.put("B", "#fffac8");
		finalColors.put("H", "#fffac8");
		finalColors.put("E", "#fffac8");
		finalColors.put("F", "#000000");
	}
	
	private static Queue<String> colors;
	static {
		colors = new LinkedList<String>();
		colors.add("#3cb44b");
		colors.add("#ffe119");
		colors.add("#4363d8");
		colors.add("#f58231");
		colors.add("#911eb4");
		colors.add("#42d4f4");
		colors.add("#aaffc3");
		colors.add("#bfef45");
		};

	public static Map<String,String> getFinalColors(){
		return finalColors;
	}
	
	public static void setFinalColors(String key, String value) {
		finalColors.put(key, value);
	}
	
	public static Queue<String> getColors() {
		return colors;
	}
	

	public HotelInfoBar getInfoBar() {
		return InfoBar;
	}

	public HotelFileReader getFileReader() {
		return fileReader;
	}

	public HotelBoards getHotelBoards() {
		return Boards;
	}

	/*
	 * h create gui ftiaxnei to gui, orizei titlo, kai kalei tis sunartiseis create
	 * menuber, create infobar kai create board, episis ksekinaei to scene
	 */
	/*
	public HotelGUI(Stage primaryStage){
		// TODO Copy gui creation here maybe in init...
		System.out.println("GUI.java: Create GUI");

	}
	*/

	public void fileReaderTurnOn() {
		try {
			fileReader = new HotelFileReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("HotelGUI.java : file reader error ");
			e.printStackTrace();
		}	
	}

	public void createMainWindow(Stage primaryStage) {
		primaryStage.setTitle("MediaLab Hotel");
		rootPane = new BorderPane();
		topContainer = new VBox();
		menuBar = new HotelMenuBar();
		topContainer.getChildren().add(menuBar);
		//topContainer.getChildren().addAll(menuBar, InfoBar);
		rootPane.setTop(topContainer);
		
		scene = new Scene(rootPane, 800, 600);


		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	public void createBoards() {
		InfoBar = new HotelInfoBar();
		topContainer.getChildren().add(InfoBar);
		board = fileReader.getBoard();
		try {
			Boards = new HotelBoards(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("HotelGUI.java : board reader error createBoards");
			e.printStackTrace();
		}
		rootPane.setLeft(Boards.getGameBoard());
		rootPane.setRight(Boards.getToolBox());
	}

	/*
	public void startGame(Stage primaryStage) {
		// TODO Start new game Timer etc.
		System.out.println("GUI.java: Start New Game");
		timer.stop();
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
		System.out.println("GUI.java: Hotel Application Exiting...");
		Platform.exit();
	}
*/
}

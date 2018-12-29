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
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */


public class GUI {

	private static Scene scene;
	private static BorderPane rootPane;
	private AnimationTimer timer;
	private VBox topContainer;
	private HotelMenuBar menuBar;
	private HotelInfoBar InfoBar;
	private HotelBoards Boards;
	private  HotelFileReader fileReader;
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
	public GUI(Stage primaryStage) throws IOException {
		try {
			// TODO Copy gui creation here maybe in init...
			System.out.println("GUI.java: Create GUI");

			primaryStage.setTitle("MediaLab Hotel");
			rootPane = new BorderPane();
			topContainer = new VBox();
			fileReader = new HotelFileReader();
			String[][] board = fileReader.getBoard();

			menuBar = new HotelMenuBar(primaryStage, timer);
			InfoBar = new HotelInfoBar();
			Boards = new HotelBoards(board);
			
			topContainer.getChildren().addAll(menuBar, InfoBar);
		//	System.out.println("Ela");
		//	System.out.println("Start is " + Boards.getStartBox()._getX() + " " + Boards.getStartBox()._getY());
			rootPane.setTop(topContainer);
			rootPane.setCenter(Boards);
			scene = new Scene(rootPane, 800, 600);
	        Boards.prefWidthProperty().bind(scene.widthProperty());
	        Boards.prefHeightProperty().bind(scene.heightProperty());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (IOException e) {
			System.out.println("EEEE");
			e.printStackTrace();
		}
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

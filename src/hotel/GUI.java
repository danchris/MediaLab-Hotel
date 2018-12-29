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
public class GUI extends Application {

	private AnimationTimer timer;
	private static BorderPane rootPane;
	private static VBox topContainer;
	private static HotelMenuBar menuBar;
	private static HotelInfoBar InfoBar;
	private static HotelBoards Boards;
	private static Scene scene;
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
	

	/*
	 * h init ksekinaei ton timer (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	public void init() {

		System.out.println("GUI.java: Init Application");
		timer = new AnimationTimer() {

			private static final long STOPPED = -1;
			private long startTime = STOPPED;

			@Override
			public void handle(long timestamp) {
				if (startTime == STOPPED) {
					startTime = timestamp;
				}
				long elapsedNanos = timestamp - startTime;
				long elapsedMillis = elapsedNanos / 1_000_000;
				InfoBar.getTotalTime().setText("Total Time: " + String.format("%02d : %02d",
						TimeUnit.MILLISECONDS.toHours(elapsedMillis), TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
								- TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedMillis))));
			}

			@Override
			public void stop() {
				startTime = STOPPED;
				super.stop();
			}
		};
	}

	/*
	 * h start ftiaxnei to GUI kai ksekinaei to stage (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) throws Exception {
		Platform.runLater(() -> {
			try {
				System.out.println("GUI.java: Start Application");
				createGUI(primaryStage);
				startGame(primaryStage);

			} catch (Exception ex) {
				System.out.println("GUI.java: Exception throw");
				System.out.println(ex);
			}
		});

	}

	/*
	 * h create gui ftiaxnei to gui, orizei titlo, kai kalei tis sunartiseis create
	 * menuber, create infobar kai create board, episis ksekinaei to scene
	 */
	public void createGUI(Stage primaryStage) throws IOException {
		try {
			// TODO Copy gui creation here maybe in init...
			System.out.println("GUI.java: Create GUI");

			primaryStage.setTitle("MediaLab Hotel");
			rootPane = new BorderPane();
			topContainer = new VBox();
			HotelFileReader fileReader = new HotelFileReader();
			String[][] board = fileReader.getBoard();
			//HashMap<Integer,HotelCard> cards = new ArrayList<HotelCard>();
			//cards = fileReader.getHotelsCards();
			//for (HotelCard tmp : cards) {
		//		System.out.println("GUI.java: Tupwnw tis cards");
		//		tmp.printHotelCard();
		//	}
			menuBar = new HotelMenuBar(primaryStage, timer);
			InfoBar = new HotelInfoBar();
			Boards = new HotelBoards(board);
			
			topContainer.getChildren().addAll(menuBar, InfoBar);
			System.out.println("Ela");
			System.out.println("Start is " + Boards.getStartBox()._getX() + " " + Boards.getStartBox()._getY());
			rootPane.setTop(topContainer);
			rootPane.setCenter(Boards);
			scene = new Scene(rootPane, 800, 600);
	        Boards.prefWidthProperty().bind(scene.widthProperty());
	        Boards.prefHeightProperty().bind(scene.heightProperty());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

}

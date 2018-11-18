package hotel;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * @author Daniel Christodoulopoulos
 */
public class GUI extends Application {

	private static int minutes = 0;
	private static int seconds = 0;
//	private AnimationTimer timer;
	private Timer timer;

	public void start(Stage primaryStage) throws Exception {
		Platform.runLater(() -> {
			try {
				System.out.println("GUI.java: Create GUI");

				primaryStage.setTitle("MediaLab Hotel");
				BorderPane rootPane = new BorderPane();
				VBox topContainer = new VBox();

				// Menu

				MenuBar menuBar = new MenuBar();
				Menu Game = new Menu("Game");
				Menu Statistics = new Menu("Statistics");
				MenuItem Start = new MenuItem("Start");
				MenuItem Stop = new MenuItem("Stop");
				MenuItem Cards = new MenuItem("Cards");
				MenuItem Exit = new MenuItem("Exit");
				MenuItem Hotels = new MenuItem("Hotels");
				MenuItem Entrances = new MenuItem("Entrances");
				MenuItem Profits = new MenuItem("Profits");
				Game.getItems().addAll(Start, Stop, Cards, Exit);
				Statistics.getItems().addAll(Hotels, Entrances, Profits);
				menuBar.getMenus().add(Game);
				menuBar.getMenus().add(Statistics);

				Start.setOnAction(actionEvent -> startGame(primaryStage));
				Stop.setOnAction(actionEvent -> stopGame(primaryStage));
				Exit.setOnAction(actionEvent -> Platform.exit());

				// Info Bar
				TilePane InfoBar = new TilePane();
				InfoBar.setOrientation(Orientation.HORIZONTAL);
				InfoBar.setTileAlignment(Pos.CENTER);
				InfoBar.setHgap(50);
				Text Player1 = new Text("Player 1: ");
				Text Player2 = new Text("Player 2: ");
				Text Player3 = new Text("Player 3: ");
				Text AvailableHotels = new Text("Available Hotels: ");
				Text TotalTime = new Text("Total Time: ");
				// InfoBar.setSpacing(200);
				InfoBar.getChildren().add(Player1);
				InfoBar.getChildren().add(Player2);
				InfoBar.getChildren().add(Player3);
				InfoBar.getChildren().add(AvailableHotels);
				InfoBar.getChildren().add(TotalTime);

				topContainer.getChildren().addAll(menuBar, InfoBar);

				// Boards
				SplitPane Boards = new SplitPane();

				VBox GameBoard = new VBox();
				ToolBar toolBar = new ToolBar();
				toolBar.setOrientation(Orientation.VERTICAL);
				GameBoard.setMaxSize(800, 600);
				toolBar.setMaxSize(300,600);

				Boards.getItems().addAll(GameBoard, toolBar);

				rootPane.setTop(topContainer);
				rootPane.setCenter(Boards);
				Scene scene = new Scene(rootPane, 800, 600);


				primaryStage.setScene(scene);
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
				    @Override
				    public void run() {
				    	seconds++;
				    	if (seconds==60) {
				    		minutes++;
				    		if (minutes == 60) minutes = 0;
				    		seconds = 0;
				    	}
				    	
				    	if (seconds < 10 && minutes < 10) TotalTime.setText("Total Time: 0"+minutes+":0"+seconds);
				    	else if (seconds < 10 && minutes >= 10) TotalTime.setText("Total Time: "+minutes+":0"+seconds);
				    	else if (seconds >= 10 && minutes < 10) TotalTime.setText("Total Time: 0"+minutes+":"+seconds);
				    	else TotalTime.setText("Total Time: "+minutes+":"+seconds);
				    }
				}, 0, 1000);

				
				primaryStage.show();

			} catch (Exception ex) {
				System.out.println("GUI.java: Exception throw");
				System.out.println(ex);
			}
		});

	}
	
	
	public void createGUI(Stage primaryStage) {
		//TODO Copy gui creation here maybe in init...
	}
	public void startGame(Stage primaryStage) {
		//TODO Start new game Timer etc.
		System.out.println("Start New Game");
	}
	
	public void stopGame(Stage primaryStage) {
		//TODO Stop Game stop timer etc.
		System.out.println("Stop Game");
	}

	@Override
	public void stop() {
		timer.cancel();
		timer.purge();
		System.out.println("GUI.java: Hotel Application Exiting...");
		Platform.exit();
	}
}

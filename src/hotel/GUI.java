package hotel;


import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * @author Daniel Christodoulopoulos
 */
public class GUI extends Application {


	private AnimationTimer timer;
	private static BorderPane rootPane;
	private static VBox topContainer;
	private static HotelMenuBar menuBar;
	private static TilePane InfoBar;
	private static Text Player1;
	private static Text Player2;
	private static Text Player3;
	private static Text AvailableHotels;
	private static Text TotalTime;
	private static SplitPane Boards;
	private static VBox GameBoard;
	private static ToolBar toolBar;
	private static Scene scene;

	

	
	/*
	 * h init ksekinaei ton timer
	 * (non-Javadoc)
	 * @see javafx.application.Application#init()
	 */
	public void init() {
		
		System.out.println("GUI.java: Init Application");
		timer = new AnimationTimer() {

		    private static final long STOPPED = -1 ;
		    private long startTime = STOPPED ;

		    @Override
		    public void handle(long timestamp) {
		        if (startTime == STOPPED) {
		            startTime = timestamp ;
		        }
		        long elapsedNanos = timestamp - startTime ;
		        long elapsedMillis = elapsedNanos / 1_000_000 ;
		        TotalTime.setText("Total Time: " + String.format("%02d : %02d", 
		        	    TimeUnit.MILLISECONDS.toHours(elapsedMillis),
		        	    TimeUnit.MILLISECONDS.toMinutes(elapsedMillis) - 
		        	    TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedMillis))
		        	));
		    }

		    @Override
		    public void stop() {
		        startTime = STOPPED ;
		        super.stop();
		    }
		};
	}
	
	/*
	 * h start ftiaxnei to GUI kai ksekinaei to stage
	 * (non-Javadoc)
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
	 * h create gui ftiaxnei to gui, orizei titlo, kai kalei tis sunartiseis
	 * create menuber, create infobar kai create board,
	 * episis ksekinaei to scene
	 */
	public void createGUI(Stage primaryStage) {
		//TODO Copy gui creation here maybe in init...
		System.out.println("GUI.java: Create GUI");

		primaryStage.setTitle("MediaLab Hotel");
		rootPane = new BorderPane();
		topContainer = new VBox();
		
		createMenuBar(primaryStage);
		createInfoBar(primaryStage);
		createBoards(primaryStage);
		
		topContainer.getChildren().addAll(menuBar, InfoBar);

		rootPane.setTop(topContainer);
		rootPane.setCenter(Boards);
		scene = new Scene(rootPane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	
	/*
	 * ftiaxnei ta boards, diladi to game board kai to toolbox
	 */
	public void createBoards(Stage primaryStage) {
		
		System.out.println("GUI.java: Create Boards");
		// Boards
		Boards = new SplitPane();

		GameBoard = new VBox();
		toolBar = new ToolBar();
		toolBar.setOrientation(Orientation.VERTICAL);
		GameBoard.setPrefSize(600, 600);
		toolBar.setPrefSize(200, 600);
		Boards.getItems().addAll(GameBoard, toolBar);
		

	}
	
	/*
	 * ftiaxnei to infobar
	 */
	public void createInfoBar(Stage primaryStage){
		System.out.println("GUI.java: Create Info Bar");
		// Info Bar
		InfoBar = new TilePane();
		InfoBar.setOrientation(Orientation.HORIZONTAL);
		InfoBar.setTileAlignment(Pos.CENTER);
		InfoBar.setHgap(40);
		Player1 = new Text("Player 1: ");
		Player2 = new Text("Player 2: ");
		Player3 = new Text("Player 3: ");
		AvailableHotels = new Text("Available Hotels: ");
		TotalTime = new Text("Total Time: ");
		Player1.setFill(Color.BLUE);
		Player2.setFill(Color.RED);
		Player3.setFill(Color.GREEN);
		// InfoBar.setSpacing(200);
		InfoBar.getChildren().add(Player1);
		InfoBar.getChildren().add(Player2);
		InfoBar.getChildren().add(Player3);
		InfoBar.getChildren().add(AvailableHotels);
		InfoBar.getChildren().add(TotalTime);
	}	
	

	/*
	 * ftiaxnei to menubar
	 */
	public void createMenuBar(Stage primaryStage) {
		// Menu

		System.out.println("GUI.java: Create Menu Bar object");
		menuBar = new HotelMenuBar(primaryStage,timer);
	}
	

	public void startGame(Stage primaryStage) {
		//TODO Start new game Timer etc.
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

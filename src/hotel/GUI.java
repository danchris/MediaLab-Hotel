package hotel;


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
		        InfoBar.getTotalTime().setText("Total Time: " + String.format("%02d : %02d", 
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
		
		menuBar = new HotelMenuBar(primaryStage,timer);
		InfoBar = new HotelInfoBar();
		Boards = new HotelBoards();
		
		
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

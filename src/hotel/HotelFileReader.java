package hotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelFileReader {

	private String[][] array;
	private static ArrayList<HotelCard> hotelsCards;
	private String gameDir;
	private static Image arrow;
	private static Image green;
	private static Image blue;
	private static Image red;
	private static Image hammer;
	private static Image[] dice;
	private int hotelsCount;
	private File folder;
	private static File imagesFolder;

	public HotelFileReader() throws IOException {

		try {

			gameDir = new File("").getAbsolutePath();
			FileReader file;
			BufferedReader reader;
			// gameBoardsFolder o fakelos me tous fakelous twn boards
			File gameBoardsFolder = new File(gameDir + "/boards");
			
			// folder o fakelos pou epilextike tuxaia apo ta pithana boards
			File[] listOfFolders = gameBoardsFolder.listFiles();
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
		        return file.isDirectory();
		        }
			};
			listOfFolders = gameBoardsFolder.listFiles(fileFilter);
			
			String ret = "";
			if(listOfFolders.length == 0) {
				HotelMessenger.generalInfoMessage("Error", "Error Folders", "Boards Not Found!");
				HotelGame.stopGame();
			}
			else {
				List<String> choices = new ArrayList<String>();
				for(int i = 0; i < listOfFolders.length;i++) {
					File folderName = listOfFolders[i];
					choices.add(folderName.toString());
				}
				ret = HotelMessenger.chooseFolderBoard(choices);
				System.out.println("HotelFileReader.java: Return folder" + ret);
			}
	
			if(ret==null || ret.equals("")) HotelGame.killGame();
			folder = new File(ret);
			File[] listOfFiles = folder.listFiles();
			hotelsCount = listOfFiles.length - 1;
			file = new FileReader(gameBoardsFolder + "/" + folder.getName() + "/board.txt");
			reader = new BufferedReader(file);
			readBoard(reader);
			readImages();
			HotelCard tmpHotelCard;
			hotelsCards = new ArrayList<HotelCard>();
			for (File tmp : listOfFiles) {
				String n = tmp.getName();
				System.out.println("TO onoma tou arxeiou einai " + n);
				if (!tmp.getName().equals("board.txt")) {
					file = new FileReader(gameBoardsFolder + "/" + folder.getName() +"/"+ n);
					reader = new BufferedReader(file);
					tmpHotelCard = new HotelCard(readHotelCards(reader),Integer.parseInt(n.substring(0, n.lastIndexOf('.'))));
					hotelsCards.add(tmpHotelCard);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[][] getBoard() {
		return array;
	}

	public static ArrayList<HotelCard> getHotelsCards() {
		return hotelsCards;
	}

	// read the file board.txt and initialize string[][] array
	private void readBoard(BufferedReader reader) throws IOException {

		String line;
		String[] tmp;
		array = new String[12][15];
		for (int i = 0; i < 12; i++) {
			line = reader.readLine();
			tmp = line.split(",");
			for (int j = 0; j < 15; j++)
				array[i][j] = tmp[j];
		}
		reader.close();

	}
	
	private void readImages() throws IOException{
		System.out.println("HotelFileReader.java: readImages");
		imagesFolder = new File(gameDir + "/imgs");
		try {
			green = new Image(new FileInputStream(imagesFolder+"/greencar.png"));
			blue = new Image(new FileInputStream(imagesFolder+"/bluecar.png"));
			red = new Image(new FileInputStream(imagesFolder+"/redcar.png"));
			arrow = new Image(new FileInputStream(imagesFolder+"/arrow.png"));
			hammer = new Image(new FileInputStream(imagesFolder+"/hammer.png"));
			dice = new Image[6];
			for(int i = 0; i <6; i++) {
				dice[i] = new Image(new FileInputStream(imagesFolder+"/dice"+(i+1)+".png"));
			}
		} catch (FileNotFoundException e) {
			System.out.println("HotelFileReader.java: png error file not found");
			e.printStackTrace();
		}		
	}
	

	// read one hotel card file to list of strings
	private ArrayList<String> readHotelCards(BufferedReader reader) throws IOException {
		System.out.println("HotelFileReader.java: readHotelCards");
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		String[] tmp = null;
		while ((line = reader.readLine()) != null) {
			tmp = line.split(",");
			for (String tmpS : tmp)
				lines.add(tmpS);
		}
		reader.close();

		return lines;
	}
	
	public String getGameLocation() {
		return gameDir;
	}

	// Pick a random element from array
	public static File getRandom(File[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}
	

	public static Image getPawn(String i) {
		if (i.equals("green")) return green;
		if (i.equals("blue")) return blue;
		if (i.equals("red")) return red;
		
		return null;
	}

	public static Image getArrow() {
		return arrow;
	}
	
	public static Image getHammer() {
		return hammer;
	}
	
	public static HotelCard get(int id) {
		for (HotelCard i : hotelsCards) {
			if (i.getID()==id) return i;
		}
		
		return null;
	}
	
	public void setHotelsCount(int c) {
		hotelsCount = c;
	}
	
	public int getHotelsCount() {
		return hotelsCount;
	}
	
	public String getFolderName() {
		return folder.getName();
	}
	
	public static Image getDice(int n) {
		return dice[n-1];
	}
}

package hotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
 * @author Daniel Christodoulopoulos
 */
public class HotelFileReader {

	private String[][] array;
	private ArrayList<HotelCard> hotelsCards;

	public HotelFileReader() throws IOException {

		try {

			String gameDir = new File("").getAbsolutePath();
			FileReader file;
			BufferedReader reader;
			// gameBoardsFolder o fakelos me tous fakelous twn boards
			File gameBoardsFolder = new File(gameDir + "/boards");
			File[] listOfFolders = gameBoardsFolder.listFiles();
			
			// folder o fakelos pou epilextike tuxaia apo ta pithana boards
			File folder = getRandom(listOfFolders);
			File[] listOfFiles = folder.listFiles();
			file = new FileReader(gameBoardsFolder + "/" + folder.getName() + "/board.txt");
			reader = new BufferedReader(file);
			readBoard(reader);
			HotelCard tmpHotelCard;
			hotelsCards = new ArrayList<HotelCard>();
			for (File tmp : listOfFiles) {
				String n = tmp.getName();
				System.out.println("TO onoma tou arxeiou einai " + n);
				if (!tmp.getName().equals("board.txt")) {
					file = new FileReader(gameBoardsFolder + "/" + folder.getName() +"/"+ n);
					reader = new BufferedReader(file);
					tmpHotelCard = new HotelCard(readHotelCards(reader));
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

	public ArrayList<HotelCard> getHotelsCards() {
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

	// Pick a random element from array
	public static File getRandom(File[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

}

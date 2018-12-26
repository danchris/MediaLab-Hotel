package hotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HotelFileReader {

	private String[][] array;
	
	public String[][] getBoard() {
		return array;
	}
	public HotelFileReader() throws IOException {

		try {

			BufferedReader reader = new BufferedReader(
					new FileReader("/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/boards/default/board.txt"));
			String line;
			String[] tmp;
			array = new String[12][15];
			for (int i = 0; i < 12; i++) {
				line = reader.readLine();
				tmp = line.split(",");
				System.out.println(tmp.length);
				for(int j = 0; j < 15; j++) array[i][j] = tmp[j];
				//System.out.println(Arrays.toString(array));
				//System.out.println(array);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

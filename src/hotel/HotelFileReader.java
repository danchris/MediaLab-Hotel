package hotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HotelFileReader {

	public HotelFileReader() throws IOException {

		try {

			BufferedReader reader = new BufferedReader(
					new FileReader("/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/boards/default/board.txt"));
			String line;
			String[] array;
			for (int i = 0; i < 12; i++) {
				line = reader.readLine();
				array = line.split(",");
				System.out.println(Arrays.toString(array));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

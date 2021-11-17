import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PresetModelHandler {

	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	private int[][] pentomino = {

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 1, 1, 0, 0, 0 },

			{ 0, 0, 0, 1, 1, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 1, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */

	private int[][] spaceships = {

			{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1 },

			{ 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0 },

			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 },

			{ 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 },

			{ 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },

			{ 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },

			{ 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 },

			{ 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1 },

			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 },

			{ 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 },

			{ 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1 },

	};
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */

	private int[][] eater = {

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, } };
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */

	private int[][] glider = {

			{ 0, 0, 1, 0, 0, 0 },

			{ 0, 0, 0, 1, 0, 0 },

			{ 0, 1, 1, 1, 0, 0 },

			{ 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0 } };
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */

	private int[][] pulsar = {

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 },

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 0, 1, 1, 0, 1, 1, 0, 1, 1, 0 },

			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 } };
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	public String[] options = { "pentomino", "spaceships", "eater", "glider", "pulsar", "gospel-gun", "custom" };

	private boolean[][] presetModelGrid;
	private int height;
	private int width;
	private String name;

	public PresetModelHandler(String configName, int w, int h) {
		this.height = w;
		this.width = h;
		this.name = configName;

	}

	public PresetModelHandler(int w, int h) {
		this.height = w;
		this.width = h;
		this.name = "";
	}

	public void setPresetModelGrid() {
		this.presetModelGrid = getPresetFromName(name);
	}

	public void setPresetModelGrid(String name) {
		this.presetModelGrid = getPresetFromName(name);
	}

	// get
	public boolean[][] getPresetModelGrid() {
		return presetModelGrid;
	}

	public boolean[][] getPresetFromCsv(String configName) {
		String filename = String.join(".", configName, "csv");
		int[][] unpadded = presetFromCsvHelper(filename);
		return padAndConvertIntArrayToBoolArray(unpadded, 0, unpadded[0].length, unpadded.length);
	}

	// get and set for height and width
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/* --------------------------------------------------------------- */

	private boolean[][] padAndConvertIntArrayToBoolArray(int[][] arr, int padWith, int maxWidth, int maxHeight) {

		// int[][] temp = new int[arr.length + verPadLength * 2][arr[0].length +
		// horPadLength * 2];
		int verPadLength = (maxHeight - arr.length) / 2;
		int horPadLength = (maxWidth - arr[0].length) / 2;
		int[][] temp = new int[arr.length + verPadLength * 2][arr[0].length + horPadLength * 2];
		boolean[][] result = new boolean[temp.length][temp[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = padWith;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				result[i + verPadLength][j + horPadLength] = arr[i][j] == 1;
			}
		}
		return result;

	}

	private int[][] presetFromCsvHelper(String fileName) {
		/*
		 * TODO TODO TODO
		 */

		/* TODO */

		// the csv will look like this:
		/*
		 * 1,0,0 1,0,1 1,1,1
		 */

		// so we need to convert it to this:
		/*
		 * { { 1, 0, 0 }, { 1, 0, 1 }, { 1, 1, 1 } };
		 */

		// first we need to read the file
		String filePath = "resources/" + fileName;
		String line = "";
		String cvsSplitBy = ",";
		int[][] result = new int[height][width];
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int i = 0;

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] row = line.split(cvsSplitBy);
				int j = 0;
				for (String s : row) {
					if (s.equals("1")) {
						result[i][j] = 1;
					} else {
						result[i][j] = 0;
					}
					j++;
				}
				i++;
			}
		} catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Error reading file");
			System.out.println(e.getMessage());
		}
		return result;

	}

	private boolean[][] getPresetFromName(String configName) {
		switch (configName) {
		case "pentomino" -> {
			return padAndConvertIntArrayToBoolArray(pentomino, 0, this.width, this.height);
		}
		case "spaceships" -> {
			return padAndConvertIntArrayToBoolArray(spaceships, 0, this.width, this.height);
		}
		case "eater" -> {
			return padAndConvertIntArrayToBoolArray(eater, 0, this.width, this.height);
		}
		case "glider" -> {
			return padAndConvertIntArrayToBoolArray(glider, 0, this.width, this.height);
		}
		case "pulsar" -> {
			return padAndConvertIntArrayToBoolArray(pulsar, 0, this.width, this.height);
		}
		case "gospel-gun" -> {
			return getPresetFromCsv(configName);
		}
		case "custom" -> {
			return getPresetFromCsv(configName);
		}
		default -> {
			return padAndConvertIntArrayToBoolArray(pentomino, 0, this.width, this.height);
		}
		}
	}

}

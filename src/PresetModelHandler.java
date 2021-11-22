
/**

 * GridModel is an abstract class that defines the basic grid structure for the
 * game.
 *
 * The grid is a 2D array of cells. Each cell has a state, which is either alive
 * or dead.
 *
 * The grid is a rectangular grid, with a fixed number of rows and columns. The
 * number of rows and columns are specified in the constructor. The grid is /* *
 * initialized to all dead cells. The grid is not intended to be used directly,
 * but rather through a subclass.
 *
 * @filename PresetModelHandler.java
 * @author YonahAviv
 *
 * Created at     : 2021-11-18 12:00:42
 * Last modified  : 2021-11-18 12:20:48
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PresetModelHandler {

	/*
	 *
	 * Class: PresetModel
	 *
	 *
	 * Description:
	 *
	 * This class is used to store preset models. The preset model is a 2D array of
	 * booleans. The width and height of the preset model is stored in the class.
	 *
	 *
	 *
	 * Fields:
	 *
	 * boolean[][] presetModel - the 2D array of booleans that represents the preset
	 * model
	 *
	 * int height - the height of the preset model
	 *
	 * int width - the width of the preset model
	 *
	 *
	 */
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

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, } };
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
	/*
	 * To customize the starting config, go to custom.csv in the configs folder and
	 * replace the 1 and 0s as u wish. it may not work.
	 */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	/* --------------------------------------------------------------- */
	public String[] options = { "pentomino", "spaceships", "eater", "glider", "pulsar", "custom" };

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
		return padAndConvertIntArrayToBoolArray(unpadded, 0, this.width, this.height);
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
		/*
		 * Description: pads the array with the padWith value and then converts it to a
		 * boolean array
		 *
		 * Input: int[][] arr, int padWith, int maxWidth, int maxHeight Output:
		 * boolean[][]
		 *
		 *
		 */

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
		 * Description:
		 *
		 * This method takes a csv file and converts it to an int[][] array. The csv
		 * file must be in the following format: 1,1,0,1,0 1,1,0,1,0 1,1,0,1,0
		 *
		 *
		 * Inputs: String fileName - the name of the file to read from Returns: int[][]
		 * - a 2D array of ints
		 */

		// first we need to read the file
		String filePath = "src" + "/configs/" + fileName;
		String line = "";
		String cvsSplitBy = ",";
		int[][] result = new int[width][height];
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			int i = 0;

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] row = line.split(cvsSplitBy);
				int j = 0;
				for (String s : row) {
					/*
					 * Logic:
					 *
					 * If the value is 1, then we set the value to int 1. If the value is 0, then we
					 * set the value to int 0.
					 */
					if (s.equals("1")) {
						result[i][j] = 1;
					} else {
						result[i][j] = 0;
					}
					j++;
				}
				i++;
				line = br.readLine();

			}
		} catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Error reading file");
			System.out.println(e.getMessage());
		}
		return result;

	}

	private boolean[][] getPresetFromName(String configName) {
		/*
		 * Description:
		 *
		 * This method takes a string and returns the preset model associated with that
		 * string. The string must be one of the following: "pentomino" "spaceships"
		 * "eater" "glider" "pulsar" "custom"
		 *
		 */
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
		case "custom" -> {
			return getPresetFromCsv(configName);

		}
		default -> {
			return getPresetFromCsv(configName);
		}
		}

	}

}

package assets;

// /**
// * Conways Game Of Life
// *
// *
// * @summary Game Of Life Simulation
// * @author Yonah Aviv
// *
// * Created at : 2021-11-10 15:59:19
// * Last modified : 2021-11-10 15:59:57
// */

// import javax.swing.*;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;
// import java.awt.*;
// import java.awt.event.*;

// class GridModel {

// /*
// * GridModel is an abstract class that defines the basic grid structure for
// * the game.
// *
// * The grid is a 2D array of cells. Each cell has a state, which is either
// alive
// * or dead.
// *
// * The grid is a rectangular grid, with a fixed number of rows and columns.
// The
// * number of rows and columns are specified in the constructor. The grid is /*
// *
// * initialized to all dead cells. The grid is not intended to be used
// directly,
// * but rather through a subclass.
// */

// private int width, height, gen;
// private boolean[][] grid;

// public GridModel(int w, int h) {
// /*
// * Description: Constructor for the GridModel class.
// *
// * Parameters: w - the width of the grid h - the height of the grid Variables
// * width - the width of the grid height - the height of the grid gen - the
// * current generation of the grid grid - the 2D array of cells Return: None
// */

// }

// public void randomPopulation(double fill) {
// /*
// * Fills a grid with random cells.
// *
// * Args: fill: The percentage of the grid that should be filled with cells.
// */

// }

// private String getStartingConfigFromUser() {
// /*
// * decription of getting the input
// *
// * the user can either enter a file name or they can enter a starting config
// * name and the program will find the starting config
// */
// return "";

// }

// public void loadStartingConfig() {
// /*
// * Loads the starting configuration from a file.Then, the grid is updated.
// */
// }

// public void newGeneration() {

// /*
// * Description:
// *
// * This method is called each time the user clicks the "Next" button, Or every
// * cycle after the user clicks the run button.
// *
// * The method should update the grid to the next generation.
// *
// * The rules for updating the grid are as follows:
// *
// * 1. Any live cell with fewer than two live neighbours dies, as if caused by
// * under-population.
// *
// * 2. Any live cell with two or three live neighbours lives on to the next
// * generation.
// *
// * 3. Any live cell with more than three live neighbours dies, as if by
// * overcrowding.
// *
// * 4. Any dead cell with exactly three live neighbours becomes a live cell, as
// * if by reproduction.
// */

// }

// public void setCell(int row, int col, boolean b) {
// /*
// * Description:
// *
// * Sets the state of the cell at the specified row and column to the specified
// * value.
// *
// * Args:
// *
// * row: The row of the cell to set.
// *
// * col: The column of the cell to set.
// *
// * b: The new state of the cell.
// */

// }

// public boolean getCell(int row, int col) {

// /*
// * Description:
// *
// * Returns the state of the cell at the specified row and column.
// *
// * Args:
// *
// * row: The row of the cell to get.
// *
// * col: The column of the cell to get.
// *
// * Returns: The state of the cell at the specified row and column.
// */
// return false;
// }

// public int getWidth() {
// // System.out.println("GridModel: Call to getWidth()");
// return width;
// }

// public int getHeight() {
// // System.out.println("GridModel: Call to getHeight()");
// return height;
// }

// public int getGen() {
// // System.out.println("GridModel: Call to getGen()");
// return gen;
// }

// public int getNumberOfNeighbours(int row, int col) {
// /*
// * Description: Encapsulation(this is the way to get the neighbours from
// outside
// * the class, however the actual logic to get neighbours is in the
// getNeighbours
// * method)
// *
// * Returns the number of live neighbours of the cell at the specified row and
// * column.
// *
// * Args:
// *
// * row: The row of the cell to get.
// *
// * col: The column of the cell to get.
// *
// * Returns: The number of live neighbours of the cell at the specified row and
// * column.
// *
// */
// return getNumberOfNeighboursHelper(row, col);

// }

// //
// --------------------------------------------------------------------------------
// // helpers
// //
// --------------------------------------------------------------------------------
// private int getNumberOfNeighboursHelper(int row, int col) {

// /*
// * Description:
// *
// * This method should return the number of live neighbours for the cell at the
// * specified row and column.
// *
// * Implementation:
// *
// *
// *
// *
// * Args:
// *
// * row: The row of the cell to check.
// *
// * col: The column of the cell to check.
// *
// * Returns: The number of live neighbours for the cell at the specified row
// and
// * column.
// */
// int neighbours = 0;
// return neighbours;
// }

// }

// class GameGUI extends JPanel {

// /*
// * Class Description:
// *
// * The GameGUI class is the main class for the game.
// *
// * It is responsible for:
// *
// * 1. Initializing the GUI.
// *
// * 2. Creating the game grid.
// *
// * 3. Updating the GUI.
// *
// * 4. Handling user input.
// *
// * The GameGUI class is a subclass of JPanel.
// */

// private GridModel model;
// private Grid grid;
// private Timer timer;

// public GameGUI(GridModel model, int size) {
// /*
// * Description:
// *
// * Initializes the GUI.
// *
// * The GUI consists of a grid of squares. Each square is a JButton. The
// buttons
// * are added to the GUI using the Grid class.
// *
// * The GUI also has three buttons:
// *
// * Step: The step button is used to advance the simulation by one step.
// *
// * Run: The run button is used to run the simulation continuously.
// *
// * Stop: The stop button is used to stop the simulation.
// *
// * The speed slider is used to control the speed of the simulation.
// *
// * The GUI also has a timer that is used to advance the simulation by one
// step.
// *
// * Args:
// *
// * model: The model to be used for the simulation.
// *
// * size: The size of the grid to create.
// */

// }

// private class StepListener implements ActionListener {
// public void actionPerformed(ActionEvent e) {
// model.newGeneration();
// grid.repaint();
// }
// }

// private class RunListener implements ActionListener {
// public void actionPerformed(ActionEvent e) {

// /*
// * Description: This method is called each time the user clicks the "Run"
// * button.
// *
// *
// * The method should start the timer to run the simulation continuously.
// */
// }
// }

// private class StopListener implements ActionListener {
// public void actionPerformed(ActionEvent e) {

// /*
// * Description:
// *
// * Stops the simulation.
// *
// * Args:
// *
// * e: The event that triggered this method.
// */

// }
// }

// private class SpeedListener implements ChangeListener {
// public void stateChanged(ChangeEvent e) {
// /*
// *
// * Description:
// *
// * This method is called each time the user moves the slider.
// *
// * The method should change the speed of the timer to match the speed of the
// * slider.
// *
// * Args:
// *
// * e: The event that triggered this method.
// */

// }
// }

// /*
// * fields:
// *
// *
// */
// }

// class Grid extends JPanel implements MouseListener {

// /*
// *
// * Class Description:
// *
// * The Grid class is used to display the grid.
// *
// * It is responsible for:
// *
// * 1. Displaying the grid.
// *
// * 2. Handling user input.
// *
// * The Grid class is a subclass of JPanel.
// *
// *
// */
// private GridModel model;
// private int xside, yside;

// public Grid(GridModel model, int size) {

// /*
// * Description:
// *
// * Initializes the grid.
// *
// *
// */
// }

// public void paintComponent(Graphics g) {

// /*
// * Description:
// *
// * This method is used to paint the grid.
// *
// * The grid is painted by iterating through the grid and painting each cell.
// The
// * color of each cell is determined by the number of live neighbours. To
// assign
// * the color to a cell, a switch statement is used.
// *
// * this method calls getNumberOfNeighbours() to determine the number of live
// * neighbours.
// *
// * Args:
// *
// * g: The graphics object to be used to paint the grid.
// *
// *
// */

// }

// public void mouseClicked(MouseEvent e) {
// /*
// * Description:
// *
// * This method is called when the user clicks on the GUI. It should toggle the
// * state of the cell at the specified row and column.
// *
// * Args:
// *
// * e: The MouseEvent that triggered the method call.
// */
// }

// /* allow dragging to fill in blocks */
// public void mouseEntered(MouseEvent e) {

// /*
// * Description:
// *
// * This method is called when the user moves the mouse over the GUI while
// * holding the left mouse button. It should toggle the state of the state of
// the
// * cell at the specified row and column.
// *
// */

// }

// public void mouseExited(MouseEvent e) {

// /*
// * Description:
// *
// * This method is called when the user moves the mouse away from the GUI while
// * holding the left mouse button. It should toggle the state of the state of
// the
// * cell at the specified row and column.
// *
// */

// }

// public void mousePressed(MouseEvent e) {

// /*
// *
// * Description:
// *
// * This method is called when the user presses the left mouse button. It
// should
// * toggle the state of the state of the cell at the specified row and column.
// *
// * Args:
// *
// * e: The MouseEvent that triggered the method call.
// */

// }

// public void mouseReleased(MouseEvent e) {
// // stop filling in
// /*
// * Description:
// *
// * This method is called when the user releases the left mouse button. It
// should
// * toggle the state of the state of the cell at the specified row and column.
// *
// * Args:
// *
// * e: The MouseEvent that triggered the method call.
// */

// }

// // helpers
// private int getRowClicked(MouseEvent e) {

// /*
// * Description:
// *
// * This method is used to determine the row of the cell that the user clicked.
// * The row is determined by dividing the x coordinate of the mouse by the
// width
// * of each cell.
// */
// return 0;

// }

// private int getColumnClicked(MouseEvent e) {
// /*
// * Description:
// *
// * This method is used to determine the column that the user clicked on.
// *
// * Args:
// *
// * e: The MouseEvent that triggered the method call.
// *
// * Returns:
// *
// * The column that the user clicked on.
// */

// return 0;
// }

// }

// public class SkeletonCode {

// public static void main(String[] args) {

// /*
// *
// * Description:
// *
// * This method is used to create the GUI.
// *
// * The GUI is created by creating a new GameGUI object.
// *
// * Args:
// *
// * model: The model to be used for the simulation.
// *
// * size: The size of the grid to create.
// */
// System.out.println("Game Of Life");

// }

// }


/**
 * Class: Grid Description: This class is the grid that the game is played on.
 *
 *
 *
 * Fields: int xside: the width of the grid int yside: the height of the grid
 * GridModel model: instance of the grid model
 *
 * @filename: Grid.java
 * @author YonahAviv
 *
 * Created at     : 2021-11-18 12:00:42
 * Last modified  : 2021-11-18 12:06:37
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.*;

class Grid extends JPanel implements MouseListener {
    /*
     * Class: Grid Description: This class is the grid that the game is played on.
     *
     *
     *
     * Fields: int xside: the width of the grid int yside: the height of the grid
     * GridModel model: instance of the grid model
     */

    private GridModel model;
    private int xside, yside;

    public Grid(GridModel model, int size) {
        this.model = model;
        this.xside = size / model.getWidth();
        this.yside = size / model.getHeight();
        setPreferredSize(new Dimension(size, size));
        setBackground(Color.WHITE);
        setOpaque(true);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.BLACK);
        int width = model.getWidth();
        int height = model.getHeight();
        int midWidth = (int) (width / 2);
        int midHeight = (int) (height / 2);

        /*
         * 1 | 2
         *
         * ------
         *
         * 4 | 3
         *
         */

        ArrayList<ArrayList<int[]>> quadrants = getQuadrantsWrapper(width, height, midWidth, midHeight);
        // logic
        for (int row = 0; row < width - 1; row++) {
            for (int col = 0; col < height - 1; col++) {
                if (model.getCell(row, col)) {

                    for (ArrayList<int[]> quad : quadrants) {
                        /*
                         * Summary of loop: 1. Check if the cell is in the quadrant 2. Depending on the
                         * quadrant, set the color of the cell to the color of the quadrant
                         */
                        boolean isInHorizontalBounds = determineIfInBounds(0, row, quad, "row"); // 0 is the index of
                                                                                                 // the first element in
                                                                                                 // the array
                        boolean isInVerticalBounds = determineIfInBounds(1, col, quad, "col"); // 1 is the index of the
                                                                                               // second element in the
                                                                                               // array
                        if (isInHorizontalBounds && isInVerticalBounds) {

                            int quadrantNumber = quadrants.indexOf(quad);

                            switch (quadrantNumber) {
                            case 0 -> {
                                g.setColor(Color.ORANGE);

                            }
                            case 1 -> g.setColor(Color.BLUE);
                            case 2 -> g.setColor(Color.DARK_GRAY);
                            case 3 -> g.setColor(Color.RED);
                            default -> g.setColor(Color.BLACK);
                            }
                        }
                    }
                    g.fillRect(row * xside, col * yside, xside, yside);

                }

            }
        }

    }

    private ArrayList<ArrayList<int[]>> getQuadrantsWrapper(int width, int height, int midWidth, int midHeight) {
        /*
         *
         * Description: This method returns an arraylist of arraylists of int arrays.
         * The first arraylist contains the coordinates of the top left quadrant. The
         * second arraylist contains the coordinates of the top right quadrant. The
         * third arraylist contains the coordinates of the bottom left quadrant. The
         * fourth arraylist contains the coordinates of the bottom right quadrant.
         *
         *
         * Visualization: 1 | 2 | --------- | 4 | 3
         *
         *
         *
         * Parameters: int width: the width of the grid int height: the height of the
         * grid int midWidth: the midpoint of the width int midHeight: the midpoint of
         * the height
         *
         */
        // quadrant 1
        int[][] quadrant1Coords = { { 0, 0 }, { midWidth - 1, 0 }, { midWidth - 1, midHeight - 1 },
                { 0, midHeight - 1 } };
        ArrayList<int[]> quadrant1 = converyCoordsArrayToArrayList(quadrant1Coords);

        // quadrant 2
        int[][] quadrant2Coords = { { midWidth + 1, 0 }, { width, 0 }, { width, midHeight - 1 },
                { midWidth + 1, midHeight - 1 } };
        ArrayList<int[]> quadrant2 = converyCoordsArrayToArrayList(quadrant2Coords);

        // quadrant 3
        int[][] quadrant3Coords = { { midWidth + 1, midHeight + 1 }, { width, midHeight + 1 }, { width, height },
                { midWidth + 1, height } };
        ArrayList<int[]> quadrant3 = converyCoordsArrayToArrayList(quadrant3Coords);

        // quadrant 4
        int[][] quadrant4Coords = { { 0, midHeight + 1 }, { midWidth - 1, midHeight + 1 }, { 0, height },
                { midWidth - 1, height } };
        ArrayList<int[]> quadrant4 = converyCoordsArrayToArrayList(quadrant4Coords);
        /*
         * Quick explanation of the logic in converting the coords to arraylist:
         *
         *
         * arraylist of arraylists 5. Return the arraylist of arraylists
         */

        // create arraylist of quadrants
        ArrayList<ArrayList<int[]>> quadrants = new ArrayList<>();
        quadrants.add(quadrant1);
        quadrants.add(quadrant2);
        quadrants.add(quadrant3);
        quadrants.add(quadrant4);
        return quadrants;
    }

    private boolean determineIfInBounds(int startingVal, int positionVal, ArrayList<int[]> quad, String rowOrCol) {
        /*
         * Description: This method determines if the position is in the bounds of the
         * quadrant.
         *
         *
         *
         * Parameters:
         *
         * int startingVal: the starting value of the quadrant
         *
         * int positionVal: the position value of the quadrant
         *
         * ArrayList<int[]> quad: the quadrant
         *
         * String rowOrCol: the row or column of the quadrant
         *
         *
         * Return: boolean: true if the position is in the bounds of the quadrant, false
         */
        // its bad practice to use a thisOrThat paramter but whatever
        int deepestIndex = 0;
        boolean[] isInBounds = new boolean[2];
        int loopTillThisVal = startingVal + 1;
        switch (rowOrCol) {
        case "row" -> {
            deepestIndex = 0;
        }
        case "col" -> {
            deepestIndex = 1;
        }
        }
        for (int i = startingVal; i <= loopTillThisVal; i++) {
            /*
             * Logic of this loop:
             *
             * 1. Check if the position is in the bounds of the quadrant
             *
             * 2. If it is, set the boolean to true
             *
             * 3. If it is not, set the boolean to false
             */
            int quadBoundVal = quad.get(i)[deepestIndex];
            if (i == startingVal) {
                isInBounds[0] = positionVal >= quadBoundVal;
            } else {
                isInBounds[1] = positionVal <= quadBoundVal;
            }
        }
        return isInBounds[0] && isInBounds[1];
    }

    private ArrayList<int[]> converyCoordsArrayToArrayList(int[][] args) {
        /*
         * Description: This method converts an array of coordinates to an arraylist of
         * coordinates.
         *
         * Parameters: int[][] args: the array of coordinates
         *
         * Returns: ArrayList<int[]>: the arraylist of coordinates
         */
        int[][] quadrantCorners = { args[0], args[1], args[2], args[3] };
        ArrayList<int[]> quadrant = new ArrayList<>(4);
        for (int[] coordinate : quadrantCorners) {
            quadrant.add(coordinate);
        }
        return quadrant;
    }

    public void mouseClicked(MouseEvent e) {
        /*
         * Description: This method is called when the mouse is clicked.
         *
         * Parameters: MouseEvent e: the mouse event
         */
        int row = getRowClicked(e);
        int col = getColumnClicked(e);
        boolean b = model.getCell(row, col);
        model.setCell(row, col, !b);
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        /*
         * Description: This method is called when the mouse enters the grid.
         *
         * Purpose: To fill in multiple cells at once
         *
         * Parameters: MouseEvent e: the mouse event
         */
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int row = getRowClicked(e);
                int col = getColumnClicked(e);

                model.setCell(row, col, true);
                repaint();
            }
        });

    }

    public void mouseExited(MouseEvent e) {
        /*
         * Description: This method is called when the mouse exits the grid.
         *
         * Purpose: To stop filling in multiple cells at once
         *
         * Parameters: MouseEvent e: the mouse event
         */
        removeMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int row = getRowClicked(e);
                int col = getColumnClicked(e);
                model.setCell(row, col, false);
                repaint();
            }
        });

    }

    public void mousePressed(MouseEvent e) {
        /*
         * Description: This method is called when the mouse is pressed.
         *
         * Purpose: To start filling in a single cell
         *
         * Parameters: MouseEvent e: the mouse event
         */
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                removeMouseListener(this);
            }
        });
    }

    public void mouseReleased(MouseEvent e) {
        /*
         * Description: This method is called when the mouse is released.
         *
         * Purpose: To stop filling in a single cell
         *
         * Parameters: MouseEvent e: the mouse event
         */
        removeMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int row = getRowClicked(e);
                int col = getColumnClicked(e);
                model.setCell(row, col, false);
                repaint();
            }
        });

    }

    // helpers
    private int getRowClicked(MouseEvent e) {
        /*
         * Description: This method returns the row of the cell that was clicked.
         *
         * Parameters: MouseEvent e: the mouse event
         *
         * Returns: int: the row of the cell that was clicked
         */
        int row = e.getX() / xside;
        return row;
    }

    private int getColumnClicked(MouseEvent e) {
        /*
         * Description: This method returns the column of the cell that was clicked.
         *
         * Parameters: MouseEvent e: the mouse event
         *
         * Returns: int: the column of the cell that was clicked
         */
        int col = e.getY() / yside;
        return col;
    }

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Grid extends JPanel implements MouseListener {

    /*  */
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
                    // set color based on number of neighbours

                    int neighbours = model.getNumberOfNeighbours(row, col);
                    /*
                     * // if fully surrounded set all blocks surrounding to red for 1 second
                     */
                    /*
                     * switch (neighbours) { case 1, 2, 3 -> g.setColor(Color.BLACK); case 4, 5, 6
                     * -> g.setColor(Color.BLUE); case 7 -> g.setColor(Color.GREEN); case 8 -> {
                     *
                     * for (int x = row - 1; x <= row + 1; x++) { for (int y = col - 1; y <= col +
                     * 1; y++) { if (x == row && y == col) { continue; }
                     *
                     * g.setColor(Color.RED); g.fillRect(x * xside, y * yside, xside, yside);
                     *
                     * } } }
                     *
                     * default -> g.setColor(Color.BLACK); }
                     */
                    for (ArrayList<int[]> quad : quadrants) {
                        // ArrayList<int[]> quad = quad;
                        boolean isInHorizontalBounds = determineIfInBounds(0, row, quad, "row");
                        boolean isInVerticalBounds = determineIfInBounds(1, col, quad, "col");
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

        // create arraylist of quadrants
        ArrayList<ArrayList<int[]>> quadrants = new ArrayList<>();
        quadrants.add(quadrant1);
        quadrants.add(quadrant2);
        quadrants.add(quadrant3);
        quadrants.add(quadrant4);
        return quadrants;
    }

    private boolean determineIfInBounds(int startingVal, int positionVal, ArrayList<int[]> quad, String rowOrCol) {
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
        // method to convert primitive array to arraylist
        int[][] quadrantCorners = { args[0], args[1], args[2], args[3] };
        ArrayList<int[]> quadrant = new ArrayList<>(4);
        for (int[] coordinate : quadrantCorners) {
            quadrant.add(coordinate);
        }
        return quadrant;
    }

    public void mouseClicked(MouseEvent e) {
        int row = getRowClicked(e);
        int col = getColumnClicked(e);
        boolean b = model.getCell(row, col);
        model.setCell(row, col, !b);
        repaint();
    }

    /* allow dragging to fill in blocks */
    public void mouseEntered(MouseEvent e) {
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
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                removeMouseListener(this);
            }
        });
    }

    public void mouseReleased(MouseEvent e) {
        // stop filling in
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
        int row = e.getX() / xside;
        return row;
    }

    private int getColumnClicked(MouseEvent e) {
        int col = e.getY() / yside;
        return col;
    }

}
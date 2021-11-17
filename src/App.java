import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

class AbstractGrid {
    private int width, height, gen;
    private boolean[][] grid;

    public AbstractGrid(int w, int h) {
        this.width = w;
        this.height = h;
        this.gen = 1;
        this.grid = new boolean[width][height];
    }

    // play sound method
    public void playSound(String soundName) {

        // get audio file
        File soundFile = new File(soundName);
        try {
            // create audio input stream
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // create clip
            Clip clip = AudioSystem.getClip();
            // open audio clip
            clip.open(audioIn);
            // start clip
            clip.start();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public void randomPopulation(double fill) {
        System.out.println("AbstractGrid: Call to randomPopulation(" + fill + ")");
        // fill with bool

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                grid[row][col] = Math.random() < fill;
            }
        }
    }

    public void newGeneration() {
        boolean[][] newgrid = new boolean[width][height];
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {

                // get number of neighbours
                int neighbours = getNumberOfNeighbours(row, col);
                // apply rules
                switch (neighbours) {
                case 0, 1 -> newgrid[row][col] = false;
                case 2 -> newgrid[row][col] = grid[row][col];
                case 3 -> newgrid[row][col] = true;
                case 4, 5, 6, 7, 8 -> newgrid[row][col] = false;
                default -> System.out.println("AbstractGrid: newGeneration: Error");
                }
            }
        }

        // System.out.println("AbstractGrid: Call #" + gen + " to newGeneration()");
        this.grid = newgrid;
    }

    public void setCell(int row, int col, boolean b) {

        if (row >= 0 && row < width && col >= 0 && col < height) {
            this.grid[row][col] = b;
        }

        // System.out.println("AbstractGrid: Call to setCell(" + row + ", " + col + ", "
        // + b + ")");
    }

    public boolean getCell(int row, int col) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == row && y == col) {
                    return grid[x][y];
                }
            }
        }
        // if (row == 0 && col == 0)
        // System.out.println('AbstractGrid: Call to getCell(' + row + "," + col + ")");

        return (row + col) % 2 == 0;
    }

    public int getWidth() {
        // System.out.println("AbstractGrid: Call to getWidth()");
        return width;
    }

    public int getHeight() {
        // System.out.println("AbstractGrid: Call to getHeight()");
        return height;
    }

    public int getGen() {
        // System.out.println("AbstractGrid: Call to getGen()");
        return gen;
    }

    public int getNumberOfNeighbours(int row, int col) {
        return getNumberOfNeighboursHelper(row, col);
    }

    // --------------------------------------------------------------------------------
    // helpers
    // --------------------------------------------------------------------------------
    private int getNumberOfNeighboursHelper(int row, int col) {
        /* use most efficient */
        int neighbours = 0;
        // include left and right edge columns
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (x == row && y == col) {
                    continue;
                }

                if (getCell(x, y)) {
                    neighbours++;
                }

            }
        }
        // System.out.println("AbstractGrid: Call to getNumberOfNeighbours(" + row + ","
        // + col + ")");
        return neighbours;

    }

}

class GameGUI extends JPanel {

    private AbstractGrid model;
    private Grid grid;
    private Timer timer;

    public GameGUI(AbstractGrid model, int size) {
        this.model = model;
        this.grid = new Grid(model, size);
        JButton stepButton = new JButton("Step");
        JButton runButton = new JButton("Run");
        JButton stopButton = new JButton("Stop");
        JSlider speedSlider = new JSlider(0, 1000, 50);
        setLayout(new BorderLayout());
        add(grid, BorderLayout.NORTH);
        add(stepButton, BorderLayout.WEST);
        add(runButton, BorderLayout.CENTER);
        add(stopButton, BorderLayout.EAST);
        add(speedSlider, BorderLayout.SOUTH);

        ActionListener stepListener = new StepListener();
        stepButton.addActionListener(stepListener);
        runButton.addActionListener(new RunListener());
        stopButton.addActionListener(new StopListener());
        speedSlider.addChangeListener(new SpeedListener());
        timer = new Timer(600, stepListener);
    }

    private class StepListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.newGeneration();
            grid.repaint();
        }
    }

    private class RunListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timer.start();
        }
    }

    private class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }
    }

    private class SpeedListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            timer.setDelay(100 - ((JSlider) e.getSource()).getValue());
        }
    }

}

class Grid extends JPanel implements MouseListener {

    private AbstractGrid model;
    private int xside, yside;

    public Grid(AbstractGrid model, int size) {
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
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (model.getCell(row, col)) {
                    // set color based on number of neighbours

                    int neighbours = model.getNumberOfNeighbours(row, col);
                    // if fully surrounded set all blocks surrounding to red for 1 second

                    switch (neighbours) {
                    case 1, 2, 3 -> g.setColor(Color.BLACK);
                    case 4, 5, 6 -> g.setColor(Color.BLUE);
                    case 7 -> g.setColor(Color.GREEN);
                    case 8 -> {

                        for (int x = row - 1; x <= row + 1; x++) {
                            for (int y = col - 1; y <= col + 1; y++) {
                                if (x == row && y == col) {
                                    continue;
                                }
                                // play sound "lightsaber.mp3"
                                g.setColor(Color.RED);
                                g.fillRect(x * xside, y * yside, xside, yside);

                            }
                        }
                    }

                    default -> g.setColor(Color.BLACK);
                    }
                    model.playSound("src/assets/lightsaber.mp3");

                    g.fillRect(row * xside, col * yside, xside, yside);
                }
            }
        }

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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

public class App {

    public static void main(String[] args) {
        JFrame f = new JFrame("Game of Life");
        AbstractGrid model = new AbstractGrid(60, 60);
        double ratio = Double.parseDouble(0 + "");
        model.randomPopulation(ratio);
        GameGUI gui = new GameGUI(model, 500);
        f.add(gui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}

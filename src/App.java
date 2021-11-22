
/**
 * Description: This FIle contains a class for the GUI for the game. It contains
 * the game board, the player's score, and the player's lives. It also contains
 * the buttons for the player to interact with the game.
 *
 * It is also the main App class that contnains the main method.
 *
 * @filanem: App.java
 * @author YonahAviv
 *
 * Created at     : 2021-11-18 12:00:42
 * Last modified  : 2021-11-18 12:33:26
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

class GameGUI extends JPanel {
    /*
     *
     * Class: GameGUI Description: This class is the GUI for the game. It contains
     * the game board, the player's score, and the player's lives. It also contains
     * the buttons for the player to interact with the game.
     */

    public GridModel model;
    private Grid grid;
    private Timer timer;
    PresetModelHandler presetModelHandler = new PresetModelHandler(120, 120);

    public GameGUI(GridModel model, int size) {
        /*
         * Description:
         *
         * Constructor for the GameGUI class.
         *
         *
         */

        this.model = model;
        presetModelHandler.setHeight(size);
        presetModelHandler.setWidth(size);

        // Create the combo box and add the options to it
        JComboBox<String> comboBox = new JComboBox<String>();

        // run panel
        JButton stepButton = new JButton("➡️");
        JButton runButton = new JButton("▶️Play");
        JButton stopButton = new JButton("⏸️");

        // speec create
        JSlider speedSlider = new JSlider(0, 1000, 100);
        // create the random panel
        JPanel randomPanel = new JPanel();
        JSpinner randomFactor = new JSpinner(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.1));
        JButton randomizeButton = new JButton("🎲Randomize");
        // speed
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setValue(700);
        // random panel
        randomizeButton.setToolTipText("Randomize the grid");
        randomFactor.setBounds(0, 0, 50, 50);
        randomPanel.add(randomFactor);
        randomPanel.add(randomizeButton);
        // Combobox customizations and listeners
        comboBox.setModel(new DefaultComboBoxModel<String>(presetModelHandler.options));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(10, 10, 100, 20);
        comboBox.addActionListener(new ConfigSelectionListener());

        grid = new Grid(model, size);

        // add the right components to the main panel
        setLayout(new BorderLayout());
        add(comboBox);
        add(grid, BorderLayout.NORTH);

        // add(stepButton, BorderLayout.WEST);
        add(runButton, BorderLayout.CENTER);
        add(stopButton, BorderLayout.WEST);
        add(randomPanel, BorderLayout.WEST);
        add(speedSlider, BorderLayout.EAST);

        ActionListener stepListener = new StepListener();
        stepButton.addActionListener(stepListener);
        runButton.addActionListener(new RunListener());
        stopButton.addActionListener(new StopListener());
        speedSlider.addChangeListener(new SpeedListener());
        // inline listener

        randomizeButton.addActionListener(e -> {
            /*
             *
             * Decription:
             *
             * This method is called when the randomize button is pressed. It randomizes the
             * grid by a factor specified in the spinner.
             */
            String ratioString = randomFactor.getValue().toString();
            double ratio = Double.parseDouble(ratioString);
            model.randomPopulation(ratio);
            grid.repaint();

        });
        timer = new Timer(600, stepListener);
    }

    private class ConfigSelectionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // use presetModelHandler.setPresetModelGrid(...) to load the model
            // with the selected preset
            String selected = (String) ((JComboBox) e.getSource()).getSelectedItem();
            presetModelHandler.setPresetModelGrid(selected);
            model.setGrid(presetModelHandler.getPresetModelGrid());
            grid.repaint();
        }
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
            System.out.println("Generatuion " + model.getGen());
            // count alive cells
            int alive = 0;
            for (int row = 0; row < model.getWidth(); row++) {
                for (int col = 0; col < model.getHeight(); col++) {
                    if (model.getCell(row, col)) {
                        alive++;
                    }
                }
            }

            System.out.println("Alive cells: " + alive);
        }
    }

    private class SpeedListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            timer.setDelay(1000 - ((JSlider) e.getSource()).getValue());
            // output the new delay
            if (timer.getDelay() % 100 == 0) {
                System.out.println("New speed: " + (1000 - timer.getDelay()) + " ms");
            }
            // max
            switch (timer.getDelay()) {
            case 0 -> System.out.println("Max speed reached: " + (1000 - timer.getDelay()) + " ms");
            case 1000 -> System.out.println("Minimum speed reached: " + (1000 - timer.getDelay()) + " ms");
            }
        }
    }

}

public class App {

    public static void main(String[] args) {
        /*
         * Explanation of all main method:
         *
         * 1. Create a new grid model with the selected size 2. Create a new game GUI
         * with the selected size 3. Add the game GUI to the frame 4. Set the frame to
         * visible
         *
         *
         *
         * Logic of the game: 1. Use a timer to run the game at a specific speed 2. Use
         * the timer to call the step method in the model 3. Call the step method in the
         * model 4. Repaint the grid in the game GUI 5. Repeat steps 2-4
         */
        GridModel model = new GridModel(200, 200);

        JFrame frame = new JFrame("Game of Life");
        // create a starting menu screen to select size of grid
        JPanel startMenu = new JPanel();
        JLabel label = new JLabel("Select size of grid(max 200)");
        label.setForeground(Color.WHITE);
        JSpinner spinner = new JSpinner() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 20);
            }

        };

        JButton startButton = new JButton("Start");

        // style button
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);

        JPanel imagePreview = new JPanel();
        JLabel imageLabel = new JLabel("Conways Game Of Life");
        imageLabel.setForeground(Color.WHITE);
        // precview image in assets/images/conways.png
        ImageIcon icon = new ImageIcon("src/assets/images/conways.gif");
        imageLabel.setIcon(icon);
        imagePreview.add(imageLabel);
        imagePreview.setBackground(Color.BLACK);
        imagePreview.setPreferredSize(new Dimension(200, 200));

        startMenu.add(imagePreview);
        startMenu.add(label);
        startMenu.add(spinner);
        startMenu.add(startButton);
        startMenu.setBackground(Color.BLACK);
        // set font to helvetica
        frame.setFont(new Font("Calibri", Font.PLAIN, 12));
        frame.setBackground(Color.BLACK);
        frame.add(startMenu);
        frame.setSize(new Dimension(500, 500));
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size = (int) spinner.getValue();
                if (size > 200) {
                    size = 200;
                } else if (size < 10) {
                    size = 10;
                } else if (size < 100) {
                    size = 100;
                }
                System.out.println(
                        "\n----------------------Welcome to game of life by yonahaviv--------------------------------------------\n-----------------------\nRULES:\n\n 1. if 1 or 0 neighbours, cell dies\n 2. if 2 neighbours, cell stays the same\n3. if 3 neighbours, cell lives\n4. if 4 or more neighbours, cell dies\n\n\n\n note: some configurations will not render at uyour selected size; please restart the game in order to reslect size(make it greater than 100) to render most configurations\n\nto make a custom config -> go to src/configs/custom.csv and change the 1s and 0s as you see fit. then, select custom int he dropdown in the top left corner of the grid, (it might be invisible sometimes but that does not mean its unclickable.\n -----------------------\nenjoy\n-------------------------------");
                // create a new game with the selected size
                model.setHeight(size);
                model.setWidth(size);
                GameGUI game = new GameGUI(model, 500);
                frame.remove(startMenu);
                frame.setSize(500, 400);
                frame.add(game);
                frame.pack();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        // double ratio = Double.parseDouble(0 + "");
        // model.randomPopulation(ratio);

        // GameGUI gui = new GameGUI(model, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}

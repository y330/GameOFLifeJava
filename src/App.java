import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

class GameGUI extends JPanel {

    public GridModel model;
    private Grid grid;
    private Timer timer;
    PresetModelHandler presetModelHandler = new PresetModelHandler(100, 100);

    public GameGUI(GridModel model, int size) {

        this.model = model;

        // Create the combo box and add the options to it
        JComboBox<String> comboBox = new JComboBox<String>();

        JButton stepButton = new JButton("➡️");
        JButton runButton = new JButton("▶️Play");
        JButton stopButton = new JButton("⏸️");
        JSlider speedSlider = new JSlider(0, 1000, 50);
        JPanel randomPanel = new JPanel();
        JSpinner randomFactor = new JSpinner(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.1));
        JButton randomizeButton = new JButton("🎲Randomize");
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
        setLayout(new BorderLayout());
        add(comboBox);
        add(grid, BorderLayout.NORTH);

        // add(stepButton, BorderLayout.WEST);
        add(runButton, BorderLayout.CENTER);
        add(stopButton, BorderLayout.EAST);
        add(randomPanel, BorderLayout.WEST);
        add(speedSlider, BorderLayout.EAST);

        ActionListener stepListener = new StepListener();
        stepButton.addActionListener(stepListener);
        runButton.addActionListener(new RunListener());
        stopButton.addActionListener(new StopListener());
        speedSlider.addChangeListener(new SpeedListener());
        // inline listener

        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ratioString = randomFactor.getValue().toString();
                double ratio = Double.parseDouble(ratioString);
                model.randomPopulation(ratio);

            }
        });
        // reset button uses model.randomPopulation
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
        GridModel model = new GridModel(100, 100);

        JFrame frame = new JFrame("Game of Life");
        // create a starting menu screen to select size of grid
        JPanel startMenu = new JPanel();
        JLabel label = new JLabel("Select size of grid");
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
        ImageIcon icon = new ImageIcon("conways.gif");
        imageLabel.setIcon(icon);
        imagePreview.add(imageLabel);
        imagePreview.setBackground(Color.BLACK);


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
                // create a new game with the selected size
                int size = (int) spinner.getValue();
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

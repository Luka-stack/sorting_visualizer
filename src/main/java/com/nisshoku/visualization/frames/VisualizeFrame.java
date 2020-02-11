package com.nisshoku.visualization.frames;

import com.nisshoku.visualization.SortingVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public final class VisualizeFrame extends JFrame {

    private final String[] ALGORITHMS = {
            "Selection", "Bubble", "Insertion", "Gnome", "Radix",
            "Shell", "Quick", "Heap", "Merge"
    };

    private final Color BACKGROUND_COLOR = new Color(189, 195, 199);
    private final Color BAR_COLOR = new Color(231, 76, 60);
    private final Color COMPARING_COLOR = new Color(22, 160, 133);
    private final Color WORKING_COLOR = new Color(142, 68, 173);
    private final Color SWAPPING_COLOR = new Color(52, 73, 94);

    private double sizeModifier;

    private JPanel arrayWrapper;
    private JPanel[] squarePanels;

    private TimerFrame timerFrame;
    private JComboBox<String> popupSelection;
    private JTextField popupField;

    private JButton sort;
    private JButton stop;
    private JButton reset;
    private JButton step;

    private JComboBox<String> selection;
    private JComboBox<String> selectSpeed;
    private JComboBox<String> selectSize;
    private JCheckBox sorted;
    private JCheckBox reversedSorted;

    private GridBagConstraints grid;

    public VisualizeFrame() {

        super("Sorting Algorithms");

        grid = new GridBagConstraints();
        grid.insets = new Insets(0, 1, 0, 1);
        grid.anchor = GridBagConstraints.SOUTH;

        // Setting algorithm selection
        JLabel selectionLabel = new JLabel("Algorithm");
        selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        selection = new JComboBox<>(ALGORITHMS);

        JPanel selectionWrapper = new JPanel(new GridLayout(2, 1));
        selectionWrapper.setBackground(BACKGROUND_COLOR);
        selectionWrapper.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        selectionWrapper.add(selectionLabel);
        selectionWrapper.add(selection);

        // Setting buttons Panel
        sort = new JButton("SORT");
        sort.addActionListener(event -> SortingVisualizer
                .visualizerSort((String) selection.getSelectedItem(), false));

        stop = new JButton("STOP");
        stop.setEnabled(false);
        stop.addActionListener(event -> SortingVisualizer.stopThread());

        reset = new JButton("RESET");
        reset.addActionListener(event -> SortingVisualizer.restoreArray());

        step = new JButton("STEP");
        step.addActionListener(e -> SortingVisualizer
                .visualizerSort((String) selection.getSelectedItem(), true));

        JPanel actionWrapper = new JPanel(new FlowLayout());
        actionWrapper.setBackground(BACKGROUND_COLOR);
        actionWrapper.add(sort);
        actionWrapper.add(stop);
        actionWrapper.add(reset);
        actionWrapper.add(step);

        // Setting sorting options
        sorted = new JCheckBox("Sorted Values");
        sorted.setBackground(BACKGROUND_COLOR);
        sorted.addActionListener(event -> {
            SortingVisualizer.sorted = sorted.isSelected();
            if (sorted.isSelected()) reversedSorted.setEnabled(true);
            else reversedSorted.setEnabled(false);
            SortingVisualizer.resetArray();
        });

        reversedSorted = new JCheckBox("Reversed");
        reversedSorted.setBackground(BACKGROUND_COLOR);
        reversedSorted.setEnabled(false);
        reversedSorted.addActionListener(event -> {
            SortingVisualizer.reversed = reversedSorted.isSelected();
            SortingVisualizer.resetArray();
        });

        JPanel checkBoxWrapper = new JPanel(new GridLayout(2, 1));
        checkBoxWrapper.setBackground(BACKGROUND_COLOR);
        checkBoxWrapper.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        checkBoxWrapper.add(sorted);
        checkBoxWrapper.add(reversedSorted);

        // Setting Sizes
        JLabel sizeLabel = new JLabel("Size");
        sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        selectSize = new JComboBox<>();
        selectSize.addItem("50");
        selectSize.addItem("100");
        selectSize.addItem("150");
        selectSize.setSelectedIndex(0);
        selectSize.addActionListener(event -> {
            SortingVisualizer.dataCount = Integer.parseInt((String) Objects.requireNonNull(selectSize.getSelectedItem()));
            SortingVisualizer.resetArray();

            if (SortingVisualizer.dataCount == 50) {
                setSize(780, 400);
            }
            else if (SortingVisualizer.dataCount == 100) {
                setSize(1120, 490);
            }
            else {
                setSize(1220, 490);
            }
        });

        JPanel sizeWrapper = new JPanel(new GridLayout(2, 1));
        sizeWrapper.setBackground(BACKGROUND_COLOR);
        sizeWrapper.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        sizeWrapper.add(sizeLabel);
        sizeWrapper.add(selectSize);

        // Setting Speed - choosable option
        JLabel speedLabel = new JLabel("Speed");
        speedLabel.setHorizontalAlignment(SwingConstants.CENTER);

        selectSpeed = new JComboBox<>();
        selectSpeed.addItem("fast");   // speed.addItem("150");
        selectSpeed.addItem("normal"); // speed.addItem("200");
        selectSpeed.addItem("slow");   // speed.addItem("250");
        selectSpeed.setSelectedIndex(1);
        selectSpeed.addActionListener(event -> {

            if (Objects.equals(selectSpeed.getSelectedItem(), "fast")) {
                SortingVisualizer.sleep = 150; // 150 ms sleep while drawing
            }
            else if (Objects.equals(selectSpeed.getSelectedItem(), "normal")) {
                SortingVisualizer.sleep = 200; // 200 ms sleep while drawing
            }
            else {
                SortingVisualizer.sleep = 250; // 250 ms sleep while drawing
            }
        });

        JPanel speedWrapper = new JPanel(new GridLayout(2, 1));
        speedWrapper.setBackground(BACKGROUND_COLOR);
        speedWrapper.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        speedWrapper.add(speedLabel);
        speedWrapper.add(selectSpeed);

        // adding to buttonWrapper
        JPanel optionWrapper = new JPanel();
        optionWrapper.setBackground(BACKGROUND_COLOR);
        optionWrapper.add(selectionWrapper);
        optionWrapper.add(sizeWrapper);
        optionWrapper.add(speedWrapper);
        optionWrapper.add(checkBoxWrapper);

        // setting bottom panel
        JPanel bottomBarWrapper = new JPanel(new BorderLayout());
        bottomBarWrapper.add(actionWrapper, BorderLayout.NORTH);
        bottomBarWrapper.add(optionWrapper, BorderLayout.SOUTH);

        // setting arrayWrapper
        arrayWrapper = new JPanel();
        arrayWrapper.setBackground(BACKGROUND_COLOR);
        arrayWrapper.setLayout(new GridBagLayout());
        arrayWrapper.setBorder(BorderFactory.createEmptyBorder(5, 7, 0, 7));

        // Building menu
        JMenuBar menuBar = new JMenuBar();

        JMenu performanceMenu = new JMenu("Performance");
        performanceMenu.setMnemonic(KeyEvent.VK_O);
        performanceMenu.getAccessibleContext().setAccessibleDescription("Additional Performance Options");

        JMenuItem timerOption = new JMenuItem("Timer");
        timerOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_DOWN_MASK));
        timerOption.getAccessibleContext().setAccessibleDescription("Choose size, algorithm and measure performance time");
        timerOption.addActionListener(event -> timerPopup());

        JMenuItem legendOption = new JMenuItem("Legend");
        legendOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_DOWN_MASK));
        legendOption.getAccessibleContext().setAccessibleDescription("Get information about colors");
        legendOption.addActionListener(event -> legendPopup());

        performanceMenu.add(timerOption);
        performanceMenu.add(legendOption);

        JMenu algorithmMenu = new JMenu("Algorithms");
        algorithmMenu.setMnemonic(KeyEvent.VK_3);
        algorithmMenu.getAccessibleContext().setAccessibleDescription("Algorithms Information");

        JMenuItem complexityOption = new JMenuItem("Complexities");
        complexityOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_DOWN_MASK));
        complexityOption.addActionListener(event -> new ComplexityFrame());

        JMenuItem describeOption = new JMenuItem("Descriptions");
        describeOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_DOWN_MASK));
        describeOption.getAccessibleContext().setAccessibleDescription("Get information about sorting algorithms");
        describeOption.addActionListener(event -> new DescribeFrame());

        algorithmMenu.add(complexityOption);
        algorithmMenu.add(describeOption);

        menuBar.add(performanceMenu);
        menuBar.add(algorithmMenu);

        // setting wrapper
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(menuBar, BorderLayout.NORTH);
        wrapper.add(bottomBarWrapper, BorderLayout.SOUTH);
        wrapper.add(arrayWrapper);

        // Setting JFrame
        add(wrapper);

        setResizable(false);
        setSize(780, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Reinitializes the array of panels that represent the values
    public void preDrawArray(Integer[] squares) {

        squarePanels = new JPanel[SortingVisualizer.dataCount];
        arrayWrapper.removeAll();

        if (SortingVisualizer.dataCount == 50) {
            sizeModifier = 4;
            grid.insets = new Insets(0, 3, 0, 3);
            SortingVisualizer.blockWidth = 9;
        }
        else if (SortingVisualizer.dataCount == 100) {
            sizeModifier = 3;
            grid.insets = new Insets(0, 2, 0, 2);
            SortingVisualizer.blockWidth = 7;
        }
        else {
            sizeModifier = 2;
            grid.insets = new Insets(0, 1, 0, 1);
            SortingVisualizer.blockWidth = 6;
        }

        for (int i = 0; i < SortingVisualizer.dataCount; ++i) {
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth,
                    (int) (squares[i] * sizeModifier)));
            squarePanels[i].setBackground(BAR_COLOR);
            arrayWrapper.add(squarePanels[i], grid);
        }

        repaint();
        validate();
    }

    public void reDrawArray(Integer[] arr) {
        reDrawArray(arr, -1);
    }

    public void reDrawArray(Integer[] arr, int comparing) {
        reDrawArray(arr, comparing, -1);
    }

    public void reDrawArray(Integer[] arr, int comparing, int working) {
        reDrawArray(arr, comparing, working, -1);
    }

    public void reDrawArray(Integer[] squares, int comparing, int working, int swapping) {

        arrayWrapper.removeAll();

        // Do not let swapping and pivot override comparing and working
        if (swapping == comparing || swapping == working) swapping = -1;

        for (int i = 0; i < squarePanels.length; ++i) {

            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth,
                    (int) (squares[i] * sizeModifier)));

            if (i == comparing) {
                squarePanels[i].setBackground(COMPARING_COLOR);
            }
            else if (i == working) {
                squarePanels[i].setBackground(WORKING_COLOR);
            }
            else if (i == swapping) {
                squarePanels[i].setBackground(SWAPPING_COLOR);
            }
            else {
                squarePanels[i].setBackground(BAR_COLOR);
            }
            arrayWrapper.add(squarePanels[i], grid);
        }

        repaint();
        validate();
    }

    public void switchSortButton(boolean state) {
        sort.setEnabled(state);
    }

    public void switchStopButton(boolean state) {
        stop.setEnabled(state);
    }

    public void setVisualSortingOptions() {
        stop.setEnabled(true);
        reset.setEnabled(true);

        selectSpeed.setEnabled(false);
        selectSize.setEnabled(false);
        selection.setEnabled(false);

        sorted.setEnabled(false);
        reversedSorted.setEnabled(false);
    }

    public void setStartingOptions() {
        sort.setEnabled(true);
        stop.setEnabled(false);
        reset.setEnabled(true);
        step.setEnabled(true);

        selectSpeed.setEnabled(true);
        selectSize.setEnabled(true);
        selection.setEnabled(true);

        sorted.setEnabled(true);
        reversedSorted.setEnabled(false);
    }

    public void addTimeToLogs(long time) {

        timerFrame.drawTimeLogs(
                (String) popupSelection.getSelectedItem(),
                time / 1000000.0,
                Integer.parseInt(popupField.getText()));
    }

    private void timerPopup() {

        popupSelection = new JComboBox<>(ALGORITHMS);
        popupField = new JTextField("100");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Algorithm"));
        panel.add(popupSelection);
        panel.add(new JLabel("Size"));
        panel.add(popupField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Timer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            if (timerFrame == null) timerFrame = new TimerFrame();
            else timerFrame.setVisible(true);

            SortingVisualizer.timerSort((String) popupSelection.getSelectedItem());
        }
    }

    private void legendPopup() {

        JPanel wrapper = new JPanel(new GridLayout(3, 3));

        JPanel comparingWrapper = new JPanel(new GridLayout(1, 2));
        comparingWrapper.setBorder(BorderFactory.createEmptyBorder(5, 2, 3, 2));
        JPanel firstColor = new JPanel();
        firstColor.setBackground(COMPARING_COLOR);
        comparingWrapper.add(firstColor);
        comparingWrapper.add(new JLabel("   Compare to"));

        JPanel movingWrapper = new JPanel(new GridLayout(1, 2));
        movingWrapper.setBorder(BorderFactory.createEmptyBorder(5, 2, 3, 2));
        JPanel secondColor = new JPanel();
        secondColor.setBackground(WORKING_COLOR);
        movingWrapper.add(secondColor);
        movingWrapper.add(new JLabel("   Traversing"));

        JPanel swappingWrapper = new JPanel(new GridLayout(1, 2));
        swappingWrapper.setBorder(BorderFactory.createEmptyBorder(5, 2, 3, 2));
        JPanel thirdColor = new JPanel();
        thirdColor.setBackground(SWAPPING_COLOR);
        swappingWrapper.add(thirdColor);
        swappingWrapper.add(new JLabel("   Write to"));

        wrapper.add(comparingWrapper);
        wrapper.add(movingWrapper);
        wrapper.add(swappingWrapper);

        JOptionPane.showConfirmDialog(null, wrapper, "Legend",
                JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

}

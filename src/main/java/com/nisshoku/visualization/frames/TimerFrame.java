package com.nisshoku.visualization.frames;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public final class TimerFrame extends JFrame {

    private final Color BACKGROUND_COLOR = new Color(189, 195, 199);

    private LinkedList<JPanel> logs;
    private JPanel performanceWrapper;

    public TimerFrame() {
        super("History");

        logs = new LinkedList<>();

        JLabel name = new JLabel("NAME");;
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel size = new JLabel("SIZE");
        size.setHorizontalAlignment(SwingConstants.CENTER);
        size.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel time = new JLabel("TIME [ms]");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel labelPane = new JPanel(new GridLayout(1, 3));
        labelPane.setBackground(BACKGROUND_COLOR);
        labelPane.add(name);
        labelPane.add(time);
        labelPane.add(size);

        performanceWrapper = new JPanel(new BorderLayout());
        performanceWrapper.setBackground(BACKGROUND_COLOR);

        JScrollPane scrollPane = new JScrollPane(performanceWrapper);
        scrollPane.setBackground(BACKGROUND_COLOR);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BACKGROUND_COLOR);
        wrapper.add(labelPane, BorderLayout.NORTH);
        wrapper.add(scrollPane);

        add(wrapper);

        setSize(250, 300);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocation(1000, 50);
    }

    public void drawTimeLogs(String name, double performanceTime, int size) {

        performanceWrapper.removeAll();

        JLabel nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel sizeLabel = new JLabel(String.valueOf(size));
        sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel timeLabel = new JLabel(String.valueOf(performanceTime));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelPane = new JPanel(new GridLayout(1, 3));
        labelPane.add(nameLabel);
        labelPane.add(timeLabel);
        labelPane.add(sizeLabel);
        labelPane.setBackground(new Color(231, 76, 60));

        logs.addFirst(labelPane);

        JPanel pane = new JPanel(new GridLayout(logs.size(), 1));
        pane.add(labelPane);

        for (JPanel panel : logs) {
            if (panel != labelPane)
                panel.setBackground(BACKGROUND_COLOR);
            pane.add(panel);
        }

        performanceWrapper.add(pane, BorderLayout.NORTH);

        repaint();
        validate();
    }
}

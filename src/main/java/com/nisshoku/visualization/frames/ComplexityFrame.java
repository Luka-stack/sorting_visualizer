package com.nisshoku.visualization.frames;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

final public class ComplexityFrame extends JFrame {

    public ComplexityFrame() {

        super("Complexity");

        JLabel algLabel = new JLabel("Algorithm");
        algLabel.setHorizontalAlignment(SwingConstants.CENTER);
        algLabel.setVerticalAlignment(SwingConstants.CENTER);
        algLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        JPanel algPanel = new JPanel();
        algPanel.setBackground(new Color(238,238,200));
        algPanel.add(algLabel);

        JLabel timeComLabel = new JLabel("Time Complexity");
        timeComLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeComLabel.setBorder(BorderFactory.createEmptyBorder(3, 2, 2, 2));

        JLabel bestLabel = new JLabel("Best");
        bestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bestLabel.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 25));

        JLabel avgLabel = new JLabel("Average");
        avgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgLabel.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 25));

        JLabel worstLabel = new JLabel("Worst");
        worstLabel.setHorizontalAlignment(SwingConstants.CENTER);
        worstLabel.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 25));

        JPanel compPanel = new JPanel(new BorderLayout());
        compPanel.add(timeComLabel, BorderLayout.PAGE_START);
        compPanel.add(bestLabel, BorderLayout.WEST);
        compPanel.add(avgLabel, BorderLayout.CENTER);
        compPanel.add(worstLabel, BorderLayout.EAST);
        compPanel.setBackground(new Color(238,238,200));

        JPanel headerLayout = new JPanel();
        headerLayout.setBackground(new Color(238,238,200));
        headerLayout.add(algPanel);
        headerLayout.add(compPanel);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(headerLayout, BorderLayout.PAGE_START);
        wrapper.add(addAllComplexity());

        add(wrapper);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(50, 50);
    }

    private JPanel addAllComplexity() {

        Map<String, String[]> complexities = Map.ofEntries(
                Map.entry("Selection", new String[]{"O(n^2)",      "O(n^2)",        "O(n^2)"     }),
                Map.entry("Bubble",    new String[]{"O(n)",        "O(n^2)",        "O(n^2)"     }),
                Map.entry("Insertion", new String[]{"O(n)",        "O(n^2)",        "O(n^2)"     }),
                Map.entry("Gnome",     new String[]{"O(n)",        "O(n^2)",        "O(n^2)"     }),
                Map.entry("Radix",     new String[]{"O(nk)",       "O(nk)",         "O(nk)"      }),
                Map.entry("Shell",     new String[]{"O(n log(n))", "O(n log^2(n))", "O(n^2)"     }),
                Map.entry("Quick",     new String[]{"O(n log(n))", "O(n log(n))",   "O(n^2)"     }),
                Map.entry("Heap",      new String[]{"O(n log(n))", "O(n log(n))",   "O(n log(n))"}),
                Map.entry("Merge",     new String[]{"O(n log(n))", "O(n log(n))",   "O(n log(n))"})
        );

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        int even = -1;
        for (Map.Entry<String, String[]> entry : complexities.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 4));
            rowPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            if (even == -1) {
                rowPanel.setBackground(new Color(189, 195, 199));
            }
            else {
                rowPanel.setBackground(new Color(238,238,200));
            }

            even *= -1;

            JLabel name = new JLabel(key);
            name.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel timeBest = new JLabel(values[0]);
            timeBest.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel timeAvg = new JLabel(values[0]);
            timeAvg.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel timeWorst = new JLabel(values[0]);
            timeWorst.setHorizontalAlignment(SwingConstants.CENTER);

            rowPanel.add(name);
            rowPanel.add(timeBest);
            rowPanel.add(timeAvg);
            rowPanel.add(timeWorst);

            resultPanel.add(rowPanel);
        }

        return resultPanel;
    }
}

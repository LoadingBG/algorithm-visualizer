package visualizer;

import visualizer.algorithms.BubbleSort;
import visualizer.algorithms.StepAlgorithm;
import visualizer.algorithms.StepInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.List;

public class Main {
    private static final int[] arr = { 5, 2, 4, 1, 3 };
    private static final StepAlgorithm alg = new BubbleSort(arr);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));
        frame.getContentPane().setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        frame.add(canvas);

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                while (alg.hasNextStep()) {
                    try { Thread.sleep(500); } catch (InterruptedException ex) {}
                    drawVisual(canvas, alg.nextStep());
                }
            }
        });
    }

    private static void drawVisual(Canvas canvas, StepInfo info) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int offsetX = canvas.getWidth() / 20;
        int offsetY = canvas.getHeight() / 20;
        int colWidth = (canvas.getWidth() - 2 * offsetX) / info.columns().size();
        int spacing = colWidth / 10 / 2;

        int biggest = info.columns().stream().mapToInt(AbstractMap.SimpleEntry::getKey).max().getAsInt();
        double heightUnit = (canvas.getHeight() - offsetY * 2.0) / biggest;
        List<AbstractMap.SimpleEntry<Integer, Color>> columns = info.columns();
        for (int i = 0; i < columns.size(); ++i) {
            g2d.setColor(columns.get(i).getValue());
            int height = (int) (columns.get(i).getKey() * heightUnit);
            g2d.fillRect(
                    offsetX + colWidth * i + spacing,
                    canvas.getHeight() - offsetY - height,
                    colWidth - spacing,
                    height
            );
        }
    }
}

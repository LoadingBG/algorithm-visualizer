package visualizer.algorithms;

import java.awt.Color;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

public record StepInfo(List<AbstractMap.SimpleEntry<Integer, Color>> columns) {
    public static final Color UNSELECTED = Color.WHITE;
    public static final Color SELECTED = Color.RED;
    public static final Color PIVOT = Color.BLUE;
    public static final Color SORTED = Color.GREEN;

    public static StepInfo createFinished(int[] array) {
        return new StepInfo(
                Arrays.stream(array)
                        .mapToObj(len -> new AbstractMap.SimpleEntry<>(len, Color.GREEN))
                        .toList()
        );
    }

    public static AbstractMap.SimpleEntry<Integer, Color> entry(int len, Color color) {
        return new AbstractMap.SimpleEntry<>(len, color);
    }
}

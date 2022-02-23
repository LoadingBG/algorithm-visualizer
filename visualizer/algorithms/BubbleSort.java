package visualizer.algorithms;

import java.awt.Color;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements StepAlgorithm {
    private final int[] array;
    private int pivotIdx;
    private int sortedStartIdx;
    boolean isRunning;

    public BubbleSort(int[] array) {
        this.array = array;
        pivotIdx = 0;
        sortedStartIdx = array.length;
        isRunning = true;
    }

    @Override
    public boolean hasNextStep() {
        return isRunning;
    }

    @Override
    public StepInfo nextStep() {
        if (sortedStartIdx == 1) {
            isRunning = false;
            return StepInfo.createFinished(array);
        }

        StepInfo info = generateColors();

        if (array[pivotIdx] > array[pivotIdx + 1]) {
            int temp = array[pivotIdx];
            array[pivotIdx] = array[pivotIdx + 1];
            array[pivotIdx + 1] = temp;
        }
        ++pivotIdx;

        if (pivotIdx > sortedStartIdx - 2) {
            pivotIdx = 0;
            --sortedStartIdx;
        }

        return info;
    }

    private StepInfo generateColors() {
        List<AbstractMap.SimpleEntry<Integer, Color>> colors = new ArrayList<>();
        for (int i = 0; i < pivotIdx; ++i) {
            colors.add(StepInfo.entry(array[i], StepInfo.UNSELECTED));
        }
        colors.add(StepInfo.entry(array[pivotIdx], StepInfo.PIVOT));
        colors.add(StepInfo.entry(array[pivotIdx + 1], StepInfo.SELECTED));
        for (int i = pivotIdx + 2; i < sortedStartIdx; ++i) {
            colors.add(StepInfo.entry(array[i], StepInfo.UNSELECTED));
        }
        for (int i = sortedStartIdx; i < array.length; ++i) {
            colors.add(StepInfo.entry(array[i], StepInfo.SORTED));
        }
        return new StepInfo(colors);
    }
}

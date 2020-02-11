package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class ShellSortV2 extends SortingThreadV2 {

    public ShellSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        int n = toSortArray.length;

        for (int gap = n/2; gap > 0; gap /= 2) {

            // insertion sort for gap
            for (int i = gap; i < n; i += 1) {

                // make a gap at position i
                int tmp = toSortArray[i];

                // shift elements up until the correct location i
                int j;
                for (j = i; j >= gap && toSortArray[j - gap] > tmp; j -= gap) {

                    if (SortingVisualizer.resetFlag == 1) return;
                    drawAndWaitOrSleep(j, j - gap, -1);

                    toSortArray[j] = toSortArray[j - gap];
                }
                toSortArray[j] = tmp;
            }
            if (SortingVisualizer.waitFlag == 1) waitForEvent();
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        int n = toSortArray.length;

        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {

                int tmp = toSortArray[i];
                int j;

                for (j = i; j >= gap && toSortArray[j - gap] > tmp; j -= gap) {
                    toSortArray[j] = toSortArray[j - gap];
                }
                toSortArray[j] = tmp;
            }
        }
    }
}

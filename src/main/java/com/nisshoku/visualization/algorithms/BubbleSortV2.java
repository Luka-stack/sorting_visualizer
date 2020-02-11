package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class BubbleSortV2 extends SortingThreadV2 {

    public BubbleSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        int tmp;
        int size = toSortArray.length;

        for (int i = 0; i < size - 1; ++i) {

            for (int j = 1; j < size - i; ++j) {
                if (toSortArray[j-1] > toSortArray[j]) {
                    tmp = toSortArray[j-1];
                    toSortArray[j-1] = toSortArray[j];
                    toSortArray[j] = tmp;
                }

                if (SortingVisualizer.resetFlag == 1) return;

                drawAndWaitOrSleep(j, j + 1, toSortArray.length - (i + 1));
            }

            if (SortingVisualizer.waitFlag == 1) waitForEvent();
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        int tmp;
        int size = toSortArray.length;

        for (int i = 0; i < size - 1; ++i) {

            for (int j = 1; j < size - i; ++j) {
                if (toSortArray[j-1] > toSortArray[j]) {
                    tmp = toSortArray[j-1];
                    toSortArray[j-1] = toSortArray[j];
                    toSortArray[j] = tmp;
                }
            }
        }
    }
}

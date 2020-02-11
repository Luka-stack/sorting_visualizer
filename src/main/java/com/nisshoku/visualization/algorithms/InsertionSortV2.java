package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class InsertionSortV2 extends SortingThreadV2 {

    public InsertionSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        for (int i = 1; i < toSortArray.length; ++i) {

            int key = toSortArray[i];
            int j = i - 1;

            while (j >= 0 && toSortArray[j] > key) {
                toSortArray[j + 1] = toSortArray[j];

                if (SortingVisualizer.resetFlag == 1) return;
                drawAndWaitOrSleep(j, i, -1);

                --j;
            }

            toSortArray[j + 1] = key;

            drawAndWaitOrSleep(j, i, -1);
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        for (int i = 1; i < toSortArray.length; ++i) {

            int key = toSortArray[i];
            int j = i - 1;

            while (j >= 0 && toSortArray[j] > key) {
                toSortArray[j + 1] = toSortArray[j];
                --j;
            }
            toSortArray[j + 1] = key;
        }
    }
}

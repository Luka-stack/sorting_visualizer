package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class SelectionSortV2 extends SortingThreadV2 {

    public SelectionSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        for (int i = 0; i < toSortArray.length - 1; ++i) {

            int min_idx = i;
            for (int j = i+1; j < toSortArray.length; ++j) {

                if (toSortArray[j] < toSortArray[min_idx]) {
                    min_idx = j;

                    drawAndWaitOrSleep(min_idx, j, i);
                }

                if (SortingVisualizer.resetFlag == 1) return;
                drawAndWaitOrSleep(min_idx, j, i);
            }
            int tmp = toSortArray[min_idx];
            toSortArray[min_idx] = toSortArray[i];
            toSortArray[i] = tmp;
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        for (int i = 0; i < toSortArray.length - 1; ++i) {

            int min_idx = i;
            for (int j = i+1; j < toSortArray.length; ++j) {
                if (toSortArray[j] < toSortArray[min_idx]) min_idx = j;
            }
            int tmp = toSortArray[min_idx];
            toSortArray[min_idx] = toSortArray[i];
            toSortArray[i] = tmp;
        }
    }
}

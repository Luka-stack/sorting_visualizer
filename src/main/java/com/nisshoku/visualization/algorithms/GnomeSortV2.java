package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class GnomeSortV2 extends SortingThreadV2 {

    public GnomeSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        int index = 0;

        while (index < toSortArray.length) {

            if (index == 0) {
                ++index;
            }

            if (SortingVisualizer.resetFlag == 1) return;
            drawAndWaitOrSleep(index, index - 1, -1);

            if (toSortArray[index] >= toSortArray[index - 1]) {
                ++index;
            }
            else {
                int tmp = toSortArray[index];
                toSortArray[index] = toSortArray[index - 1];
                toSortArray[index - 1] = tmp;
                --index;

                if (SortingVisualizer.waitFlag == 1) waitForEvent();
            }
        }
        frame.preDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        int index = 0;

        while (index < toSortArray.length) {

            if (index == 0) ++index;
            if (toSortArray[index] >= toSortArray[index - 1]) ++index;
            else {
                int tmp = toSortArray[index];
                toSortArray[index] = toSortArray[index - 1];
                toSortArray[index - 1] = tmp;
                --index;
            }
        }
    }

}

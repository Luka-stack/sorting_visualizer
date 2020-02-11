package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class QuickSortV2 extends SortingThreadV2 {

    private int timer;

    public QuickSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        timer = 0;
        quickSort(0, toSortArray.length - 1);

        if (SortingVisualizer.resetFlag == 0) frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        timer = 1;
        quickSort(0, toSortArray.length - 1);
    }

    private void quickSort(int low, int high) {

        if (low < high) {

            // find the pivot
            int pivot = partition(low, high);
            if (timer == 0 && SortingVisualizer.resetFlag == 1) return;

            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }
    }

    private int partition(int low, int high) {

        int pivot = toSortArray[high];
        int i = (low - 1);

        for (int j = low; j < high; ++j) {
            if (toSortArray[j] < pivot)
            {
                ++i;
                int tmp = toSortArray[i];
                toSortArray[i] = toSortArray[j];
                toSortArray[j] = tmp;
            }

            if (timer == 0) {
                if (SortingVisualizer.resetFlag == 1) return -1;
                drawAndWaitOrSleep(high, j, i);
            }
        }

        if (timer == 0 ) drawAndWaitOrSleep(high, i + 1, -1);

        int tmp = toSortArray[i + 1];
        toSortArray[i + 1] = toSortArray[high];
        toSortArray[high] = tmp;

        return i + 1;
    }
}

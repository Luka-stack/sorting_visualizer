package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public final class HeapSortV2 extends SortingThreadV2 {

    private int timer;

    public HeapSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        timer = 0;
        int size = toSortArray.length;

        for (int i = size / 2-1; i >= 0; --i) {
            heapify(size, i);
        }

        for (int i = size -1; i >= 0; --i) {

            if (SortingVisualizer.resetFlag == 1) return;
            else drawAndWaitOrSleep(-1, i, 0);

            int swap = toSortArray[0];
            toSortArray[0] = toSortArray[i];
            toSortArray[i] = swap;

            heapify(i, 0);
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        timer = 1;
        int size = toSortArray.length;

        for (int i = size / 2-1; i >= 0; --i) heapify(size, i);

        for (int i = size -1; i >= 0; --i) {
            int swap = toSortArray[0];
            toSortArray[0] = toSortArray[i];
            toSortArray[i] = swap;

            heapify(i, 0);
        }
    }

    private void heapify(int size, int index) {

        if (timer == 0 && SortingVisualizer.resetFlag == 1) return;

        int root = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if (left < size && toSortArray[left] > toSortArray[root]) {

            if (timer == 0) drawAndWaitOrSleep(left, root, -1);
            root = left;
        }

        if (right < size && toSortArray[right] > toSortArray[root]) {

            if (timer == 0) drawAndWaitOrSleep(right, root, -1);
            root = right;
        }

        if (root != index) {

            if (timer == 0) drawAndWaitOrSleep(index, root, -1);

            int swap = toSortArray[index];
            toSortArray[index] = toSortArray[root];
            toSortArray[root] = swap;

            heapify(size, root);
        }
    }
}

package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;

public final class MergeSortV2 extends SortingThreadV2 {

    private int timer;

    public MergeSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        timer = 0;
        mergeSort(0, toSortArray.length - 1);

        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        timer = 1;
        mergeSort(0, toSortArray.length - 1);
    }

    private void mergeSort(int low, int high) {

        if (high > low) {

            // find the middle element
            int mid = (low + high) / 2;

            mergeSort(low, mid);
            mergeSort(mid + 1, high);

            merge(low, mid, high);

            if (timer == 0) waitForEvent();
        }
    }

    private void merge(int low, int mid, int high) {

        // sizes of two subarrays
        int n1 = mid - low + 1;
        int n2 = high - mid;

        // temporary arrays
        int[] left = new int[n1];
        int[] right = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; ++i) left[i] = toSortArray[low + i];
        for (int i = 0; i < n2; ++i) right[i] = toSortArray[mid + 1 + i];

        // merge temp arrays

        // initial indexes of first and second subarrays
        int i=0, j=0;

        // initial index of merged subarray
        int k = low;
        while (i < n1 && j < n2) {

            if (timer == 0) drawAndWaitOrSleep(low + 1, mid + j, k);

            if (left[i] <= right[j]) {
                toSortArray[k] = left[i];
                ++i;
            }
            else {
                toSortArray[k] = right[j];
                ++j;
            }

            ++k;
        }

        // copy remaining elements of left array
        while (i < n1) {

            if (timer == 0) drawAndWaitOrSleep(low + i, -1, k);

            toSortArray[k] = left[i];
            ++k;
            ++i;
        }

        // copy remaining elements of right array
        while (j < n2) {

            if (timer == 0) drawAndWaitOrSleep(-1, mid + j, k);

            toSortArray[k] = right[j];
            ++k;
            ++j;
        }
    }
}

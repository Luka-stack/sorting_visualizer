package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

import java.util.Arrays;

public final class RadixSortV2 extends SortingThreadV2 {

    private int timer;

    public RadixSortV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        super(toSortArray, frame, isVisualiser);
    }

    @Override
    protected void visualizeSort() {

        timer = 0;

        // to get max number of digits
        int max = Arrays.stream(toSortArray).mapToInt(Integer::intValue).max().getAsInt();

        // exp -> 10 ^ i
        for (int exp = 1; max / exp > 0; exp *= 10) {

            countSort(exp);

            if (SortingVisualizer.waitFlag == 1) waitForEvent();
            if (SortingVisualizer.resetFlag == 1) return;
        }
        frame.reDrawArray(toSortArray);
    }

    @Override
    protected void timeSort() {

        timer = 1;

        int max = Arrays.stream(toSortArray).mapToInt(Integer::intValue).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) countSort(exp);
    }

    private void countSort(int exp) {

        Integer[] output = new Integer[toSortArray.length];
        Arrays.fill(output, 0);
        int[] buckets = new int[10]; // bucket for digits

        // number of occurrences
        for (Integer integer : toSortArray) {
            buckets[(integer / exp) % 10] += 1;
        }

        // change number of occurrences to actual position
        for (int i = 1; i < 10; ++i) {
            buckets[i] += buckets[i - 1];
        }

        // sort by current digit (point by exp)
        for (int i = toSortArray.length - 1; i >= 0; --i) {
            output[ buckets[ (toSortArray[i] / exp) % 10] - 1] = toSortArray[i];

            if (timer == 0) {
                if (SortingVisualizer.resetFlag == 1) return;

                frame.reDrawArray(output);
                try {
                    sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buckets[ (toSortArray[i] / exp) % 10 ] -= 1;
        }

        toSortArray = output;
    }
}

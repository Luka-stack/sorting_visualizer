package com.nisshoku.visualization;

import com.nisshoku.visualization.algorithms.*;
import com.nisshoku.visualization.frames.VisualizeFrame;

import java.util.Random;

public final class SortingVisualizer {

    // thread flags
    public static int resetFlag = 0;
    public static int waitFlag = 0;

    private static VisualizeFrame frame;
    private static Integer[] toSortArray;
    private static Integer[] toRestoreArray;

    public static boolean isSorting = false;
    public static int blockWidth = 5;
    public static int sleep = 200;
    public static int dataCount = 50;
    public static boolean sorted = false;
    public static boolean reversed = false;

    public static void main(String... args) {
        frame = new VisualizeFrame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }

    public static void visualizerSort(String type, boolean steps) {

        synchronized (frame) {
            if (steps) {
                waitFlag = 1;
                // disable stop and enable sort
                frame.switchStopButton(false);
                frame.switchSortButton(false);
            }
            else {
                waitFlag = 0;
                // disable sort and enable stop
                frame.switchSortButton(false);
                frame.switchStopButton(true);
            }

            frame.notify();
        }

        if (!isSorting) {
            resetFlag = 0;
            isSorting = true;
            startThead(type, true);
        }
    }

    public static void timerSort(String type) {
        startThead(type, false);
    }

    public static void stopThread() {
        waitFlag = 1;
        // enable sort and disable stop
        frame.switchStopButton(false);
    }

    public static void restoreArray() {

        resetFlag = 1;
        synchronized (frame) {
            frame.notify();
        }

        //sortingThread = null;
        isSorting = false;
        toSortArray = toRestoreArray.clone();

        frame.reDrawArray(toSortArray);
    }

    public static void resetArray()   {

        if (isSorting) return;

        toSortArray = new Integer[dataCount];
        blockWidth = Math.max(dataCount, 1);
        Random rnd = new Random();

        for (int i = 0; i < dataCount; ++i) {
            if (!sorted) {
                toSortArray[i] = rnd.nextInt(dataCount + 1) + 10;
            }
            else {
                toSortArray[i] = i + 10;
            }
        }

        if (reversed && sorted) {
            int tmp;
            for (int i = 0; i < toSortArray.length / 2; ++i) {
                tmp = toSortArray[i];
                toSortArray[i] = toSortArray[toSortArray.length - i - 1];
                toSortArray[toSortArray.length - i - 1] = tmp;
            }
        }

        toRestoreArray = toSortArray.clone();
        frame.preDrawArray(toSortArray);
    }

    private static void startThead(String type, boolean isVisualizer) {

        switch (type) {
            case "Bubble":
                new BubbleSortV2(toRestoreArray, frame, isVisualizer).start();
                break;
            case "Selection":
                new SelectionSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Insertion":
                new InsertionSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Gnome":
                new GnomeSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Radix":
                new RadixSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Shell":
                new ShellSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Quick":
                new QuickSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Heap":
                new HeapSortV2(toSortArray, frame, isVisualizer).start();
                break;
            case "Merge":
                new MergeSortV2(toSortArray, frame, isVisualizer).start();
                break;
            default:
                isSorting = false;
                return;
        }

        resetFlag = 0;
        frame.setVisualSortingOptions();
    }
}

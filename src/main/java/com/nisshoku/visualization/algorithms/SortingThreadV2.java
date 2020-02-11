package com.nisshoku.visualization.algorithms;

import com.nisshoku.visualization.frames.VisualizeFrame;
import com.nisshoku.visualization.SortingVisualizer;

public abstract class SortingThreadV2 extends Thread {

    protected Integer[] toSortArray;
    protected VisualizeFrame frame;
    private boolean isVisualiser;

    public SortingThreadV2(Integer[] toSortArray, VisualizeFrame frame, boolean isVisualiser) {
        this.toSortArray = toSortArray;
        this.frame = frame;
        this.isVisualiser = isVisualiser;
    }

    @Override
    public void run() {

        if (isVisualiser) visualizeSort();
        else {
            long start = System.nanoTime();
            timeSort();
            frame.addTimeToLogs(System.nanoTime() - start);
        }

        // enable buttons
        frame.setStartingOptions();
        SortingVisualizer.isSorting = false;
    }

    protected void drawAndWaitOrSleep(int comparing, int working, int swapping) {

        frame.reDrawArray(toSortArray, comparing, working, swapping);

        if (SortingVisualizer.waitFlag == 1) {
            waitForEvent();
        }
        else {
            try {
                sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected void waitForEvent() {

        frame.switchSortButton(true);

        synchronized (frame) {
            try {
                frame.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    protected abstract void visualizeSort();
    protected abstract void timeSort();
}

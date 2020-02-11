package com.nisshoku.visualization.frames;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public final class DescribeFrame extends JFrame {

    public DescribeFrame() {

        super("Sorting Explained");

        JTextPane pane = new JTextPane();
        pane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        pane.setBackground(new Color(238,238,200));
        pane.setEditable(false);

        SimpleAttributeSet attributeBold = new SimpleAttributeSet();
        SimpleAttributeSet attributePlain = new SimpleAttributeSet();
        StyleConstants.setBold(attributeBold, true);
        pane.setCharacterAttributes(attributeBold, true);

        Document doc = pane.getStyledDocument();

        try {

            doc.insertString(doc.getLength(), "Selection Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tSelection sort algorithm is based on the idea of finding the minimum or maximum element\n" +
                            "\tin an unsorted array and then putting it in its correct position in a sorted array \n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Bubble Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tBubble sort is based on the idea of repeatedly comparing pairs of adjacent elements and\n" +
                            "\tthen swapping their positions if they exists in the wrong order\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Insertion Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tInsertion sort is based on the idea that one element from the input elements is consumed\n" +
                            "\tin each iteration to find its correct position i.e, the position to which it belongs in\n" +
                            "\ta sorted array\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Gnome Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tPut items in order by comparing the current item with the previous item. If they are in\n" +
                            "\torder, move to the next item (or stop if the end is reached). If they are out of order,\n" +
                            "\tswap them and move to the previous item. If there is no previous item,\n" +
                            "\tmove to the next item\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Radix Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tFor each digit i where i varies from the least significant digit to the most significant\n" +
                            "\tdigit of a number. Sort input array using Count Sort algorithm according to ith digit\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Count Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tIn Counting Sort, the frequencies of distinct elements of the array to be sorted is counted\n" +
                            "\tand stored in an auxiliary array, by mapping its value as an index of the auxiliary array\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Shell Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tIn Shell Sort, elements at a specific interval are sorted. The interval between the elements\n" +
                            "\tis gradually decreased based on the sequence used. The performance of the Shell Sort\n" +
                            "\tdepends on the type of sequence used for a given input array\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Quick Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tQuick Sort is based on the divide-and-conquer approach based on the idea of choosing one\n" +
                            "\telement as a pivot element and partitioning the array around it such that:\n" +
                            "\tLeft side of pivot contains all the elements that are less than the pivot element\n" +
                            "\tRight side contains all elements greater than the pivot\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Heap Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tHeaps can be used in sorting an array. In max-heaps, maximum element will always\n" +
                            "\tbe at the root. Heap Sort uses this property of heap to sort the array.\n" +
                            "\tConsider an array which is to be sorted using Heap Sort:\n" +
                            "\t* Initially build a max heap of elements\n" +
                            "\t* The root element will contain maximum element. After that, swap this element with the\n" +
                            "\t     last element and heapify the max heap excluding the last element which is already in\n" +
                            "\t     its correct position and then decrease the length of heap by one.\n" +
                            "\t* Repeat the step 2, until the elements are in their correct position.\n\n\n",
                    attributePlain);

            doc.insertString(doc.getLength(), "Merge Sort\n", attributeBold);
            doc.insertString(doc.getLength(),
                    "\tMerge Sort is a divide-and-conquer algorithm based on the idea of breaking down a list\n" +
                            "\tinto several sub-lists until each sublist consists of a single element and merging\n" +
                            "\tthose those sublists in a manner that results into a sorted list.\n" +
                            "\t* Divide the unsorted list into N sublists, each containing 1 element\n" +
                            "\t* Take adjacent pairs of two singleton lists and merge them to form a list of 2 elements.\n" +
                            "\t     N will now convert into N/2 lists of size 2.\n" +
                            "\t* Repeat the process till a single sorted list is obtained.\n\n\n",
                    attributePlain);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }



        JScrollPane scrollPane = new JScrollPane(pane);

        add(scrollPane);
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(50, 50);
    }
}

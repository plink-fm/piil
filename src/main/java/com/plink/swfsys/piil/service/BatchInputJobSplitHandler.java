package com.plink.swfsys.piil.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class BatchInputJobSplitHandler extends RecursiveAction {

    /*
    TODO: finish this class and wire up to service.
    Measure typical throughput when persistemce is wired up, etc. and iterate on this for divide-and-conquer
    */

    @Autowired
    private ProductInfoIngestionLibraryService productInfoIngestionLibraryService;

    private List<String> inputLines;
    private static final int THRESHOLD = 5000;  // per the Google, an average grocery store has ~40k items

    public BatchInputJobSplitHandler(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    @Override
    protected void compute() {
        if (inputLines.size() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processing(inputLines);
        }
    }

    private List<BatchInputJobSplitHandler> createSubtasks() {
        List<BatchInputJobSplitHandler> subtasks = new ArrayList<>();

        List<String> firstBatch =  inputLines.subList(0, inputLines.size() / 2);
        List<String> secondBatch = inputLines.subList(inputLines.size() / 2, inputLines.size());

        // TODO: at a minimum create n parts for n cores, source from config

        subtasks.add(new BatchInputJobSplitHandler(firstBatch));
        subtasks.add(new BatchInputJobSplitHandler(secondBatch));

        return subtasks;
    }

    private void processing(List<String> inputLines) {
        productInfoIngestionLibraryService.processStore(inputLines);
    }
}

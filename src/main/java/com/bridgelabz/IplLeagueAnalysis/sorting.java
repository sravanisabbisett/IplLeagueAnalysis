package com.bridgelabz.IplLeagueAnalysis;

import java.util.Comparator;
import java.util.List;

public class sorting {
    public <E> void sort(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E ipl = csvList.get(j);
                E iplNext = csvList.get(j + 1);
                if (comparator.compare(ipl, iplNext) > 0) {
                    csvList.set(j, iplNext);
                    csvList.set(j + 1, ipl);
                }
            }
        }
    }
}

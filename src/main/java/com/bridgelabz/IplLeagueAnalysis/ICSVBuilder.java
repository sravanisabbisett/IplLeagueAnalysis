package com.bridgelabz.IplLeagueAnalysis;

import java.io.Reader;
import java.util.List;

public interface  ICSVBuilder<E> {
    public <E> List<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}

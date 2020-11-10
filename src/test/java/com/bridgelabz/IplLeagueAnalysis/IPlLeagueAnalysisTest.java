package com.bridgelabz.IplLeagueAnalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class IPlLeagueAnalysisTest {
    IplLeagueAnalysis iplLeagueAnalysis;

    public static final String BATTING_FILE=".\\src\\main\\java\\com\\bridgelabz\\IplLeagueAnalysis\\batting.csv";
    public static final String BOWLING_FILE=".\\src\\main\\java\\com\\bridgelabz\\IplLeagueAnalysis\\bowling.csv";

    @Before
    public void setUp() throws Exception {
        iplLeagueAnalysis=new IplLeagueAnalysis();
    }
    @Test
    public void givenLoadBatingFile_shouldReturnTheNoOfRecords() throws IOException, CSVBuilderException {
        int numOfEntries=iplLeagueAnalysis.loadBatingdata(BATTING_FILE);
        Assert.assertEquals(101,numOfEntries);
    }
    @Test
    public void givenLoadBowlingFile_shouldReturnTheNoOfRecords() throws IOException, CSVBuilderException {
        int numOfEntries=iplLeagueAnalysis.loadBowlingData(BOWLING_FILE);
        Assert.assertEquals(99,numOfEntries);
    }
}

package com.bridgelabz.IplLeagueAnalysis;

import com.google.gson.Gson;
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
    public void givenLoadBatingFile_shouldReturnTheNoOfRecords() throws IplLeagueException {
        int numOfEntries=iplLeagueAnalysis.loadBatingdata(BATTING_FILE);
        Assert.assertEquals(101,numOfEntries);
    }
    @Test
    public void givenLoadBowlingFile_shouldReturnTheNoOfRecords() throws IOException, CSVBuilderException {
        int numOfEntries=iplLeagueAnalysis.loadBowlingData(BOWLING_FILE);
        Assert.assertEquals(99,numOfEntries);
    }
    @Test
    public void givenBatingData_ShouldSortedOnAverage_ShouldReturnResult() {
        try {
            String sortedIPLBattingData = iplLeagueAnalysis.getAverageWiseSortedIPLBattingData(BATTING_FILE);
            BatingAnalysisCsv[] battingAnalysisCSV = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
            Assert.assertEquals(0,battingAnalysisCSV[0].average,0.0);
            Assert.assertEquals(83.2,battingAnalysisCSV[100].average, 0.0);
        } catch (IplLeagueException ioException) {
            ioException.printStackTrace();
        }
    }

    @Test
    public void givenBattingData_shouldSortedOnStrikeRate_shouldReturnResult() throws IplLeagueException {
        String sortedIPLBattingData = iplLeagueAnalysis.getHighestStrikeRateIplBattingData(BATTING_FILE);
        BatingAnalysisCsv[] battingAnalysisCSV = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
        Assert.assertEquals(333.33,battingAnalysisCSV[100].strikeRate, 0.0);
    }
}

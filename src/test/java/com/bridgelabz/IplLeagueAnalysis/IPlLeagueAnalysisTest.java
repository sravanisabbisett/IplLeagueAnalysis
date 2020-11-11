package com.bridgelabz.IplLeagueAnalysis;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
    public void givenLoadBowlingFile_shouldReturnTheNoOfRecords() throws IplLeagueException {
        int numOfEntries=iplLeagueAnalysis.loadBowlingData(BOWLING_FILE);
        Assert.assertEquals(99,numOfEntries);
    }
    @Test
    public void givenBatingData_ShouldSortedOnAverage_ShouldReturnResult() {
        try {
            String sortedIPLBattingData = iplLeagueAnalysis.getAverageWiseSortedIPLBattingData(BATTING_FILE);
            BatingAnalysisCsv[] battingAnalysisCSV = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
            Assert.assertEquals(0,battingAnalysisCSV[0].average,0.0);
            Assert.assertEquals(83.2,battingAnalysisCSV[battingAnalysisCSV.length-1].average, 0.0);
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
    @Test
    public void givenBattingData_max6sAnd4s_shouldReturnResult() throws IplLeagueException {
        List sortedIPLBattingData = iplLeagueAnalysis.getMaximum4sAnd6sInBattingData(BATTING_FILE);
        String fours= (String) sortedIPLBattingData.get(0);
        String sixes= (String) sortedIPLBattingData.get(1);
        BatingAnalysisCsv[] battingAnalysisCSV1 = new Gson().fromJson(fours,BatingAnalysisCsv[].class);
        BatingAnalysisCsv[] battingAnalysisCSV2 = new Gson().fromJson(sixes,BatingAnalysisCsv[].class);
        Assert.assertEquals("Shikhar Dhawan",battingAnalysisCSV1[battingAnalysisCSV1.length-1].player);
        Assert.assertEquals("Andre Russell",battingAnalysisCSV2[battingAnalysisCSV2.length-1].player);
    }
    @Test
    public void givenBowlingData_shouldSortedOnAverage_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getMaximumBowlingAverage(BOWLING_FILE);
        BowlingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BowlingAnalysisCsv[].class);
        Assert.assertEquals(166.0,bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].average, 0.0);
    }
    @Test
    public void givenBatingData_shouldReturnHighestSRWith4sAnd6s_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getBestStrikeRateWith4sAnds6S(BATTING_FILE);
        BatingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
        Assert.assertEquals("Andre Russell",bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].player);
    }
    @Test
    public void givenBatingData_shouldReturnGreatAverageWithBestStrikeRate_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getGreatAverageWithBestStrikeRate(BATTING_FILE);
        BatingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
        Assert.assertEquals("MS Dhoni",bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].player);
    }
    @Test
    public void givenBatingData_shouldHighestRunsWithBestAverage_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getMaximumRumsWithBestAverage(BATTING_FILE);
        BatingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BatingAnalysisCsv[].class);
        Assert.assertEquals("David Warner ",bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].player);
    }
    @Test
    public void givenBowlingData_shouldGetHighestStrikeRate_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getMaximumStrikeRateForBowling(BOWLING_FILE);
        BowlingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BowlingAnalysisCsv[].class);
        Assert.assertEquals(120,bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].strikeRate, 0.0);
    }
    @Test
    public void givenBowlingData_shouldGetBestStrikingRate_shouldReturnResult() throws IplLeagueException{
        String sortedIPLBattingData = iplLeagueAnalysis.getBestEconomyRateInBowlers(BOWLING_FILE);
        BowlingAnalysisCsv[] bowlingAnalysisCsvs = new Gson().fromJson(sortedIPLBattingData, BowlingAnalysisCsv[].class);
        Assert.assertEquals(13.5,bowlingAnalysisCsvs[bowlingAnalysisCsvs.length-1].economyRate, 0.0);
    }
}

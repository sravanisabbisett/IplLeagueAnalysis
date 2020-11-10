package com.bridgelabz.IplLeagueAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IplLeagueAnalysis {
    List<BatingAnalysisCsv> batingAnalysisCsvList = null;
    List<BowlingAnalysisCsv> bowlingAnalysisCsvs=null;
    String filePath="C:\\Users\\PC\\IdeaProjects\\IPLLeagueAnalysis\\src\\main\\java\\com\\bridgelabz\\IplLeagueAnalysis\\BatinganalysisCsv.csv";

    public int loadBatingdata(String filePathCSV) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            batingAnalysisCsvList=csvBuilder.getCsvFileIterator(reader,BatingAnalysisCsv.class);
            return batingAnalysisCsvList.size();
        }catch (CSVBuilderException builderException){
            throw new CSVBuilderException(builderException.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
    public int loadBowlingData(String filePathCSV) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            bowlingAnalysisCsvs=csvBuilder.getCsvFileIterator(reader,BowlingAnalysisCsv.class);
            return bowlingAnalysisCsvs.size();
        }catch (CSVBuilderException builderException){
            throw new CSVBuilderException(builderException.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}

package com.bridgelabz.IplLeagueAnalysis;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class IplLeagueAnalysis {
    sorting sort=new sorting();
    List<BatingAnalysisCsv> batingAnalysisCsvList = null;
    List<BowlingAnalysisCsv> bowlingAnalysisCsvs=null;
    String filePath="C:\\Users\\PC\\IdeaProjects\\IPLLeagueAnalysis\\src\\main\\java\\com\\bridgelabz\\IplLeagueAnalysis\\BatinganalysisCsv.csv";



    public int loadBatingdata(String filePathCSV) throws IplLeagueException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            batingAnalysisCsvList=csvBuilder.getCsvFileIterator(reader,BatingAnalysisCsv.class);
            return batingAnalysisCsvList.size();
        }catch (CSVBuilderException builderException){
            throw new IplLeagueException(builderException.getMessage(),IplLeagueException.ExceptionType.UNABLE_TO_PARSE);
        }catch (IOException ioException){
            throw new IplLeagueException(ioException.getMessage(),IplLeagueException.ExceptionType.CSV_FILE_PROBLEM);
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
    public String getAverageWiseSortedIPLBattingData(String filePath) throws IplLeagueException {
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> battingAnalysisComparator = Comparator.comparing(battingAnalysisCSV -> battingAnalysisCSV.average);
        return returnJsonFile(batingAnalysisCsvList,battingAnalysisComparator);
    }

    public <E> String returnJsonFile(List<E> list,Comparator<E> comparator ){
        this.sort.sort(list,comparator);
        String sortedCensusData=new Gson().toJson(list);
        return sortedCensusData;
    }
}

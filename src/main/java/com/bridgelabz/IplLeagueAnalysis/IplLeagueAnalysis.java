package com.bridgelabz.IplLeagueAnalysis;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
    public int loadBowlingData(String filePathCSV) throws IplLeagueException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            bowlingAnalysisCsvs=csvBuilder.getCsvFileIterator(reader,BowlingAnalysisCsv.class);
            return bowlingAnalysisCsvs.size();
        }catch (CSVBuilderException builderException){
            throw new IplLeagueException(builderException.getMessage(),IplLeagueException.ExceptionType.UNABLE_TO_PARSE);
        }catch (IOException ioException){
            throw new IplLeagueException(ioException.getMessage(),IplLeagueException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
    public String getAverageWiseSortedIPLBattingData(String filePath) throws IplLeagueException {
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> battingAnalysisComparator = Comparator.comparing(battingAnalysisCSV -> battingAnalysisCSV.average);
        return returnJsonFile(batingAnalysisCsvList,battingAnalysisComparator);
    }
    public String getHighestStrikeRateIplBattingData(String filePath) throws IplLeagueException{
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> batingAnalysisCsvComparator=Comparator.comparing(batingAnalysisCsv -> batingAnalysisCsv.strikeRate);
        return returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator);
    }
    public List<String> getMaximum4sAnd6sInBattingData(String filePath) throws IplLeagueException {
        List<String> maxFoursAndSixes=new ArrayList<>();
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> batingAnalysisCsvComparator=Comparator.comparing(batingAnalysisCsv->batingAnalysisCsv.fours);
        Comparator<BatingAnalysisCsv>batingAnalysisCsvComparator1=Comparator.comparing(batingAnalysisCsv -> batingAnalysisCsv.sixes);
        maxFoursAndSixes.add(returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator));
        maxFoursAndSixes.add(returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator1));
        return maxFoursAndSixes;
    }
    public String getMaximumBowlingAverage(String filePath) throws IplLeagueException {
        loadBowlingData(filePath);
        Comparator<BowlingAnalysisCsv> bowlingAnalysisCsvComparator=Comparator.comparing(bowlingAnalysisCsv -> bowlingAnalysisCsv.average);
        return returnJsonFile(bowlingAnalysisCsvs,bowlingAnalysisCsvComparator);
    }
    public String getBestStrikeRateWith4sAnds6S(String filePath)throws IplLeagueException{
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> batingAnalysisCsvComparator=Comparator.comparing(BatingAnalysisCsv::getBoundaryCount)
                .thenComparing(BatingAnalysisCsv::getStrikeRate);
        return returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator);
    }
    public String getGreatAverageWithBestStrikeRate(String filePath) throws IplLeagueException {
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> batingAnalysisCsvComparator=Comparator.comparing(BatingAnalysisCsv::getAverage)
                .thenComparing(BatingAnalysisCsv::getStrikeRate);
        return returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator);
    }
    public String getMaximumRumsWithBestAverage(String filePath) throws IplLeagueException {
        loadBatingdata(filePath);
        Comparator<BatingAnalysisCsv> batingAnalysisCsvComparator=Comparator.comparing(BatingAnalysisCsv::getRuns).
                thenComparing(BatingAnalysisCsv::getAverage);
        return returnJsonFile(batingAnalysisCsvList,batingAnalysisCsvComparator);
    }

    public String getMaximumStrikeRateForBowling(String filePath) throws IplLeagueException {
        loadBowlingData(filePath);
        Comparator<BowlingAnalysisCsv> bowlingAnalysisCsvComparator=Comparator.comparing(bowlingAnalysisCsv -> bowlingAnalysisCsv.strikeRate);
        return returnJsonFile(bowlingAnalysisCsvs,bowlingAnalysisCsvComparator);
    }
     
    public String getBestEconomyRateInBowlers(String filePath) throws IplLeagueException {
        loadBowlingData(filePath);
        Comparator<BowlingAnalysisCsv> bowilingAnalysisCsvComparator=Comparator.comparing(bowlingAnalysisCsv -> bowlingAnalysisCsv.economyRate);
        return returnJsonFile(bowlingAnalysisCsvs,bowilingAnalysisCsvComparator);
    }
    public String getBestStrikeRateWith4wAnd5W(String filePath) throws IplLeagueException {
        loadBowlingData(filePath);
        Comparator<BowlingAnalysisCsv> bowlingAnalysisCsvComparator=Comparator.comparing(BowlingAnalysisCsv::getwicketCount)
                                                                .thenComparing(BowlingAnalysisCsv::getStrikeRate);
        return returnJsonFile(bowlingAnalysisCsvs,bowlingAnalysisCsvComparator);
    }
    public String getGreatBowlingAverageWithBestStrikeRate(String filePath) throws IplLeagueException {
        loadBowlingData(filePath);
        Comparator<BowlingAnalysisCsv> bowlingAnalysisCsvComparator=Comparator.comparing(BowlingAnalysisCsv::getAverage)
                .thenComparing(BowlingAnalysisCsv::getStrikeRate);
        return returnJsonFile(bowlingAnalysisCsvs,bowlingAnalysisCsvComparator);
    }

    public <E> String returnJsonFile(List<E> list,Comparator<E> comparator ){
        this.sort.sort(list,comparator);
        String sortedCensusData=new Gson().toJson(list);
        return sortedCensusData;
    }

    public static void main(String[] args) throws IplLeagueException {
        IplLeagueAnalysis iplLeagueAnalysis=new IplLeagueAnalysis();
        String result=iplLeagueAnalysis.getGreatBowlingAverageWithBestStrikeRate(".\\src\\main\\java\\com\\bridgelabz\\IplLeagueAnalysis\\bowling.csv");
        System.out.println(result);
    }
}

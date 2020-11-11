package com.bridgelabz.IplLeagueAnalysis;

public class IplLeagueException extends Exception {
    enum ExceptionType{
        NO_DATA,CSV_FILE_PROBLEM,UNABLE_TO_PARSE;
    }
    ExceptionType exceptionType;

    public IplLeagueException(String message,ExceptionType exceptionType){
        super(message);
        this.exceptionType=exceptionType;
    }
}

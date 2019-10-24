package ru.Illarionov.StockAccountingService.exceptions;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String message){
        super(message);
    }
}

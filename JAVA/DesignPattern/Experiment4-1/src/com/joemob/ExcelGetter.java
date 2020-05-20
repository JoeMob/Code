package com.joemob;

public class ExcelGetter implements DataGetter{
    public String getData(){
        ExcelAPI excelAPI = new ExcelAPI();
        excelAPI.excelSendData();
        return "Excel Data";
    }
}

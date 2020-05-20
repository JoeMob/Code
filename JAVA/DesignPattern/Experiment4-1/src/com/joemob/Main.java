package com.joemob;

public class Main {

    public static void main(String[] args) {
        // write your code here
        DataGetter dataGetter = new TXTGetter();
        ShowForm showFormP = new ShowPieForm();
        ShowForm showFormT = new ShowTableForm();
        showFormP.showDataForm(dataGetter);
        showFormT.showDataForm(dataGetter);
        dataGetter = new DBGetter();
        showFormP.showDataForm(dataGetter);
        showFormT.showDataForm(dataGetter);
        dataGetter = new ExcelGetter();
        showFormP.showDataForm(dataGetter);
        showFormT.showDataForm(dataGetter);
    }
}

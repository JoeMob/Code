package com.joemob;

public class ShowPieForm implements ShowForm {
    public void showDataForm(DataGetter dataGetter) {
        System.out.println("Showing " + dataGetter.getData() + " in Pie form.");
    }
}

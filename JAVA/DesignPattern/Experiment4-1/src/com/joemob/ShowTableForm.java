package com.joemob;

public class ShowTableForm implements ShowForm {
    @Override
    public void showDataForm(DataGetter dataGetter) {
        System.out.println("Showing " + dataGetter.getData() + " in table form.");
    }
}

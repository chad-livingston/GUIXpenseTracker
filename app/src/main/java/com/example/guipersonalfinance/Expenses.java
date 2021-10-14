package com.example.guipersonalfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Expenses extends AppCompatActivity {
    private static String filePath = "E:\\Git\\LocalGUIXpenseTracker\\expense.txt";
    //creates ArrayList of expenses in double value.
    public static ArrayList<Double> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        checkForSaveFile();

    }
    public void onClickExit(View view){
        Intent exit = new Intent(this, MainActivity.class);
        startActivity(exit);
    }
    public void refreshOnClick(View view){
        setSaveStatus();
    }
    public Boolean checkForSaveFile(){
        try {
            File mySaveFile = new File(filePath);
            if (mySaveFile.createNewFile()) {
                TextView textView = (TextView) findViewById(R.id.saveExistsText);
                textView.setText(mySaveFile.getName());
                return true;
                //System.out.println("File created: " + mySaveFile.getName());
            } else {
                //System.out.println("Save file already exists! Loading in save.");
                //reads save file and saves it into expense arraylist.
                importSave();
            }
            setSaveStatus();
        }
        catch (IOException e){
            //System.out.println("An error occurred.");
            TextView textView = (TextView) findViewById(R.id.saveExistsText);
            textView.setText("An Error occurred.");
            e.printStackTrace();
            return false;
        }
        return false;
    }
    //imports save from specified filePath then reads the strings in file and copies strings into expenses ArrayList. Calls convert string to double to convert input at the same time.
    public ArrayList importSave() {
        ArrayList<String> importList = new ArrayList<String>();
        //ArrayList importList = new ArrayList();
        try {
            Scanner in;
            File mySave = new File(filePath);
            in = new Scanner(mySave);
            while (in.hasNextLine()) {
                importList.add(in.nextLine());
            }
            expenses = convertArrayListToDoubleFromString(importList);
            setSaveStatus();

            //System.out.println("You\'ve imported an expense list with " + expenses);

        } catch (IOException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return expenses;
    }
    //Saves the ArrayList of expenses to specified filePath in string format, each entry is on a newline.  If nothing is in the expense array then write nothing.
    public static void saveFile() {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            ArrayList<String> expensee = convertArrayListToStringFromDouble(expenses);
            for (int i = 0; i < expensee.size(); i++) {
                if (expenses.size() == 0) {
                    myWriter.write("");
                }else {
                    myWriter.write(expensee.get(i)+ "\n");
                }
            }
            myWriter.close();
            //System.out.println("Successfully saved to " + filePath);
        }
        catch (IOException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //takes in a Double ArrayList and converts it to a String ArrayList.
    public static ArrayList<String> convertArrayListToStringFromDouble(ArrayList<Double> doubleList) {
        ArrayList<String> stringSave = new ArrayList<String>();
        for (Double d : doubleList) {
            stringSave.add(d.toString());
        }
        //System.out.println("Successfully converted DoubleArray to a StringArray.");
        return stringSave;
    }
    //takes in a String ArrayList and converts it to a Double ArrayList
    public static ArrayList<Double> convertArrayListToDoubleFromString(ArrayList<String> stringList){
        ArrayList<Double> doubleSave = new ArrayList<Double>();
        for (String d : stringList) {
            doubleSave.add(Double.parseDouble(d));
        }
        //System.out.println("Successfully converted StringArray to a DoubleArray.");
        return doubleSave;
    }
    public void setSaveStatus(){
        Boolean saveStatus;
        if (checkForSaveFile() == true) {
            saveStatus = true;
        } else {
            saveStatus = false;
        }
        TextView text = (TextView) findViewById(R.id.saveExistsText);
        text.setText("Save Exists: " + saveStatus.toString());
    }

}
package com.intuitiveappstudio.mealmenu.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.intuitiveappstudio.mealmenu.models.ListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Azhar on 3/17/16.
 */
public class MainMenuController {
    private static MainMenuController mainMenuController;
    private  Locale myLocale;
    public  String[] dataObjects = new String[]{"Fresh Juice",
            "Main Course",
            "Soup", "Salads", "Desserts", "Sandwich", "Soup", "Soup", "Fresh Juice", "Fresh Juice"};

    public MainMenuController(){

    }
    public static MainMenuController getSingletonInstance(){
        if(mainMenuController==null)   {
            mainMenuController=new MainMenuController();


        }
        return mainMenuController;
    }

    public  List<ListModel> getContent(){
        List<ListModel> listModels =new ArrayList<>();
        for (int i = 0; i < dataObjects.length; i++) {
            ListModel listModel = new ListModel();
            listModel.setTitle(dataObjects[i]);
            listModels.add(listModel);
        }
        return listModels;
    }
    public void changeLang(String lang,Activity context)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        context.finish();
        context.startActivity(context.getIntent());

    }

}

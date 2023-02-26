package com.example.islamicapp.pojo;

import android.content.Context;

import java.text.DecimalFormat;

public class PagesManager {

    public static int getImageByPageNumber(Context context, int pageNumber){
        DecimalFormat formatter= new DecimalFormat("000");
        String pageName= "page"+ formatter.format(pageNumber);
        int nameOfImageInDrawableFile= context.getResources()
                .getIdentifier(pageName,"drawable", context.getPackageName());
        return nameOfImageInDrawableFile;
    }
}

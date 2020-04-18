package org.game.pictionary.util;

import android.content.Context;

import org.game.pictionary.entity.AnimalInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadJSONData {
    private static String TAG = LoadJSONData.class.getSimpleName();


         // load the data and store in to list

    public static List<AnimalInfo> getAnimalList(Context context,String jsonStr){
        List<AnimalInfo> animalInfoList = new ArrayList<>();

          try {
              JSONArray animalJsonArray= new JSONArray(loadJSONFromAsset(context,jsonStr));
              // implement for loop for getting animal list

              for(int i = 0 ;  i < animalJsonArray.length(); i++){
                  // create a JSONObject for fetching animal data
                  JSONObject animalObject = animalJsonArray.getJSONObject(i);
                  int id = animalObject.getInt("id");
                  String imageUrl = animalObject.getString("imageUrl");
                  int difficultyLevel = animalObject.getInt("difficulty");
                  String answer = animalObject.getString("answer");
                  animalInfoList.add(new AnimalInfo(id,imageUrl,difficultyLevel,answer));

              }
          } catch (JSONException  e){
              LogUtils.e(TAG,"Json Error",e);
          }
          return animalInfoList;
    }

    public static String loadJSONFromAsset(Context context,String strJson){
        String json = null;

         try{
             InputStream inputStream = context.getAssets().open(strJson);
             int size = inputStream.available();
             byte[] buffer = new byte[size];
             inputStream.read(buffer);
             inputStream.close();
             json = new String(buffer,"UTF-8");
         } catch (IOException e) {
             LogUtils.e(TAG,"Json Conversion Error",e);
         }

         return json;

    }
}

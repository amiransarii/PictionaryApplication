package org.game.pictionary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.game.pictionary.entity.AnimalInfo;
import org.game.pictionary.util.AppsNetworkUtils;
import org.game.pictionary.util.LoadJSONData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPlayActivity extends BaseActivity  implements View.OnClickListener {

    private TextView textView_Result;
    private TextView textView_Timer;
    private ImageView imageView_Animal;
    private EditText editText_Answer;
    private Button button_Submit;
    private Map<Integer,Boolean> map = new HashMap<>();
    private final int interval = 1000;
    private Handler handler = new Handler();
    private int count = 30;// if user does not enter within 30 second it will end game
    private List<AnimalInfo> animalInfoList = new ArrayList<>();
    private boolean isGameStart = false;
    private int level = 3;
    private int id;
    private   String imageUrl;
    private String answer;
    private int difficultyLevel;
    private List<String> imageUrlList = new ArrayList<>();
    private ArrayList<AnimalInfo> userResult = new ArrayList<>();
    private int userDifficultyLevel = 3;

    private int countAttempt = 0;
    private int correct = 0;
    private int inCorrect = 0;


    //private Random r = new Random();


    @Override
    protected int getContentView() {
        return R.layout.activity_user_play;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        textView_Result = (TextView)findViewById(R.id.txt_user_score);
        textView_Timer  = (TextView)findViewById(R.id.txt_gametimer);
        imageView_Animal =(ImageView)findViewById(R.id.img_animal);
        editText_Answer = (EditText) findViewById(R.id.edit_guess);
        button_Submit = (Button)findViewById(R.id.btn_submit);
        button_Submit.setOnClickListener(this);
        animalInfoList = LoadJSONData.getAnimalList(this,"pictionary.json");
         loadImage();

        //loadImage(animalInfoList);

        //start handler
       // timeUpHandler();


    }

    private void loadImage() {

        if(!isGameStart){
            userDifficultyLevel = 3;
        }
        for (int i =  0; i < animalInfoList.size(); i++){
            AnimalInfo animalInfo = animalInfoList.get(i);
            id = animalInfo.getId();
            imageUrl =  animalInfo.getImageUrl();;
            answer =    animalInfo.getAnswer();
            difficultyLevel = animalInfo.getDifficulty();
            if(difficultyLevel == userDifficultyLevel && !imageUrlList.contains(imageUrl)){
                Picasso.with(this).load(imageUrl).into(imageView_Animal);
                break;
            }
        }
        isGameStart = true;
        imageUrlList.add(imageUrl);

         //r.next
      /*  System.out.println("Animal Info List");
        for (int i =  0; i < animalInfoList.size(); i++){
            AnimalInfo animalInfo = animalInfoList.get(i);
            int id = animalInfo.getId();
            String imageUrl = animalInfo.getImageUrl();
            String answer = animalInfo.getAnswer();
            int level = animalInfo.getDifficulty();
            Picasso.with(this).load(imageUrl).into(imageView_Animal);

            //System.out.println("Id " +id +" imageUrl "+ imageUrl +" answer "+answer +" level "+level);
        } */

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_submit:
                if(!AppsNetworkUtils.isConnected(this)){
                    showToast(getString(R.string.internet_error));
                }
                else{
                    checkAnswer();
                }

        }
    }



    private void timeUpHandler(){
        handler.postDelayed(new Runnable(){
            public void run(){
                textView_Timer.setText("00:" + count);
                count--;
                if(count == 0){
                    showToast("Game Over !!");
                    UserPlayActivity.this.finish();
                }
                handler.postDelayed(this, interval);
            }
        }, interval);

    }

       private void checkAnswer(){
          String userAnswer = editText_Answer.getText().toString();
          if(userAnswer.equalsIgnoreCase(answer)){
              userDifficultyLevel++;
             userResult.add(new AnimalInfo(imageUrl, answer,"CORRECT"));
             correct++;
          }
          else{
              userDifficultyLevel --;
              userResult.add(new AnimalInfo(imageUrl, answer,"INCORRECT"));
              inCorrect++;
          }
          loadImage();
           countAttempt++;
           editText_Answer.getText().clear();

           if(countAttempt == 5){
               //show result

               String scoreResult = "Score:" + correct +"/" +inCorrect;
               Intent intent = new Intent(this, UserResultActivity.class);
               Bundle bundle = new Bundle();
               bundle.putParcelableArrayList("userResultList", userResult);
               bundle.putString("userScore",scoreResult);
               intent.putExtras(bundle);
               this.startActivity(intent);

              /* for (int i = 0 ; i < userResult.size() ; i++){
                   AnimalInfo result = userResult.get(i);
                   String imageUrl = result.getImageUrl();
                   String name = result.getAnswer();
                   String answerResult = result.getAnswerResult();

                   System.out.println( "Id "+id +" Image Url "+imageUrl + "Name "+ name + " Answer Result "+answerResult);

               } */

           }
       }


}

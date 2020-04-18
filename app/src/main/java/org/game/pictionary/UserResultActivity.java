package org.game.pictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.game.pictionary.adapter.UserResultAdapter;
import org.game.pictionary.entity.AnimalInfo;

import java.util.ArrayList;
import java.util.List;

public class UserResultActivity extends BaseActivity {

    private ListView listView_UserResult;
    private TextView textView_userScore;

    private UserResultAdapter userResultAdapter;
    private String userScore = "";

    @Override
    protected int getContentView() {
        return R.layout.activity_user_result;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        listView_UserResult =(ListView)findViewById(R.id.lv_userResult);
        textView_userScore = (TextView)findViewById(R.id.txt_userScore);

         System.out.println("UserResultActivity Called "+getIntent().getExtras());
         if(getIntent().getExtras() != null){
             Bundle bundle = getIntent().getExtras();
             ArrayList<AnimalInfo> userResultList = bundle.getParcelableArrayList("userResultList");
             userScore = bundle.getString("userScore");

            userResultAdapter = new UserResultAdapter(this,R.layout.custom_user_result,userResultList);
            listView_UserResult.setAdapter(userResultAdapter);

            textView_userScore.setText(userScore);
         }

        //userResultAdapter = new UserResultAdapter(this,R.layout.custom_user_result,)
    }
}

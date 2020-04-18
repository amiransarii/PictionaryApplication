package org.game.pictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.game.pictionary.util.AppsNetworkUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button button_Start;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        button_Start = (Button)findViewById(R.id.btn_start);
        button_Start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.btn_start:
                 if(!AppsNetworkUtils.isConnected(this)){
                     showToast(getString(R.string.internet_error));
                 }
                 else{
                   Intent intent_userPlay =  new Intent(MainActivity.this,UserPlayActivity.class);
                   startActivity(intent_userPlay);
                 }

         }

    }
}

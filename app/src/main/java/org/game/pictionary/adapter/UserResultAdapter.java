package org.game.pictionary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import org.game.pictionary.R;
import org.game.pictionary.entity.AnimalInfo;

import java.util.List;

public class UserResultAdapter extends AppBaseAdapter<AnimalInfo> {

    private ViewHolder viewHolder;
    private Context context;

    public UserResultAdapter(@NonNull Context context, int resource,  List<AnimalInfo> itemList) {
        super(context, resource, itemList);
        this.context = context;
    }

    @Override
    public View getAppView(int position, View convertView, ViewGroup parent, AnimalInfo animalInfo, LayoutInflater layoutInflater) {

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.custom_user_result,parent,false);
            viewHolder.imageView_Animal = (ImageView)convertView.findViewById(R.id.image_animal);
            viewHolder.textView_AnimalName =(TextView)convertView.findViewById(R.id.txt_animalName);
            viewHolder.textView_animalResult = (TextView)convertView.findViewById(R.id.txt_resultAnswer);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder =(ViewHolder)convertView.getTag();
        }

        /**
         * set the value
         */
        Picasso.with(context).load(animalInfo.getImageUrl()).into(viewHolder.imageView_Animal);
        viewHolder.textView_AnimalName.setText(animalInfo.getAnswer());
        viewHolder.textView_animalResult.setText(animalInfo.getAnswerResult());

        if(animalInfo.getAnswerResult().equalsIgnoreCase("Correct")){
            viewHolder.textView_animalResult.setTextColor(Color.GREEN);
        }
        else{
            viewHolder.textView_animalResult.setTextColor(Color.RED);
        }


        return convertView;
    }


    //view look up
    private static class ViewHolder{
        ImageView imageView_Animal;
        TextView textView_AnimalName;
        TextView textView_animalResult;


    }
}

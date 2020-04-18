package org.game.pictionary.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class AnimalInfo implements Parcelable {
    private int id;
    private String imageUrl;
    private int difficulty;
    private String answer;
    private String answerResult;

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }

    public AnimalInfo(){
        this.id = 0;
        this.imageUrl ="";
        this.difficulty = 0;
        this.answer ="";
        this.answerResult = "";
    }


    public AnimalInfo(int id, String imageUrl, int difficulty, String answer) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
        this.answer = answer;
    }

    public AnimalInfo(String imageUrl,String answer,String answerResult){
        this.imageUrl = imageUrl;
        this.answer = answer;
        this.answerResult = answerResult;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

   public AnimalInfo (Parcel in){

        id = in.readInt();
        imageUrl = in.readString();
        difficulty = in.readInt();
        answer = in.readString();
        answerResult =in.readString();

   }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(imageUrl);
        dest.writeInt(difficulty);
        dest.writeString(answer);
        dest.writeString(answerResult);
    }

    public static final Parcelable.Creator<AnimalInfo> CREATOR = new Parcelable.Creator<AnimalInfo>()
    {
        public AnimalInfo createFromParcel(Parcel in)
        {
            return new AnimalInfo(in);
        }
        public AnimalInfo[] newArray(int size)
        {
            return new AnimalInfo[size];
        }
    };
}

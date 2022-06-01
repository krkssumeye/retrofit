package com.sumos.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private Button  btn_get_time;
private TextView txt_Time, week;
private Retrofit retrofit ;
private TimeApi timeApi;
private String baseUrl= "http://worldtimeapi.org/api/timezone/";
private Call<time_istanbul> time_istanbulCall;
private time_istanbul timeistanbul;



private void init(){
    btn_get_time = findViewById(R.id.btn_get_time);
    txt_Time = findViewById(R.id.txt_time);
    week = findViewById(R.id.week);

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btn_get_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRetrofitSettings();
            }
        });

    }
    private void setRetrofitSettings(){

    retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


   timeApi= retrofit.create(TimeApi.class);
    time_istanbulCall= timeApi.getTime();

    time_istanbulCall.enqueue(new Callback<time_istanbul>() {
        @Override
        public void onResponse(Call<time_istanbul> call, Response<time_istanbul> response) {
            if (response.isSuccessful()){
                timeistanbul =response.body();
                if (timeistanbul != null)
                txt_Time.setText(timeistanbul.getDateTime().split("T")[0]);
                week.setText(String.valueOf(timeistanbul.getDayOfWeek()));
            }
        }

        @Override
        public void onFailure(Call<time_istanbul> call, Throwable t) {
            System.out.println(t.toString());

        }
    });
    }

}
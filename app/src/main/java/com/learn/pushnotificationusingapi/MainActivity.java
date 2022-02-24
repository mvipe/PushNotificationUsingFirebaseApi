package com.learn.pushnotificationusingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.learn.pushnotificationusingapi.FirebaseApi.ApiUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.learn.pushnotificationusingapi.constants.TOPIC;

public class MainActivity extends AppCompatActivity {
    private EditText title,text;
    private Button send_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_btn=findViewById(R.id.send);
        title=findViewById(R.id.title);
        text=findViewById(R.id.text);

        //subscribe a topic
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
        /////


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title_text=title.getText().toString();
                String message_text=text.getText().toString();

                if(!(title_text.isEmpty()) || !(message_text.isEmpty())){
                    PushNotifications notification=new PushNotifications(new NotificationData(message_text,title_text),TOPIC);
                    sendNotification(notification);
                }
            }


        });


    }

    private void sendNotification(PushNotifications notification) {
        ApiUtilities.getClient().sendNotifications(notification)
        .enqueue(new Callback<PushNotifications>() {
            @Override
            public void onResponse(Call<PushNotifications> call, Response<PushNotifications> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                }
                else {

                    Log.d("tagggg",response.message());
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotifications> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
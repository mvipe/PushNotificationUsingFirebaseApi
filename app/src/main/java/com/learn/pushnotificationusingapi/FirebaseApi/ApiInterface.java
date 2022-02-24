package com.learn.pushnotificationusingapi.FirebaseApi;

import com.learn.pushnotificationusingapi.PushNotifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.learn.pushnotificationusingapi.constants.CONTENT_TYPE;
import static com.learn.pushnotificationusingapi.constants.SERVER_KEY;

public interface ApiInterface {

    @Headers({"Authorization:key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotifications> sendNotifications(@Body PushNotifications notification);

}

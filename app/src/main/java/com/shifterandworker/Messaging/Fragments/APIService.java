package com.shifterandworker.Messaging.Fragments;

import com.shifterandworker.Messaging.Notifications.MyResponse;
import com.shifterandworker.Messaging.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAkpK2PY0:APA91bE_BsYyopPhCDXTNXCEKw9CZNrVfZ-st3w5cCPhviNv9fWv4V9UV6y33VM56hyyoCvHERdg6h7bWl-YeBTd82cg-hIHY4HakyxN6RRGA2kCpiKyBtLOaEjdTQvZTb4J1uFeTwhn"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

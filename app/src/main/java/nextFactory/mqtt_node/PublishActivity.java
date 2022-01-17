package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nextFactory.mqtt_node.Data.ConnectResponse;
import nextFactory.mqtt_node.Data.PublishRequest;
import nextFactory.mqtt_node.Data.PublishResponse;
import nextFactory.mqtt_node.Network.RetrofitClient;
import nextFactory.mqtt_node.Network.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishActivity extends AppCompatActivity {

    private Button btnPublish;

    private ServiceAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        btnPublish = findViewById(R.id.btnPublish);

        service= RetrofitClient.getClient().create(ServiceAPI.class);

        Intent intent = getIntent();

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPublish();
            }
        });

    }

    private void attemptPublish() {
        String topic = "Arduino/ready";
        String message = "off";

        startPulish(new PublishRequest(topic, message));
    }

    private void startPulish(PublishRequest data) {
        service.publish(data).enqueue(new Callback<PublishResponse>() {
            @Override
            public void onResponse(Call<PublishResponse> call, Response<PublishResponse> response) {
                PublishResponse result = response.body();
            }

            @Override
            public void onFailure(Call<PublishResponse> call, Throwable t) {

            }
        });
    }

}
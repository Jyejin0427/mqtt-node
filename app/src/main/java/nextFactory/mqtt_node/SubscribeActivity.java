package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import nextFactory.mqtt_node.Data.PublishRequest;
import nextFactory.mqtt_node.Data.PublishResponse;
import nextFactory.mqtt_node.Data.SubscribeRequest;
import nextFactory.mqtt_node.Data.SubscribeResponse;
import nextFactory.mqtt_node.Network.RetrofitClient;
import nextFactory.mqtt_node.Network.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeActivity extends AppCompatActivity {

    private CheckBox optTemp, optHumi;
    private Button btnOptSearch, btnOptResearch;
    private FrameLayout layShowing;
    private TextView txtTemp, txtHumi;

    private String topicTemp, topicHumi;

    private ServiceAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        service= RetrofitClient.getClient().create(ServiceAPI.class);

        optTemp = findViewById(R.id.optTemp);
        optHumi = findViewById(R.id.optHumi);
        //optLight = findViewById(R.id.optLight);

        btnOptSearch = findViewById(R.id.btnOptSearch);
        btnOptResearch = findViewById(R.id.btnOptResearch);

        btnOptSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(optTemp.isChecked() && !optHumi.isChecked()) {
                    //온도만 보고싶다!
                    topicTemp = "Arduino/temp";
                    topicHumi = null;

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                } else if(!optTemp.isChecked() && optHumi.isChecked()) {
                    //습도만 보고싶다!
                    topicTemp = null;
                    topicHumi = "Arduino/humi";

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                } else if(optTemp.isChecked() && optHumi.isChecked()) {
                    //온습도 둘 다 보고싶다!
                    topicTemp = "Arduino/temp";
                    topicHumi = "Arduino/humi";

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                    Log.d("topicTemp", topicTemp);
                }

            }
        });

        btnOptResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                research();
            }
        });
    }

    private void research() {
        topicTemp = null;
        topicHumi = null;

        startSubscribe(new SubscribeRequest(topicTemp, topicHumi));
    }

    private void startSubscribe(SubscribeRequest data) {
        service.subscribe(data).enqueue(new Callback<SubscribeResponse>() {
            @Override
            public void onResponse(Call<SubscribeResponse> call, Response<SubscribeResponse> response) {
                SubscribeResponse result = response.body();


                //Log.d("result temp? ", result.getValTemp());
                String valTemp = result.getValTemp();
                String valHumi = result.getValHumi();



                txtTemp = findViewById(R.id.txtTemp);
                txtHumi = findViewById(R.id.txtHumi);

                txtTemp.setText(valTemp);
                txtHumi.setText(valHumi);

            }

            @Override
            public void onFailure(Call<SubscribeResponse> call, Throwable t) {

            }
        });
    }
}
package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
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

    private RadioButton tempRadio, humiRadio;
    private Button btnOptSearch;
    private FrameLayout layShowing;
    private TextView txtTemp, txtHumi, txtAny;

    private String topicTemp, topicHumi;

    private ServiceAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        txtTemp = findViewById(R.id.txtTemp);
        txtHumi = findViewById(R.id.txtHumi);

        service= RetrofitClient.getClient().create(ServiceAPI.class);

        tempRadio = findViewById(R.id.tempRadio);
        humiRadio = findViewById(R.id.humiRadio);
        //optLight = findViewById(R.id.optLight);

        tempRadio.setOnClickListener(view->{
            if(tempRadio.isChecked()){
                humiRadio.setChecked(false);
                txtHumi.setVisibility(View.GONE);

                txtTemp.setVisibility(View.VISIBLE);
            }
        });

        humiRadio.setOnClickListener(view->{
            if(humiRadio.isChecked()){
                tempRadio.setChecked(false);
                txtTemp.setVisibility(View.GONE);

                txtHumi.setVisibility(View.VISIBLE);
            }
        });

        btnOptSearch = findViewById(R.id.btnOptSearch);

        btnOptSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempRadio.isChecked() && !humiRadio.isChecked()) {
                    //온도만 보고싶다!
                    topicTemp = "Arduino/temp";
                    topicHumi = null;

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                } else if(!tempRadio.isChecked() && humiRadio.isChecked()) {
                    //습도만 보고싶다!
                    topicTemp = null;
                    topicHumi = "Arduino/humi";

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                } else if(tempRadio.isChecked() && humiRadio.isChecked()) {
                    //온습도 둘 다 보고싶다!
                    topicTemp = "Arduino/temp";
                    topicHumi = "Arduino/humi";

                    startSubscribe(new SubscribeRequest(topicTemp, topicHumi));

                    Log.d("topicTemp", topicTemp);
                }

            }
        });
    }

    private void startSubscribe(SubscribeRequest data) {
        service.subscribe(data).enqueue(new Callback<SubscribeResponse>() {
            @Override
            public void onResponse(Call<SubscribeResponse> call, Response<SubscribeResponse> response) {
                SubscribeResponse result = response.body();

                Log.d("result? ", result.getValAny());
                String valTemp = result.getValAny();

                txtAny = findViewById(R.id.txtAny);
                txtAny.setText(valTemp);
            }

            @Override
            public void onFailure(Call<SubscribeResponse> call, Throwable t) {
                Log.d("result", t.toString());
            }
        });
    }
}
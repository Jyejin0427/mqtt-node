package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nextFactory.mqtt_node.Data.Subscribe2Request;
import nextFactory.mqtt_node.Data.SubscribeResponse;
import nextFactory.mqtt_node.Network.RetrofitClient;
import nextFactory.mqtt_node.Network.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Subscribe2Activity extends AppCompatActivity {

    private Button btnOptSearch;
    private EditText edtTopic;
    private TextView txtAny;
    private String topic;
    private ServiceAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe2);

        service= RetrofitClient.getClient().create(ServiceAPI.class);

        btnOptSearch = findViewById(R.id.btnOptSearch);
        edtTopic = findViewById(R.id.edtTopic);


        btnOptSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topic = String.valueOf(edtTopic.getText());
                startSubscribe(new Subscribe2Request(topic));
            }
        });
    }

    private void startSubscribe(Subscribe2Request data) {
        service.subscribe2(data).enqueue(new Callback<SubscribeResponse>() {
            @Override
            public void onResponse(Call<SubscribeResponse> call, Response<SubscribeResponse> response) {
                SubscribeResponse result = response.body();

                //Log.d("result? ", result.getValAny());
                String valResult = result.getValAny();

                txtAny = findViewById(R.id.txtAny);
                txtAny.setText(valResult);
            }

            @Override
            public void onFailure(Call<SubscribeResponse> call, Throwable t) {
                Log.d("result", t.toString());
            }
        });
    }
}
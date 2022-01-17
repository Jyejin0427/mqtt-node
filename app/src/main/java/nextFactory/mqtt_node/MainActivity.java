package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nextFactory.mqtt_node.Data.ConnectRequest;
import nextFactory.mqtt_node.Data.ConnectResponse;
import nextFactory.mqtt_node.Network.RetrofitClient;
import nextFactory.mqtt_node.Network.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtClientID, edtServer, edtUsername, edtPassword, edtPort;
    private Button btnCancel, btnConnect;

    private ServiceAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtClientID = findViewById(R.id.edtClientID);
        edtServer = findViewById(R.id.edtServer);
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtPort = findViewById(R.id.edtPort);

        btnCancel = findViewById(R.id.btnCancel);
        btnConnect = findViewById(R.id.btnConnect);

        service= RetrofitClient.getClient().create(ServiceAPI.class);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptConnect();
            }
        });
    }

    private void attemptConnect() {
        edtClientID.setError(null);
        edtServer.setError(null);
        edtUsername.setError(null);
        edtPassword.setError(null);
        edtPort.setError(null);

        String clientID = edtClientID.getText().toString();
        String server = edtServer.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String port = edtPort.getText().toString();

        Log.d("값이 잘 들어왔니? ", clientID + server + username + password + port);


        startConnect(new ConnectRequest(clientID, server, username, password, port));
    }

    private void startConnect(ConnectRequest data) {
        service.connectMqtt(data).enqueue(new Callback<ConnectResponse>() {
            @Override
            public void onResponse(Call<ConnectResponse> call, Response<ConnectResponse> response) {
                ConnectResponse result = response.body();

                if(result.getcCode() == 1) {
                    Intent intent = new Intent(MainActivity.this, SubscribeActivity.class);
                    intent.putExtra("cCode", result.getcCode());
                    Log.d("cMessage", result.getcMessage());

                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ConnectResponse> call, Throwable t) {


            }
        });
    }
}
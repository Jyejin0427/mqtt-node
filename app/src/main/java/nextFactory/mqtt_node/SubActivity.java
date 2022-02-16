package nextFactory.mqtt_node;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    Button toPublish, toSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        toPublish = findViewById(R.id.toPublish);
        toSubscribe = findViewById(R.id.toSubscribe);

        toPublish.setOnClickListener(view->{
            Intent pub = new Intent(SubActivity.this, PublishActivity.class);
            startActivity(pub);
        });
        toSubscribe.setOnClickListener(view->{
            Intent sub = new Intent(SubActivity.this, Subscribe2Activity.class);
            startActivity(sub);
        });
    }
}
package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class PublishRequest {
    @SerializedName("topic")
    public String topic;

    @SerializedName("message")
    public String message;



    public PublishRequest (String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
}

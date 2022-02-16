package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class Subscribe2Request {
    @SerializedName("topic")
    public String topic;

    public Subscribe2Request (String topic) {
        this.topic = topic;
    }
}

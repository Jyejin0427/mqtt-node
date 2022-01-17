package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class SubscribeRequest {
    @SerializedName("topicTemp")
    public String topicTemp;

    @SerializedName("topicHumi")
    public String topicHumi;



    public SubscribeRequest (String topicTemp, String topicHumi) {
        this.topicTemp = topicTemp;
        this.topicHumi = topicHumi;
    }
}

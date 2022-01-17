package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class PublishResponse {


    @SerializedName("cMessage")
    private String cMessage;


    public String getcMessage() {
        return cMessage;
    }
}

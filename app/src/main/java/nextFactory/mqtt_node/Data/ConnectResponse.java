package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class ConnectResponse {

    @SerializedName("cCode")
    private int cCode;

    @SerializedName("cMessage")
    private String cMessage;

    public int getcCode() { return cCode; }

    public String getcMessage() {
        return cMessage;
    }
}

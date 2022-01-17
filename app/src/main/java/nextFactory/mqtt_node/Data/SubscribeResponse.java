package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class SubscribeResponse {
    @SerializedName("valTemp")
    private String valTemp;

    @SerializedName("valHumi")
    private String valHumi;

    public String getValTemp() {
        return valTemp;
    }

    public String getValHumi() {
        return valHumi;
    }
}

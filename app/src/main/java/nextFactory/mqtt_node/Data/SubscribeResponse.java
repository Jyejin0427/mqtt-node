package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class SubscribeResponse {
    @SerializedName("valAny")
    private String valAny;

    public String getValAny() {
        return valAny;
    }
}

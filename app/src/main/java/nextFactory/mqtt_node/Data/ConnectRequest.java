package nextFactory.mqtt_node.Data;

import com.google.gson.annotations.SerializedName;

public class ConnectRequest {
    //서버 연결 시 노드로 보낼 데이터들
    @SerializedName("clientID")
    public String clientID;

    @SerializedName("server")
    public String server;

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("port")
    public String port;

    public ConnectRequest (String clientID, String server, String username, String password, String port) {
        this.clientID = clientID;
        this.server = server;
        this.username = username;
        this.password = password;
        this.port = port;
    }
}

package nextFactory.mqtt_node.Network;

import nextFactory.mqtt_node.Data.ConnectRequest;
import nextFactory.mqtt_node.Data.ConnectResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceAPI {
    //서버에 어떤 식으로 요청을 보내고 응답을 받을 건지를 미리 정의

    //mqtt cloud에 연결할 때
    @POST("/app/connect")
    Call<ConnectResponse> connectMqtt(@Body ConnectRequest data);
}

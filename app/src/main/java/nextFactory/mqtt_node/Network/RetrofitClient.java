package nextFactory.mqtt_node.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://172.18.10.204:3006/"; //지금 현재 예진 IP주소 + 노드 돌릴때 포트번호
    private static Retrofit retrofit = null;


    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            //Retrofit 객체 초기화
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //요청 보낼 base url 설정
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 파싱을 위한 GsonConverterFactory를 추가한다.
                    .build();
        }
        return retrofit;
    }
}

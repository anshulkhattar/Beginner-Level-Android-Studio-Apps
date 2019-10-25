//package hundredthirtythree.boxca;
//
//import java.io.IOException;
//
//import okhttp3.HttpUrl;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * Created by chingizh on 12/12/16.
// */
//
//public class ApiCall {
//
//    //GET network request
//    public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
//
//    //POST network request
//    public static String POST(OkHttpClient client, HttpUrl url, RequestBody body) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
//}

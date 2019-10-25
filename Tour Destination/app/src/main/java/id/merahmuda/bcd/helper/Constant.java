package id.merahmuda.bcd.helper;

import id.merahmuda.bcd.rest.ApiClient;
import id.merahmuda.bcd.rest.ApiInterface;

/**
 * Created by Ujang Wahyu on 02,Oktober,2018
 */
public class Constant {

    public static final String BASE_URL_API = "http://www.json-generator.com/";
    public static final String API = "api/";
    public static final String VERSION = "json/get/";

    public static ApiInterface getAPIService(){
        return ApiClient.getService(BASE_URL_API + API + VERSION).create(ApiInterface.class);
    }
}


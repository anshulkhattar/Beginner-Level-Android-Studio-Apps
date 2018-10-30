package id.merahmuda.bcd.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.merahmuda.bcd.model.Tenant;

/**
 * Created by Ujang Wahyu on 03,Oktober,2018
 */
public class ResponseTenant
{
    @SerializedName("data")
    @Expose
    private List<Tenant> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    public List<Tenant> getData() {
        return data;
    }

    public void setData(List<Tenant> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

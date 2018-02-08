package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;


public class ServerResponse {

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

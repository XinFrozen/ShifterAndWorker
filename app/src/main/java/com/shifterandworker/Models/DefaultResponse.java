package com.shifterandworker.Models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("Success")
    private boolean success;

    @SerializedName("Message")
    private String msg;

    public DefaultResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }
}

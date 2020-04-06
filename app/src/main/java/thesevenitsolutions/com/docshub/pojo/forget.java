package thesevenitsolutions.com.docshub.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class forget {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("data")
    @Expose
    private forgotpassword data;

    public forget(boolean status, Integer code, String message, forgotpassword data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public forgotpassword getData() {
        return data;
    }

    public void setData(forgotpassword data) {
        this.data = data;
    }
}

package thesevenitsolutions.com.docshub.pojo;

public class forgotpassword {
    private String userid,otp,new_password,email;

    public forgotpassword(String email ,String otp, String new_password) {
        this.email = email;
        this.otp = otp;
        this.new_password = new_password;
    }

    public forgotpassword(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}

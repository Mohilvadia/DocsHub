package thesevenitsolutions.com.docshub;
import java.util.List;

import retrofit2.http.FormUrlEncoded;
import thesevenitsolutions.com.docshub.pojo.user_signup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface apiInterface {
        @FormUrlEncoded
        @POST("public/api/register")
        Call<user_signup> createUser(@Field("name")String name,
                                           @Field("password")String password,
                                           @Field("regid")Integer regid,
                                           @Field("mobile")String mobile,
                                           @Field("email")String email,
                                           @Field("username")String username,
                                           @Field("confirm_password")String confirm_password);
    }

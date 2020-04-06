package thesevenitsolutions.com.docshub;
import java.util.List;

  import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import thesevenitsolutions.com.docshub.pojo.forget;
import thesevenitsolutions.com.docshub.pojo.hospitallist;
import thesevenitsolutions.com.docshub.pojo.user_signup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface apiInterface {
        @FormUrlEncoded
        @POST("register")
        Call<user_signup> createUser(@Field("name")String name,
                                     @Field("username")String username,
                                     @Field("password")String password,
                                     @Field("mobile")String mobile,
                                     @Field("email")String email);


        @Headers("application:x-www-form-urlencoded,charset:UTF-8")
        @FormUrlEncoded
        @POST("login")
        Call<user_signup> loginUser(@Field("userName") String userName,
                                        @Field("password") String password);

        @FormUrlEncoded
        @POST("change-password")
        Call<user_signup> changepassword(@Field("old_password") String old_password,
                                        @Field("new_password") String new_password,
                                        @Field("confirm_password") String confirm_password);
        @GET("logout")
        Call<user_signup> logout(@Header("Authorization") String token);

        @GET("hospitals/list")
        Call<hospitallist> gethospital(@Header("Authorization") String token, @Query("City") String city);

        @FormUrlEncoded
        @POST("user/reset-password-otp")
        Call<forget> sendotp(@Header("Authorization")String token, @Field("userid") String userid );

        @FormUrlEncoded
        @POST("user/reset-password-otp")
        Call<forget> resetpassword(@Header("Authorization")String token, @Field("email") String email,@Field("otp") String otp, @Field("new_password") String new_password );

}


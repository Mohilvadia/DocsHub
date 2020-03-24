package thesevenitsolutions.com.docshub;
import android.content.Context;
import android.content.SharedPreferences;
import thesevenitsolutions.com.docshub.pojo.*;
public class prefrence {


    private static prefrence preferences;
    private SharedPreferences.Editor writer;

    private Context ctx;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";

    private static final String KEY_USER_USERNAME ="keyusername" ;//private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_MOBILE ="keyusermobilenumber" ;


    private prefrence(Context context) {
        ctx = context;
    }

    public static synchronized prefrence getInstance(Context context) {
        if (preferences == null) {
            preferences = new prefrence(context);
        }
        return preferences;
    }

    public boolean userLogin(user user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_USERNAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
       // editor.putString(KEY_USER_MOBILE, user.getMobile());
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EMAIL, null) != null)
            return true;
        return false;
    }

    public user getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new user(
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null),
                sharedPreferences.getString(KEY_USER_MOBILE, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}



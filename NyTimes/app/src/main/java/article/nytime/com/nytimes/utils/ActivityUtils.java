package article.nytime.com.nytimes.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ActivityUtils {

  private Context currentContext;

  public ActivityUtils(Context context) {
    currentContext = context;
  }

  public boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) currentContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  public static void toast(Activity a, String msg) {
    Toast.makeText(a, msg, Toast.LENGTH_SHORT).show();
  }

  public static void toastLong(Activity a, String msg) {
    Toast.makeText(a, msg, Toast.LENGTH_LONG).show();
  }
}

package article.nytime.com.nytimes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NyTimesAPIService {
  private static  String API_BASE_URL = "https://api.nytimes.com/svc/search/v2/";
  private static NyTimesAPI mInstance = null;

  private NyTimesAPIService() {};

  private static Retrofit retrofit = null;

  public static NyTimesAPI getInstance() {
    if (mInstance == null) {
      mInstance = getRetrofit().create(NyTimesAPI.class);
    }
    return mInstance;
  }

  public static Retrofit getRetrofit() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(API_BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }

}

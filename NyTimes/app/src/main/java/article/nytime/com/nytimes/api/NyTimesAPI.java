package article.nytime.com.nytimes.api;


import article.nytime.com.nytimes.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NyTimesAPI {
  String API_IMAGE_BASE_URL = "http://www.nytimes.com/";

  @GET("articlesearch.json")
  Call<ApiResponse> query(
      @Query("q") String query,
      @Query("page") Integer page,
      @Query("fq") String filteredQuery,
      @Query("begin_date") String beginDate,
      @Query("end_date") String endDate,
      @Query("sort") String sort,
      @Query("api-key") String apiKey);
}

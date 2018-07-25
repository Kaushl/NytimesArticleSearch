package article.nytime.com.nytimes.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;

import java.util.ArrayList;

import article.nytime.com.nytimes.R;
import article.nytime.com.nytimes.adapter.ArticleAdapter;
import article.nytime.com.nytimes.api.NyTimesAPIService;
import article.nytime.com.nytimes.listener.EndlessRecyclerViewScrollListener;
import article.nytime.com.nytimes.listener.RecyclerViewItemClickSupport;
import article.nytime.com.nytimes.model.ApiResponse;
import article.nytime.com.nytimes.model.Doc;
import article.nytime.com.nytimes.spacing.SpacesItem;
import article.nytime.com.nytimes.utils.ActivityUtils;
import article.nytime.com.nytimes.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  private static final String LOG_TAG = MainActivity.class.getSimpleName();
  public static final String EXTRA_ARTICLE_URL = "ArticleUrl";
  private static String API_KEY = "d31fe793adf546658bd67e2b6a7fd11a";
  // General
  Activity currentActivity;

  // RecyclerView
  ArrayList<Doc> articles;
  ArticleAdapter articleAdapter;
  ProgressDialog progressDialog;
  EndlessRecyclerViewScrollListener scrollListener;
  RecyclerView recyclerView;
  // API requests
  String queryData;
  ActivityUtils activityUtils;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    activityUtils = new ActivityUtils(this);
    currentActivity = this;
    progressDialog = setupProgressDialog();
    articles = new ArrayList<>();
    articleAdapter = new ArticleAdapter(articles, this);

    // Set up RecyclerView
    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    // With GAP_HANDLING_NONE, no reshuffling of items ot top of list (when scrolling back),
    // but possible gap (with default gap handling strategy it's the reverse)
    //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
    //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
    scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override
      public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        loadData(page);
      }
    };
    recyclerView.setAdapter(articleAdapter);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new SpacesItem(16));
    recyclerView.addOnScrollListener(scrollListener);
    RecyclerViewItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerViewItemClickSupport.OnItemClickListener() {
      @Override
      public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        // TODO: pass Doc object instead of only URL (make DOC extend Parcelable)
        Log.w(LOG_TAG, "You clicked on "+position);
        Intent intent = new Intent(currentActivity, DetailActivity.class);
        intent.putExtra(EXTRA_ARTICLE_URL, articles.get(position).getWebUrl());
        startActivity(intent);
     }
    });

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    setupSearchView(menu);
    return super.onCreateOptionsMenu(menu);
  }

  // TODO: check if using ProgressBar is better
  private ProgressDialog setupProgressDialog() {
    ProgressDialog progressDialog =  new ProgressDialog(currentActivity);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage(getString(R.string.progress_loading));
    return progressDialog;
  }


  private void setupSearchView(Menu menu) {
    MenuItem menuItem = menu.findItem(R.id.action_search);
    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
    searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      // Called when query is submitted (by pressing "Search" button on keyboard)
      // Note: empty search queries detected by the SearchView itself and ignored
      @Override
      public boolean onQueryTextSubmit(String query) {
        articleAdapter.clearArticles();
        scrollListener.resetState();
        queryData = query;
        loadData(0);
        loadData(1);
        searchView.clearFocus();
        return true;
      }
      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
  }
  private void loadData(int page) {
    String query = nullify(queryData);
    if(activityUtils.isNetworkAvailable()) {
      query(query, page, API_KEY);
    } else {
      noInternetDialog();
    }
  }

  // When there is no internet enabled and user tries to access the weather info, this dialog box appears.
  public void noInternetDialog() {
    final AlertDialog.Builder builder;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
    } else {
      builder = new AlertDialog.Builder(this);
    }
    builder.setTitle(getString(R.string.no_internet_title))
        .setMessage(getString(R.string.no_internet_content))
        .setPositiveButton(getString(R.string.no_internet_mobile_data), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(Constants.SETTINGS_ACTIVITY, Constants.DATA_USAGE_ACTIVITY));
            startActivityForResult(intent, 0);
          }
        })
        .setNegativeButton(getString(R.string.no_internet_wifi), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);
          }
        })
        .show();
  }

  // Return null for an empty string, and the original string for an non-empty string
  private String nullify(String str) {
    return (str.isEmpty()) ? null : str;
  }

  private void query(String q, Integer page, String apiKey) {
    progressDialog.show();
    Call<ApiResponse> call = NyTimesAPIService.getInstance().query(q,page, null,null,null,null, apiKey);
    call.enqueue(new Callback<ApiResponse>() {
      @Override
      public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        // API rate limits: 1000 requests per day, 1 request per second (check X-RateLimit
        // fields in HTTP response).
        if (response.code() == 429) {
          Log.v(LOG_TAG, response.code() + ": rate limit exceeded");
          return;
        }
        try {
          ArrayList<Doc> articles = (ArrayList<Doc>) response.body().getResponse().getDocs();
          if (articles.isEmpty()) {
            ActivityUtils.toastLong(currentActivity, getString(R.string.toast_no_results));
          }
          else
            articleAdapter.appendArticles(articles);
        }
        catch (NullPointerException e) {
          fail(e);
        }
        progressDialog.dismiss();
      }
      @Override
      public void onFailure(Call<ApiResponse> call, Throwable t) {
        progressDialog.dismiss();
        fail(t);
      }

      private void fail(Throwable t) {
        // TODO: check for SocketTimeoutException (if connection is slow)
        // TODO: check for UnknownHostException (if there is no Internet connection)
        ActivityUtils.toastLong(currentActivity, "Query failed: " + t.getClass().getSimpleName());
        t.printStackTrace();
      }
    });
  }
}

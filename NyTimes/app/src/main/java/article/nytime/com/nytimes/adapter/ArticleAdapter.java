package article.nytime.com.nytimes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import article.nytime.com.nytimes.R;
import article.nytime.com.nytimes.api.NyTimesAPI;
import article.nytime.com.nytimes.model.Doc;
import article.nytime.com.nytimes.model.Multimedium;
import article.nytime.com.nytimes.utils.DateFormat;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

  private ArrayList<Doc> data;
  private Context activityContext;

  public ArticleAdapter(ArrayList<Doc> data, Context context) {
    this.data = data;
    activityContext = context;
  }

  // Called when a new view for an item must be created. This method does not return the view of
  // the item, but a ViewHolder, which holds references to all the elements of the view.
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // The view for the item
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
    // Create a ViewHolder for this view and return it
    return new ViewHolder(itemView);
  }
  // Populate the elements of the passed view (represented by the ViewHolder) with the data of
  // the item at the specified position.
  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    Doc article = data.get(position);

    viewHolder.articleTitle.setText(getSafeString(article.getHeadline().getMain()));

    if (article.getPubDate() != null) {
      viewHolder.articleDate.setVisibility(View.VISIBLE);
      DateFormat date = new DateFormat(article.getPubDate());
      viewHolder.articleDate.setText(date.format1());
    } else
      viewHolder.articleDate.setVisibility(View.GONE);

    ArrayList<Multimedium> multimedia = (ArrayList<Multimedium>) article.getMultimedia();
    String thumbUrl = "";
    for (Multimedium m : multimedia) {
      if (m.getType().equals("image") && m.getSubtype().equals("thumbnail")) {
        thumbUrl = NyTimesAPI.API_IMAGE_BASE_URL + m.getUrl();
        break;
      }
    }
    if (!thumbUrl.isEmpty()) {
      // TODO: Glide seems to not cache most of these images but load them from the URL each time
      Glide.with(activityContext)
          .load(thumbUrl)
          // Save original image in cache (less fetching from server)
          .diskCacheStrategy(DiskCacheStrategy.SOURCE)
          .placeholder(R.drawable.placeholder_thumb)
          .error(R.drawable.error_thumb)
          .into(viewHolder.thumbNailImage);
    }

  }
  @Override
  public int getItemCount() {
    return data.size();
  }

  private String getSafeString(String str) {
    if (str == null)
      return "";
    else
      return str;
  }

  public void clearArticles() {
    data.clear();
    notifyItemRangeRemoved(0, getItemCount());
  }

  public void appendArticles(List<Doc> articles) {
    int oldSize = getItemCount();
    data.addAll(articles);
    notifyItemRangeInserted(oldSize, articles.size());
  }


  class ViewHolder extends RecyclerView.ViewHolder {
    ImageView thumbNailImage;
    TextView articleDate;
    TextView articleTitle;

    // Create a viewHolder for the passed view (item view)
    ViewHolder(View view) {
      super(view);
      thumbNailImage = (ImageView) view.findViewById(R.id.thumb_nail_image);
      articleDate = (TextView) view.findViewById(R.id.article_date);
      articleTitle = (TextView) view.findViewById(R.id.article_title);
    }
  }
}

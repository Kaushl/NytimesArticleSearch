package article.nytime.com.nytimes.spacing;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItem  extends RecyclerView.ItemDecoration {
  private final int mSpace;

  public SpacesItem(int space) {
    this.mSpace = space;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    outRect.left = mSpace;
    outRect.right = mSpace;
    outRect.bottom = mSpace;
    outRect.top = mSpace;
  }
}

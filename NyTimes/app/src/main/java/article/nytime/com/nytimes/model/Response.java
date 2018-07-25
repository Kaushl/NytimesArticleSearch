package article.nytime.com.nytimes.model;

import java.util.List;

public class Response {

  private Meta meta;
  private List<Doc> docs = null;

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public List<Doc> getDocs() {
    return docs;
  }

  public void setDocs(List<Doc> docs) {
    this.docs = docs;
  }

}

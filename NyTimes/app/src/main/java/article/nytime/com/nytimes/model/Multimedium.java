package article.nytime.com.nytimes.model;

public class Multimedium {

  private Integer width;
  private String url;
  private Integer height;
  private String subtype;
  private Legacy legacy;

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getSubtype() {
    return subtype;
  }

  public void setSubtype(String subtype) {
    this.subtype = subtype;
  }

  public Legacy getLegacy() {
    return legacy;
  }

  public void setLegacy(Legacy legacy) {
    this.legacy = legacy;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  private String type;
}

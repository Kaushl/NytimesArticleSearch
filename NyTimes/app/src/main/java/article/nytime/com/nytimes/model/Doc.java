package article.nytime.com.nytimes.model;

import java.util.List;

public class Doc {

  private String web_url;
  private String snippet;
  private String leadParagraph;
  private Object _abstract;
  private Object printPage;
  //private List<Object> blog = null;
  private String source;
  private List<Multimedium> multimedia = null;
  private Headline headline;
  private List<Keyword> keywords = null;
  private String pubDate;
  private String documentType;
  private String newsDesk;
  private String sectionName;
  private String subsectionName;

  private String typeOfMaterial;
  private String id;
  private Object wordCount;
  private Object slideshowCredits;

  public String getTypeOfMaterial() {
    return typeOfMaterial;
  }

  public void setTypeOfMaterial(String typeOfMaterial) {
    this.typeOfMaterial = typeOfMaterial;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Object getWordCount() {
    return wordCount;
  }

  public void setWordCount(Object wordCount) {
    this.wordCount = wordCount;
  }

  public Object getSlideshowCredits() {
    return slideshowCredits;
  }

  public void setSlideshowCredits(Object slideshowCredits) {
    this.slideshowCredits = slideshowCredits;
  }

  public String getWebUrl() {
    return web_url;
  }

  public void setWebUrl(String webUrl) {
    this.web_url = webUrl;
  }

  public String getSnippet() {
    return snippet;
  }

  public void setSnippet(String snippet) {
    this.snippet = snippet;
  }

  public String getLeadParagraph() {
    return leadParagraph;
  }

  public void setLeadParagraph(String leadParagraph) {
    this.leadParagraph = leadParagraph;
  }

  public Object get_abstract() {
    return _abstract;
  }

  public void set_abstract(Object _abstract) {
    this._abstract = _abstract;
  }

  public Object getPrintPage() {
    return printPage;
  }

  public void setPrintPage(Object printPage) {
    this.printPage = printPage;
  }

// // public List<Object> getBlog() {
//    return blog;
//  }

//  public void setBlog(List<Object> blog) {
//    this.blog = blog;
//  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public List<Multimedium> getMultimedia() {
    return multimedia;
  }

  public void setMultimedia(List<Multimedium> multimedia) {
    this.multimedia = multimedia;
  }

  public Headline getHeadline() {
    return headline;
  }

  public void setHeadline(Headline headline) {
    this.headline = headline;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<Keyword> keywords) {
    this.keywords = keywords;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public String getNewsDesk() {
    return newsDesk;
  }

  public void setNewsDesk(String newsDesk) {
    this.newsDesk = newsDesk;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public String getSubsectionName() {
    return subsectionName;
  }

  public void setSubsectionName(String subsectionName) {
    this.subsectionName = subsectionName;
  }
}

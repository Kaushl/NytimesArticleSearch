package article.nytime.com.nytimes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormat {

  private static final Pattern apiJsonPattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}).*");
  private static final Pattern apiHttpPattern = Pattern.compile("^\\d{8}$");

  // Format for parsing the YYYY-MM-DD date that was extracted with above pattern
  private static final SimpleDateFormat apiJsonDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  private static final SimpleDateFormat apiHttpDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

  // Output date formats
  private static final SimpleDateFormat outFormat = new SimpleDateFormat("d MMM. yyyy", Locale.US);

  private Date date;

  /**
   * Create a MyDate object by passing a string starting of one of the following formats:
   * - Starting with "YYYY-MM-DD" (date format of JSON response from API)
   * - "YYYYMMDD" (date format of HTTP requests for API)
   */

  public DateFormat(String str) {
    Matcher matcher;
    matcher = apiJsonPattern.matcher(str);
    if (matcher.find()) {
      try {
        date = apiJsonDateFormat.parse(matcher.group(1));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    else {
      matcher = apiHttpPattern.matcher(str);
      if (matcher.find()) {
        try {
          date = apiHttpDateFormat.parse(matcher.group());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
    }
  }


  // Format the date with output format 1
  public String format1() {
    return format(outFormat);
  }
  private String format(SimpleDateFormat f) {
    if (date != null)
      return f.format(date);
    else
      return null;
  }

  public Date getDate() {
    return date;
  }
}

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSON {
    public List<String> groceryList = new ArrayList<>();
    private Pattern nameValue = Pattern.compile("(?<=name[:]).*?(?=[;])", Pattern.CASE_INSENSITIVE);
    private Pattern priceValue = Pattern.compile("(?<=price[:]).*?(?=[;^%*!@])", Pattern.CASE_INSENSITIVE);
    private Pattern typeValue = Pattern.compile("(?<=type[:]).*?(?=[;^%*!@])", Pattern.CASE_INSENSITIVE);
    private Pattern expirationValue = Pattern.compile("(?<=expiration[:]).*?(?=[##])", Pattern.CASE_INSENSITIVE);

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }


    public List<String> splitRawData(String raw) {
        Pattern p = Pattern.compile("name.*?[##$]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(raw);
        while (matcher.find()) {
            ;
            groceryList.add(matcher.group());
        }
        return groceryList;
    }

    public String getValue(String value, String regexParams, String groceryItem) {
        String content = "";
        Pattern pattern = Pattern.compile("(?<=" + value + "[:]).*?(?=[" + regexParams + "])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(groceryItem);
        if (matcher.find()) {
            content = matcher.group();
        }
        return content;
    }

    public String getKey(String key, String groceryItem) {
        String content = "";
        Pattern pattern = Pattern.compile("(?=" + key + ").*?(?=[:])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(groceryItem);
        if (matcher.find()) {
            content = matcher.group().toLowerCase(Locale.ROOT);
        }

        return content;
    }

    public Grocery grocery(String groceryItem) {
        return null;
    }




}

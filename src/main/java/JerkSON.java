import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSON {
    public List<String> groceryList = new ArrayList<>();

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }


    public List<String> splitRawData(String raw) {
        Pattern p = Pattern.compile("name.*?[##$]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(raw);
        while(matcher.find()) {;
            groceryList.add(matcher.group());
        }
        return groceryList;
    }


    public String getName(String groceryItem) {
        String name = "";
        Pattern pattern = Pattern.compile("(?<=name[:]).*?(?=[;])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(groceryItem);
        if (matcher.find()) {
            name = matcher.group();
        }
            return name;
    }

    public String getType(String groceryItem) {
        String name = "";
        Pattern pattern = Pattern.compile("(?<=type[:]).*?(?=[;^%*!@])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(groceryItem);
        if (matcher.find()) {
            name = matcher.group();
        }
        return name;
    }


}

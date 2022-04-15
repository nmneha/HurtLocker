import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSON {
    public List<String> groceryString = new ArrayList<>();
    public List<Grocery> groceryList = new ArrayList<>();
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
            groceryString.add(matcher.group());
        }
        return groceryString;
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
            content = matcher.group();
        }

        return content;
    }

    public List<Grocery> grocery(List<String> groceryString) {
        for(String s : groceryString) {
            String name = getValue("name", ";", s);
            String price = getValue("price", ";^%*!@", s);
            Grocery g = new Grocery(name, price);
            groceryList.add(g);
        }
        return groceryList;
    }

    public void name(List<Grocery> groceryList) {
        String name = "";
        for (int i = 0; i < groceryList.size(); i++) {
            name = groceryList.get(i).getName();
            Pattern milk = Pattern.compile("milk", Pattern.CASE_INSENSITIVE);
            Matcher matcher = milk.matcher(name);
            if (matcher.find()) {
                groceryList.get(i).setName("Milk");
            }
            Pattern cookies = Pattern.compile("c..kies", Pattern.CASE_INSENSITIVE);
            Matcher matcher1 = cookies.matcher(name);
            if(matcher1.find()) {
                groceryList.get(i).setName("Cookies");
            }
            Pattern bread = Pattern.compile("bread", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = bread.matcher(name);
            if(matcher2.find()) {
                groceryList.get(i).setName("Bread");
            }
            Pattern apples = Pattern.compile("apples", Pattern.CASE_INSENSITIVE);
            Matcher matcher3 = apples.matcher(name);
            if(matcher3.find()) {
                groceryList.get(i).setName("Apples");
            }

        }
    }

    public String printFormat(List<Grocery> list) {
        int milkCount = 0;
        int mprice1 = 0;
        int mprice2 = 0;
        int cookieCount = 0;
        int cprice = 0;
        int breadCount = 0;
        int bprice = 0;
        int applesCount = 0;
        int aprice1 = 0;
        int aprice2 = 0;
        int errorCount = 0;
        for (Grocery g : list) {

            if (g.getName().equals("Milk")) {
                if (g.getPrice().equals("3.23")) {
                    mprice1++;
                    milkCount++;
                } else if (g.getPrice().equals("1.23")){
                    mprice2++;
                    milkCount++;
                } else if (g.getPrice().equals("")){
                    errorCount++;
                }
            } else if (g.getName().equals("Cookies")) {
                if (g.getPrice().equals("2.25")) {
                    cprice++;
                    cookieCount++;
                } else if (g.getPrice().equals("")){
                    errorCount++;
                }
            } else if (g.getName().equals("Bread")) {

                if (g.getPrice().equals("1.23")) {
                    breadCount++;
                    bprice++;
                } else if (g.getPrice().equals("")){
                    errorCount++;
                }
            } else if (g.getName().equals("Apples")) {
                if (g.getPrice().equals("0.25")) {
                    applesCount++;
                    aprice1++;
                } else if (g.getPrice().equals("0.23")){
                    applesCount++;
                    aprice2++;
                } else if (g.getPrice().equals("")){
                    errorCount++;
                }
            } else if (g.getName().equals("")) {
                errorCount++;
            }
        }

        return ("name:    Milk \t\t seen: " + milkCount + " times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: " + mprice1 + " times\n" +
                "-------------\t\t -------------\n" +
                "Price:   1.23\t\t seen: " + mprice2 + " time\n" +
                "\n" +
                "name:   Bread\t\t seen: " + breadCount + " times\n" +
                "=============\t\t =============\n" +
                "Price:   1.23\t\t seen: " + bprice + " times\n" +
                "-------------\t\t -------------\n" +
                "\n" +
                "name: Cookies     \t seen: " + cookieCount + " times\n" +
                "=============     \t =============\n" +
                "Price:   2.25        seen: " + cprice + " times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apples     \t seen: " + applesCount + " times\n" +
                "=============     \t =============\n" +
                "Price:   0.25     \t seen: " + aprice1 + " times\n" +
                "-------------     \t -------------\n" +
                "Price:   0.23  \t \t seen: " + aprice2 + " times\n" +
                "\n" +
                "Errors         \t \t seen: " + (errorCount) + " times");


    }
}

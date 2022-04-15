import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        JerkSON jerk = new JerkSON();
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        List<Grocery> groceryList = jerk.grocery(split);
        jerk.name(groceryList);
        System.out.println(jerk.printFormat(groceryList));

    }
}

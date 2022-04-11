import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class JerkSONTest extends TestCase {
    JerkSON jerk = new JerkSON();

    public void testReadRawDataToString() throws Exception {
        String expected = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        System.out.println(expected);
        String actual = jerk.readRawDataToString();
        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }


    public void testSplitRawData() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        for (String s: split) {
            System.out.println(s);
        }
        List<String> expected = new ArrayList<>();
        String[] list = {"naMe:Milk;price:3.23;type:Food;expiration:1/25/2016#",
        "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016#", "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016#",
        "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016#", "naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016#",
        "naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016#", "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016#",
        "naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016#", "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016#",
        "naMe:MilK;price:1.23;type:Food!expiration:4/25/2016#", "naMe:apPles;price:0.25;type:Food;expiration:1/23/2016#",
        "naMe:apPles;price:0.23;type:Food;expiration:5/02/2016#", "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016#",
        "naMe:;price:3.23;type:Food;expiration:1/04/2016#", "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016#",
        "naME:BreaD;price:1.23;type:Food@expiration:1/02/2016#", "NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016#",
        "naMe:MiLK;priCe:;type:Food;expiration:1/11/2016#", "naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016#",
        "naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016#", "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016#",
        "naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016#", "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016#",
        "naMe:MilK;priCe:;type:Food;expiration:4/25/2016#", "naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016#",
        "naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016#", "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016#",
        "naMe:;price:3.23;type:Food^expiration:1/04/2016#"};
        expected.addAll(List.of(list));
        Assert.assertEquals(expected, split);
    }

    public void testGetName() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String name = jerk.getName(s);
            System.out.println((i++) + ". " + name);
        }
        String name = jerk.getName(split.get(3));
        String nameEmpty = jerk.getName(split.get(13));
        Assert.assertEquals("MiLK", name);
        Assert.assertEquals("", nameEmpty);
    }

    public void testGetType() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String type = jerk.getType(s);
            System.out.println((i++) + ". " + type);
        }
        for (String s : split) {
            String type = jerk.getType(s);
            Assert.assertEquals("Food", type);
        }
    }


}
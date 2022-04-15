import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public void testGetValueName() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String name = jerk.getValue("name", ";", s);
            System.out.println((i++) + ". " + name);
        }

        String name = jerk.getValue("name", ";", split.get(3));
        String nameEmpty = jerk.getValue("name", ";", split.get(13));
        Assert.assertEquals("MiLK", name);
        Assert.assertEquals("", nameEmpty);
    }

    public void testGetValuePrice() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String price = jerk.getValue("price", ";^%*!@", s);
            System.out.println((i++) + ". " + price);
        }
        String price = jerk.getValue("price", ";^%*!@", split.get(3));
        String priceEmpty = jerk.getValue("price", ";^%*!@", split.get(23));
        Assert.assertEquals("3.23", price);
        Assert.assertEquals("", priceEmpty);

    }

    public void testGetValueType() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String type = jerk.getValue("type", ";^*%!@", s);
            System.out.println((i++) + ". " + type);
        }
        for (String s : split) {
            String type = jerk.getValue("type", ";^%*!@", s);
            Assert.assertEquals("Food", type);
        }
    }

    public void testGetValueExpiration() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String expiration = jerk.getValue("expiration", "##", s);
            System.out.println((i++) + ". " + expiration);
        }
        String expiration = jerk.getValue("expiration", "##", split.get(3));
        Assert.assertEquals("1/11/2016", expiration);
    }

    public void testGetKeyName() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String name = jerk.getKey("name", s);
            System.out.println((i++) + ". " + name);
        }

        String name = jerk.getKey("name", split.get(3));
        Assert.assertEquals("name", name);
    }


    public void testGetKeyPrice() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String price = jerk.getKey("price", s);
            System.out.println((i++) + ". " + price);
        }

        String price = jerk.getKey("price", split.get(3));
        Assert.assertEquals("price", price);
    }


    public void testGetKeyType() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String type = jerk.getKey("type", s);
            System.out.println((i++) + ". " + type);
        }

        String type = jerk.getKey("type", split.get(3));
        Assert.assertEquals("type", type);
    }


    public void testGetKeyExpiration() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        int i = 1;
        for (String s : split) {
            String expiration = jerk.getKey("expiration", s);
            System.out.println((i++) + ". " + expiration);
        }

        String expiration = jerk.getKey("expiration", split.get(3));
        Assert.assertEquals("expiration", expiration);
    }

    public void testGroceryList() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        List<Grocery> groceryList = jerk.grocery(split);
        for (Grocery g : groceryList) {
            String name = g.getName();
            String price = g.getPrice();
            System.out.println("Name: " + name +
                    "\nPrice: " + price +
                    "\n--------------");
        }
        Grocery actual = groceryList.get(0);
        Assert.assertEquals("Milk", actual.getName());
        Assert.assertEquals("3.23", actual.getPrice());
    }

    public void testFixNames() throws Exception {
        String raw = jerk.readRawDataToString();
        List<String> split = jerk.splitRawData(raw);
        List<Grocery> groceryList = jerk.grocery(split);
        jerk.name(groceryList);
        for (Grocery g : groceryList) {
            String name = g.getName();
            String price = g.getPrice();
            System.out.println("Name: " + name +
                    "\nPrice: " + price +
                    "\n--------------");
        }
        Grocery actual = groceryList.get(1);
        Assert.assertEquals("Bread", actual.getName());
        Assert.assertEquals("1.23", actual.getPrice());
    }

}
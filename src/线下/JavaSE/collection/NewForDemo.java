package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class NewForDemo {
    public static void main(String[] args) {
        String []a=new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        for (String i:a
             ) {
            System.out.println(i);

        }

        Collection<String> c = new ArrayList<>(Arrays.asList(a));
        for (Object o : c)
              {
            System.out.println(o);

        }

        Iterator<String> i = c.iterator();
        while (i.hasNext()){
            String s = i.next();
            System.out.println(s);
        }

    }
}

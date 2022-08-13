package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 99);
        map.put("数学", 98);
        map.put("英语", 100);
        map.put("物理", 97);
        map.put("化学", 100);

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println("key: " + key);
        }

        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println("value: " + value);
        }

//        values.forEach(System.out::println);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry e : entrySet) {
            System.out.println("key: " + e.getKey() + "  value: " + e.getValue());
        }

        map.forEach((k,y)-> System.out.println(k+":"+y));

    }
}

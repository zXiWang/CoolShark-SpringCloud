package map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo1 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 99);
        map.put("数学", 98);
        map.put("英语", 100);
        map.put("物理", 97);
        map.put("化学", 100);
        System.out.println(map);

        Integer value = map.put("语文", 98);
        System.out.println(value);
        System.out.println(map);

        value = map.get("数学");
        System.out.println(value);
        value = map.get("英语");
        System.out.println(value);

        map.remove("英语");
        System.out.println(map);

        System.out.println(map.size());

        System.out.println("是否存在key:"+map.containsKey("语文"));
        System.out.println("是否存在value:"+map.containsValue(99));
    }
}

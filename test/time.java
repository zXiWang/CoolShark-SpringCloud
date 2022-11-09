import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    public static void main(String[] args) throws ParseException {

        String birth = "2022-11-07 00:00:00";
        //创建日期格式化对象
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = time.parse(birth);
        //调用Date类的getTime()方法获取指定时间的毫秒值
        System.out.println(date.getTime());  //1667750400000


    }
}

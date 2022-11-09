package com.xiwang.csmall.cart.webapi.kafka;

import com.google.gson.Gson;
import com.xiwang.csmall.commons.pojo.cart.model.Cart;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    // 直接从Spring容器中获取kafkaTemplate对象
    // 这个对象会在SpringBoot启动时,根据配置信息自动生成
    // KafkaTemplate<[话题类型],[消息类型]>
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    int i=1;
    // 实现每隔15秒(15000毫秒)发送一次消息到Kafka指定话题
    @Scheduled(fixedRate = 1000)
    public void sendMessage(){
        Cart cart=new Cart();
        cart.setId(i++);
        cart.setCommodityCode("PC100");
        cart.setUserId("UU100");
        cart.setPrice(RandomUtils.nextInt(90)+10);
        cart.setCount(RandomUtils.nextInt(10)+1);
        // 将cart对象转换成json格式字符串发送
        // {"id":"1","userId":"UU100","price":"68",....}
        // 利用Google提供的gson工具进行转换
        Gson gson=new Gson();
        String json=gson.toJson(cart);
        System.out.println("要发送的json字符串为:"+json);
        // 执行发送
        kafkaTemplate.send("myCart",json);
    }

}

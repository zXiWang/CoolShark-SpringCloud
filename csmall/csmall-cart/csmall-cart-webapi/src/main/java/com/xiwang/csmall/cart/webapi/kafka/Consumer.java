package com.xiwang.csmall.cart.webapi.kafka;

import com.google.gson.Gson;
import com.xiwang.csmall.commons.pojo.cart.model.Cart;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    // 监听机制
    @KafkaListener(topics = "myCart")
    // 上面是监听器注解,指定了myCart这个话题名称
    // 当kafka的myCart话题出现消息时,监听器会自动调用下面的方法
    // 方法的参数和返回值是指定的,不能修改
    public void received(ConsumerRecord<String, String> record) {
        // 方法参数类型必须是ConsumerRecord
        // 泛型<[话题类型],[消息内容的类型]>
        // 这个record就是消息发送者发来的消息,由监听器自动赋值
        String json = record.value();
        Gson gson = new Gson();
        Cart cart = gson.fromJson(json, Cart.class);
        System.out.println("消息的接收者获得:" + cart);
    }
}

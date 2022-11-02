package cn.tedu.mall.order.mapper;

import cn.tedu.mall.pojo.order.model.OmsOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsOrderItemMapper {

    // 新增订单项(Order_item)的方法
    // 一个订单可能包含多件商品,如果每件商品都单独新增到数据库,会造成连库次数多效率降低
    // 采用一次连库就增加多条订单项的方式,提升效率
    int insertOrderItemList(List<OmsOrderItem> omsOrderItems);

}

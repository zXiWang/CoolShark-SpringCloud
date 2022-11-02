package cn.tedu.mall.order.mapper;

import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.model.OmsOrder;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsCartMapper {

    int insertOrder(OmsOrder omsOrder);

    OmsCart selectExistsCart(@Param("userId") Long userId,
                             @Param("skuId") Long skuId);

    int saveCart(OmsCart omsCart);

    int updateQuantityById(OmsCart omsCart);

    List<CartStandardVO> selectCartsByUserId(@Param("userId") Long userId);

    int deleteCartsByIds(Long[] ids);

    int deleteCartsByUserId(Long userId);

    int deleteCartByUserIdAndSkuId(OmsCart omsCart);
}

package cn.tedu.mall.order.mapper;

import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsCartMapper {

    OmsCart selectExistsCart(@Param("userId") Long userId,
                             @Param("skuId") Long skuId);

    int saveCart(OmsCart omsCart);

    int updateQuantityById(OmsCart omsCart);

    List<CartStandardVO> selectCartsByUserId(@Param("userId") Long userId);
}

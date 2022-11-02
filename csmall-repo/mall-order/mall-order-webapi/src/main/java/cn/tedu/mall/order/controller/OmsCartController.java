package cn.tedu.mall.order.controller;


import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.order.service.IOmsCartService;
import cn.tedu.mall.order.utils.WebConsts;
import cn.tedu.mall.pojo.order.dto.CartAddDTO;
import cn.tedu.mall.pojo.order.dto.CartUpdateDTO;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "购物车管理模块")
@RequestMapping("/oms/cart")
public class OmsCartController {

    @Autowired
    private IOmsCartService omsCartService;

    @PostMapping("/add")
    @ApiOperation("新增购物车信息")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public JsonResult addCart(@Validated CartAddDTO cartAddDTO) {
        omsCartService.addCart(cartAddDTO);
        return JsonResult.ok("新增sku到购物车完成!");
    }

    @PostMapping("/delete")
    @ApiOperation("根据id数组删除购物车中的sku信息")
    @ApiImplicitParam(value = "包含要删除的id的数组", name = "ids", required = true, dataType = "array")
//    @PreAuthorize("hasAuthority('ROLE_user')")
    @PreAuthorize("hasRole('user')")
    public JsonResult delete(Long[] ids) {

        omsCartService.removeCart(ids);
        return JsonResult.ok("删除购物车信息完成!");
    }

    @PostMapping("/delete/all")
    @ApiOperation("删除购物车中所有的sku信息")
    @PreAuthorize("hasRole('user')")
    public JsonResult deleteAll() {
        omsCartService.removeAllCarts();
        return JsonResult.ok("删除所有购物车信息完成!");
    }

    @PostMapping("/update/quantity")
    @ApiOperation("修改购物车中的sku数据")
    @PreAuthorize("hasRole('user')")
    public JsonResult updateQuantity(@Validated CartUpdateDTO cartUpdateDTO) {
        omsCartService.updateQuantity(cartUpdateDTO);
        return JsonResult.ok("修改完成!");
    }

    @GetMapping("/list")
    @ApiOperation("按userId分页查询购物车sku信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "page", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "10")
    })
    @PreAuthorize("hasAuthority('ROLE_user')")
    public JsonResult<JsonPage<CartStandardVO>> listCartsByPage(
            @RequestParam(required = false, defaultValue = WebConsts.DEFAULT_PAGE)
                    Integer page,
            @RequestParam(required = false, defaultValue = WebConsts.DEFAULT_PAGE_SIZE)
                    Integer pageSize) {
        JsonPage<CartStandardVO> pageList = omsCartService.listCarts(page, pageSize);
        return JsonResult.ok(pageList);
    }
}

package cn.tedu.mall.order.controller;


import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.order.service.IOmsCartService;
import cn.tedu.mall.order.utils.WebConsts;
import cn.tedu.mall.pojo.order.dto.CartAddDTO;
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

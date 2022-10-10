package com.xiwang.csmall.passport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.passport.pojo.entity.Admin;
import com.xiwang.csmall.passport.pojo.vo.AdminListItemVO;
import com.xiwang.csmall.passport.pojo.vo.AdminNormalVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    int insert(Admin admin);

    int update(Admin admin);

    int countByUsername(String username);

    int countByPhone(String phone);

    int countByEmail(String email);


    List<AdminListItemVO> list();

    AdminNormalVO getNormalById(Long id);

    int deleteByIds(Long[] adminIdByRoleId);
}

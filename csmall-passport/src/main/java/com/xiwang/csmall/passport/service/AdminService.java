package com.xiwang.csmall.passport.service;

import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import com.xiwang.csmall.passport.pojo.vo.AdminListItemVO;
import com.xiwang.csmall.passport.pojo.vo.AdminNormalVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员(Admin)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-29 11:32:46
 */
public interface AdminService {
    List<AdminListItemVO> list();

    AdminNormalVO getNormalById(Long id);

    @Transactional
    void addNew(AdminAddNewDTO adminAddNewDTO);

    @Transactional
    void delete(Long id);

    @Transactional
    void update(AdminAddNewDTO adminAddNewDTO);

    @Transactional
    void setEnabled(Long id);

    @Transactional
    void setDisabled(Long id);
}

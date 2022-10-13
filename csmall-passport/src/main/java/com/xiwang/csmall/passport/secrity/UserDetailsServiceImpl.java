package com.xiwang.csmall.passport.secrity;

import com.xiwang.csmall.passport.mapper.AdminMapper;
import com.xiwang.csmall.passport.pojo.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);
        AdminLoginInfoVO loginInfo = adminMapper.getLoginInfoByUsername(s);
        log.debug("从数据库查询与用户名【{}】匹配的管理员信息：{}", s, loginInfo);
        if (loginInfo == null) {
            log.debug("此用户名【{}】不存在，即将抛出异常",s);
            String message = "登录失败，用户名不存在！";
            throw new BadCredentialsException(message);
        }

        UserDetails userDetails = User.builder()
                .username(loginInfo.getUsername())
                .password(loginInfo.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(loginInfo.getEnable() == 0)
                .authorities("这是一个山寨的权限标识") // 权限，注意，此方法的参数不可以为null，在不处理权限之前，可以写一个随意的字符串值
                .build();
        log.debug("即将向Spring Security返回UserDetails对象：{}", userDetails);
        return userDetails;
    }

}

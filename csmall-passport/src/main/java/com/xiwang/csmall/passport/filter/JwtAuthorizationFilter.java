package com.xiwang.csmall.passport.filter;

import com.xiwang.csmall.passport.secrity.LoginPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    public static final int JWT_MIN_LENGTH = 100;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 尝试获取客户端提交请求时可能携带的JWT
        String jwt = request.getHeader("Authorization");
        log.debug("接收到JWT数据：{}", jwt);

        // 判断是否获取到有效的JWT
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            // 直接放行
            log.debug("未获取到有效的JWT数据，将直接放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 尝试解析JWT，从中获取用户的相关数据，例如id、username等
        log.debug("将尝试解析JWT……");
        String secretKey = "kns439a}fdLK34jsmfd{MF5-8DJSsLKhJNFDSjn";
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        log.debug("从JWT中解析得到数据：id={}", id);
        log.debug("从JWT中解析得到数据：username={}", username);

        // 将根据从JWT中解析得到的数据来创建认证信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("这是一个山寨的权限标识");
        authorities.add(authority);
        LoginPrincipal loginPrincipal = new LoginPrincipal();
        loginPrincipal.setId(id);
        loginPrincipal.setUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPrincipal, null, authorities);

        // 将认证信息存储到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 放行
        filterChain.doFilter(request, response);
    }

}

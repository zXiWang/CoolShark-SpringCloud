package com.xiwang.csmall.product.config;

import com.xiwang.csmall.product.interceptor.mybatis.InsertUpdateTimeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
@MapperScan("com.xiwang.csmall.product.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {
        log.debug("创建配置类对象：MybatisConfiguration");

    }

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct // 使得此方法在调用了构造方法、完成了属性注入之后自动执行
    public void addInterceptor() {
        InsertUpdateTimeInterceptor interceptor = new InsertUpdateTimeInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}

package com.mw.dmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 大数据管理平台
 * 包管理
 * constants    常量
 * controller   控制器/Restful api入口
 * dao		    持久层
 * filter	    过滤器/拦截器
 * helper	    工具类
 * service	    业务层接口
 * service.impl 业务实现层
 * @author zang
 * @version 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@ServletComponentScan
@EnableCaching
@MapperScan("com.mw.dmp.dao")
public class DmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmpApplication.class, args);
	}
}

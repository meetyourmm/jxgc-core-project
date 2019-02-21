package com.micolor.commoncore.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.interceptor
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
@Configuration
public class RegistryInterceptor extends WebMvcConfigurerAdapter {
    @Bean
    APIInterceptor apiInterceptor() {
        return new APIInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/interface/**").
                excludePathPatterns("/interface/logintoken").
                excludePathPatterns("/interface/tokencheck").
                excludePathPatterns("/interface/business/register/**").
                excludePathPatterns("/interface/business/ads/**").
                excludePathPatterns("/interface/business/supply/**").
                excludePathPatterns("/interface/business/supplyNum/**").
                excludePathPatterns("/interface/business/client/businessMorePic").
                excludePathPatterns("/interface/system/chinacity/**").
                excludePathPatterns("/interface/business/ads/getByAdvPic").
                excludePathPatterns("/interface/business/client/businessMorePic").
                excludePathPatterns("/interface/business/client/delBusinessPic").
                excludePathPatterns("/interface/business/client/getBusinessPic").
                excludePathPatterns("/interface/communication/**");
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/web/**").
                excludePathPatterns("/web/logintoken").
                excludePathPatterns("/web/tokencheck").
                excludePathPatterns("/web/business/register/**").
                excludePathPatterns("/web/business/ads/**").//广告
                excludePathPatterns("/web/business/supplyNum/**").
                excludePathPatterns("/web/upload/delBusinessPic").
                excludePathPatterns("/web/upload/savebusinessmorepic").
                excludePathPatterns("/web/suppygoods/getsupplygoodslist").//货源
                excludePathPatterns("/web/dictionary/getAllDictionaryValues").//字典
                excludePathPatterns("/web/chinacity/**"). //城市
                excludePathPatterns("/web/news/**"). //新闻
                excludePathPatterns("/web/communication/**");
        super.addInterceptors(registry);
    }
}
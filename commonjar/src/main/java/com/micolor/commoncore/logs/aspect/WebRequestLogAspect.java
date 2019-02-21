package com.micolor.commoncore.logs.aspect;

import com.micolor.commoncore.logs.domain.OperatorLogEntity;
import com.micolor.commoncore.logs.service.OptLogService;
import com.micolor.commoncore.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.aspect
 *
 * @Author: Evangoe
 * @Description: 用于对Controller中所有的进行日志记录的切面
 * @Date:11/05/17
 * @Modified:
 */
@Aspect
@Component
public class WebRequestLogAspect {
    private static Logger logger = LoggerFactory.getLogger(WebRequestLogAspect.class);

    private ThreadLocal<OperatorLogEntity> tlocal = new ThreadLocal<OperatorLogEntity>();

    @Autowired
    private OptLogService optLogService;

    /*
     * 切面定义
     */
    @Pointcut("(execution(* com.micolor..*Controller.*(..))) " +    //com.micolor下面所有子目录以Controller结尾
            "&& !execution(* com.micolor.controller.admin.index..*.*(..)) " + //排除com.micolor.admin.controller.index下面的Controller
            "&& !execution(* com.micolor.commoncore.exception.controller..*.*(..))") //排除com.micolor.commoncore.exception.controller下面的Controller
    public void webRequestLog() {}



    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            long beginTime = System.currentTimeMillis();
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String beanName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String uri = request.getRequestURI();
            String remoteAddr = StringUtil.getIpAddr(request);
            String sessionId = request.getSession().getId();
            Subject currentUser = SecurityUtils.getSubject();
            String user = String.valueOf(currentUser.getPrincipal());
            String method = request.getMethod();
            String params = "";
            if ("POST".equals(method)) {
                Object[] paramsArray = joinPoint.getArgs();
                params = StringUtil.argsArrayToString(paramsArray);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                Map<?,?> paramsMapByParameterMap = request.getParameterMap();
                params = paramsMap.toString() + StringUtil.transMapToString(paramsMapByParameterMap);
            }
            OperatorLogEntity optLog = new OperatorLogEntity();
            optLog.setBeanName(beanName);
            optLog.setCurUser(user);
            optLog.setMethodName(methodName);
            optLog.setParams(StringUtil.isEmpty4Str(params.toString()));
            optLog.setRemoteAddr(remoteAddr);
            optLog.setSessionId(sessionId);
            optLog.setUri(uri);
            optLog.setRequestTime(beginTime);
            tlocal.set(optLog);
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @SuppressWarnings("unchecked")
    @AfterReturning(value="webRequestLog()",argNames = "result",returning = "result")
    public void doAfterReturning(Object result) {
        try {
            // 处理完请求，返回内容
            OperatorLogEntity optLog = tlocal.get();
            optLog.setResult(StringUtil.isEmpty4Str(result));
            long beginTime = optLog.getRequestTime();
            long requestTime = (System.currentTimeMillis() - beginTime) / 1000;
            optLog.setRequestTime(requestTime);
            optLog.setOpTime(new Date());
            optLogService.saveLog(optLog);
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
    }

    @AfterThrowing(value="webRequestLog()",throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex){
        String strLog ="afterThrowing:log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
        logger.error(strLog+"["+ex+"]");
    }
}

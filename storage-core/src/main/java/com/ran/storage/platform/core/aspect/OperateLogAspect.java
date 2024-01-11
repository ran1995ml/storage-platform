package com.ran.storage.platform.core.aspect;

import com.ran.storage.platform.common.annotation.OperateLog;
import com.ran.storage.platform.common.bean.po.common.OperateLogPO;
import com.ran.storage.platform.persist.mysql.common.OperateLogDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * OperateLogAspect
 *
 * @author rwei
 * @since 2024/1/10 13:55
 */
@Aspect
@Component
public class OperateLogAspect {
    @Autowired
    private OperateLogDAO operateLogDAO;

    @Pointcut("@annotation(com.ran.storage.platform.common.annotation.OperateLog)")
    public void operateLogPointCut() {}

    @AfterReturning(value = "operateLogPointCut()", returning = "keys")
    public void saveOperateLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperateLogPO operateLogPO = new OperateLogPO();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperateLog operateLog = method.getAnnotation(OperateLog.class);

        if (Objects.nonNull(operateLog)) {
            operateLogPO.setOperateModule(operateLog.module());
            operateLogPO.setOperateType(operateLog.type());
            operateLogPO.setDetail(operateLog.desc());
            operateLogPO.setOperateUsername("test");
        }

        operateLogDAO.addAndSetId(operateLogPO);
    }


}

package com.ran.storage.platform.common.utils;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * ConvertUtil
 *
 * @author rwei
 * @since 2023/8/14 22:03
 */
public class ConvertUtil {
    private ConvertUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    public static <T> List<T> list2List(List<? extends Object> srcList, Class<T> tgtClass) {
        return list2List(srcList, tgtClass, (t) -> {});
    }

    public static <T> List<T> list2List(List<? extends Object> srcList, Class<T> tgtClass, Consumer<T> consumer) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Lists.newArrayList();
        }

        List<T> result = Lists.newArrayList();

        for (Object object : srcList) {
            T t = obj2Obj(object, tgtClass, consumer);
            if (t != null) {
                result.add(t);
            }
        }

        return result;
    }

    public static <T> T obj2Obj(final Object srcObj, Class<T> tgtClass) {
        return obj2Obj(srcObj, tgtClass, (t) -> {});
    }

    public static <T> T obj2Obj(final Object srcObj, Class<T> tgtClass, Consumer<T> consumer) {
        if (srcObj == null) {
            return null;
        }

        T tgt = null;
        try {
            tgt = tgtClass.newInstance();
            BeanUtils.copyProperties(srcObj, tgt);
            consumer.accept(tgt);
        } catch (Exception e) {
            logger.error("Error converting {} to {}", srcObj.getClass().getName(), tgtClass.getName(), e);
        }

        return tgt;
    }
}

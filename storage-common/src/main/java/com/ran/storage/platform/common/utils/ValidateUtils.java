package com.ran.storage.platform.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * ValidateUtils
 *
 * @author rwei
 * @since 2023/8/23 17:11
 */
public class ValidateUtils {
    /**
     * whether object is null
     * @param object input object
     * @return result
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * whether string is null or empty
     * @param str input string
     * @return result
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
}

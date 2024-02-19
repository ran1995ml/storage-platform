package com.ran.storage.platform.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * DateUtils
 *
 * @author rwei
 * @since 2024/2/18 16:02
 */
public class DateUtils {
    public static final String PATTERN_1 = "yyyy-MM-ddTHH:mm:ssZ";

    public static Date offsetDate2Date(OffsetDateTime offsetDateTime) {
        return Date.from(offsetDateTime.toInstant());
    }

    public static Date str2Date(String str, String format) {
        if (str == null) {
            return null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                return simpleDateFormat.parse(str);
            } catch (ParseException e) {
                return null;
            }
        }
    }
}

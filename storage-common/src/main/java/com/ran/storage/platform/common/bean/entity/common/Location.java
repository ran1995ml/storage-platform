package com.ran.storage.platform.common.bean.entity.common;

import lombok.Data;

/**
 * Location
 *
 * @author rwei
 * @since 2023/11/27 21:54
 */
@Data
public class Location {
    private String host;

    private int port;

    private int tlsPort;
}

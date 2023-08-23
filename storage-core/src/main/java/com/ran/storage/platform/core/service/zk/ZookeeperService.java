package com.ran.storage.platform.core.service.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * ZookeeperService
 *
 * @author rwei
 * @since 2023/8/2 17:53
 */
public class ZookeeperService {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("node1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(15 * 1000)
                .sessionTimeoutMs(60 * 1000)
                .build();
        client.start();
        List<String> list = client.getChildren().forPath("/");
        System.out.println(list.toString());
    }
}

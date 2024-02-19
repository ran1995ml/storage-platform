package com.ran.storage.platform.common.component;

import com.ran.storage.platform.common.enums.DruidNodeTypeEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.kubernetes.client.PodLogs;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Streams;
import okhttp3.Call;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KubernetesTool
 *
 * @author rwei
 * @since 2024/2/4 10:33
 */
@Component
public class KubernetesTool {
    private static final Integer LIMIT = Integer.MAX_VALUE;

    private static final Integer TIME_OUT_SECONDS = 2;

    private static final Boolean WATCH = false;

    private static final Boolean ALLOW_WATCH_BOOKMARKS = false;

    public static void main(String[] args) throws IOException, ApiException {
        KubernetesTool kubernetesTool = new KubernetesTool();
        String config = "{\"apiVersion\":\"v1\",\"kind\":\"Config\",\"clusters\":[{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"cluster\":{\"server\":\"https://rancher.prod.conviva.com/k8s/clusters/c-m2bbt\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp212-10d-iad4-prod-conviva-com\",\"cluster\":{\"server\":\"https://10.7.8.82:6443\",\"certificate-authority-data\":\"LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN3akNDQWFxZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFTTVJBd0RnWURWUVFERXdkcmRXSmwKTFdOaE1CNFhEVEl4TURRd01qRTRNekEwT0ZvWERUTXhNRE16TVRFNE16QTBPRm93RWpFUU1BNEdBMVVFQXhNSAphM1ZpWlMxallUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0NnZ0VCQUwyWlp6OE1uMmNUCnJlTGcxa2NVZDZtbUlrdDZONEdxM0ZXRmUyUHd4TzRuY3JHYWpWZ1EzbHVDR0p6ZmhxNjJyMFZiU0MrQ1J1NHUKQU82RWVKMWllQnlJa0xGVjBQMUpSSS9pRE1NcGZRc3ptY0ZqM0t6NTNOektjRDR6REhHalExODJVUVRiaitsUwo2c241dWFrdGZ0MEZJYURrbUYxVDZTbFdteXlTR2tyVXQzOVdwUmk4K2trZnNvWjRBK2VHekQyTms4eDh4N0ZVCkFLdW1mSXJ4MkphdFFQaXVVVk1ZTFNCem5VdVJ1bU95bHI4NzlqN052WWNDcXg1dmY5YjdKeVJUVzhnOVZoeSsKc0M2WEhDbFM3MnpHSTBjV01CWFpPNXY5VWwxKzlUdGxmU2RFYnV3dkE0ZFRaYXQ5SXI1eWNJaFlhd0xiMzExSwpkQ2pXZjR1LzE2VUNBd0VBQWFNak1DRXdEZ1lEVlIwUEFRSC9CQVFEQWdLa01BOEdBMVVkRXdFQi93UUZNQU1CCkFmOHdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRU5CQlpuU0dSeiszRG1jbWdFeE9DYll5blM4TDB0QkUwTmYKdWgzTk5SSWp3bW4vUTZxZDJ1RHU4K2pIMUhGbTNPUW9CdzZZZXYwUFZuY3U2MlJ0MStISmt6Z0pZNktvZ3grZQo4ZS9JdUsrYlZSR0ozdDUrZUVJOEVCb1BDbzdxRWtEVENoOEwzbTZMU2dqT3hHWkhLcERUNDdFQUpIRUF3U1hqCjQ1NTJtVE5tRmVTWHdjVTJlVWpFMUxPVmR0N3lwTGVXMnFzL0l2d0grckZHREorRitjeGxlM1ROckIwL2ZPZEwKc0NsMzcrbzMrL3pqSk5DZytQWFVrNEUyY3VCN0NHcHhhRU9keUNneVpKTzNuVE5yc3VnMjNhWXdMWHZnTXdaMwp3c09IbTlIbkMrKzYwTFh2a2lOL00wUS95eHA3SUk4WW5ubzM4Ly9oWWlYV1o2d0xQYlk9Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp211-10d-iad4-prod-conviva-com\",\"cluster\":{\"server\":\"https://10.7.8.46:6443\",\"certificate-authority-data\":\"LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN3akNDQWFxZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFTTVJBd0RnWURWUVFERXdkcmRXSmwKTFdOaE1CNFhEVEl4TURRd01qRTRNekEwT0ZvWERUTXhNRE16TVRFNE16QTBPRm93RWpFUU1BNEdBMVVFQXhNSAphM1ZpWlMxallUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0NnZ0VCQUwyWlp6OE1uMmNUCnJlTGcxa2NVZDZtbUlrdDZONEdxM0ZXRmUyUHd4TzRuY3JHYWpWZ1EzbHVDR0p6ZmhxNjJyMFZiU0MrQ1J1NHUKQU82RWVKMWllQnlJa0xGVjBQMUpSSS9pRE1NcGZRc3ptY0ZqM0t6NTNOektjRDR6REhHalExODJVUVRiaitsUwo2c241dWFrdGZ0MEZJYURrbUYxVDZTbFdteXlTR2tyVXQzOVdwUmk4K2trZnNvWjRBK2VHekQyTms4eDh4N0ZVCkFLdW1mSXJ4MkphdFFQaXVVVk1ZTFNCem5VdVJ1bU95bHI4NzlqN052WWNDcXg1dmY5YjdKeVJUVzhnOVZoeSsKc0M2WEhDbFM3MnpHSTBjV01CWFpPNXY5VWwxKzlUdGxmU2RFYnV3dkE0ZFRaYXQ5SXI1eWNJaFlhd0xiMzExSwpkQ2pXZjR1LzE2VUNBd0VBQWFNak1DRXdEZ1lEVlIwUEFRSC9CQVFEQWdLa01BOEdBMVVkRXdFQi93UUZNQU1CCkFmOHdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRU5CQlpuU0dSeiszRG1jbWdFeE9DYll5blM4TDB0QkUwTmYKdWgzTk5SSWp3bW4vUTZxZDJ1RHU4K2pIMUhGbTNPUW9CdzZZZXYwUFZuY3U2MlJ0MStISmt6Z0pZNktvZ3grZQo4ZS9JdUsrYlZSR0ozdDUrZUVJOEVCb1BDbzdxRWtEVENoOEwzbTZMU2dqT3hHWkhLcERUNDdFQUpIRUF3U1hqCjQ1NTJtVE5tRmVTWHdjVTJlVWpFMUxPVmR0N3lwTGVXMnFzL0l2d0grckZHREorRitjeGxlM1ROckIwL2ZPZEwKc0NsMzcrbzMrL3pqSk5DZytQWFVrNEUyY3VCN0NHcHhhRU9keUNneVpKTzNuVE5yc3VnMjNhWXdMWHZnTXdaMwp3c09IbTlIbkMrKzYwTFh2a2lOL00wUS95eHA3SUk4WW5ubzM4Ly9oWWlYV1o2d0xQYlk9Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp213-10d-iad4-prod-conviva-com\",\"cluster\":{\"server\":\"https://10.7.8.122:6443\",\"certificate-authority-data\":\"LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN3akNDQWFxZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFTTVJBd0RnWURWUVFERXdkcmRXSmwKTFdOaE1CNFhEVEl4TURRd01qRTRNekEwT0ZvWERUTXhNRE16TVRFNE16QTBPRm93RWpFUU1BNEdBMVVFQXhNSAphM1ZpWlMxallUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0NnZ0VCQUwyWlp6OE1uMmNUCnJlTGcxa2NVZDZtbUlrdDZONEdxM0ZXRmUyUHd4TzRuY3JHYWpWZ1EzbHVDR0p6ZmhxNjJyMFZiU0MrQ1J1NHUKQU82RWVKMWllQnlJa0xGVjBQMUpSSS9pRE1NcGZRc3ptY0ZqM0t6NTNOektjRDR6REhHalExODJVUVRiaitsUwo2c241dWFrdGZ0MEZJYURrbUYxVDZTbFdteXlTR2tyVXQzOVdwUmk4K2trZnNvWjRBK2VHekQyTms4eDh4N0ZVCkFLdW1mSXJ4MkphdFFQaXVVVk1ZTFNCem5VdVJ1bU95bHI4NzlqN052WWNDcXg1dmY5YjdKeVJUVzhnOVZoeSsKc0M2WEhDbFM3MnpHSTBjV01CWFpPNXY5VWwxKzlUdGxmU2RFYnV3dkE0ZFRaYXQ5SXI1eWNJaFlhd0xiMzExSwpkQ2pXZjR1LzE2VUNBd0VBQWFNak1DRXdEZ1lEVlIwUEFRSC9CQVFEQWdLa01BOEdBMVVkRXdFQi93UUZNQU1CCkFmOHdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRU5CQlpuU0dSeiszRG1jbWdFeE9DYll5blM4TDB0QkUwTmYKdWgzTk5SSWp3bW4vUTZxZDJ1RHU4K2pIMUhGbTNPUW9CdzZZZXYwUFZuY3U2MlJ0MStISmt6Z0pZNktvZ3grZQo4ZS9JdUsrYlZSR0ozdDUrZUVJOEVCb1BDbzdxRWtEVENoOEwzbTZMU2dqT3hHWkhLcERUNDdFQUpIRUF3U1hqCjQ1NTJtVE5tRmVTWHdjVTJlVWpFMUxPVmR0N3lwTGVXMnFzL0l2d0grckZHREorRitjeGxlM1ROckIwL2ZPZEwKc0NsMzcrbzMrL3pqSk5DZytQWFVrNEUyY3VCN0NHcHhhRU9keUNneVpKTzNuVE5yc3VnMjNhWXdMWHZnTXdaMwp3c09IbTlIbkMrKzYwTFh2a2lOL00wUS95eHA3SUk4WW5ubzM4Ly9oWWlYV1o2d0xQYlk9Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K\"}}],\"users\":[{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"user\":{\"token\":\"kubeconfig-u-jfg6jupqncr5cvr:5wcp4xphmlxq9z2hq4dpf6p2lhc9z8snkcbnjzr5cf7qrpvrtl7xs8\"}}],\"contexts\":[{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"context\":{\"user\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"cluster\":\"rke-datafeeds-dev-iad4-prod-conviva-com\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp212-10d-iad4-prod-conviva-com\",\"context\":{\"user\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"cluster\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp212-10d-iad4-prod-conviva-com\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp211-10d-iad4-prod-conviva-com\",\"context\":{\"user\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"cluster\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp211-10d-iad4-prod-conviva-com\"}},{\"name\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp213-10d-iad4-prod-conviva-com\",\"context\":{\"user\":\"rke-datafeeds-dev-iad4-prod-conviva-com\",\"cluster\":\"rke-datafeeds-dev-iad4-prod-conviva-com-rccp213-10d-iad4-prod-conviva-com\"}}],\"current-context\":\"rke-datafeeds-dev-iad4-prod-conviva-com\"}";
        String namespace = "conviva-druid-test";
        String podName = "druid-cluster-broker-0";
        CoreV1Api coreV1Api = kubernetesTool.buildCoreV1Api(config);
        V1Pod v1Pod = coreV1Api.readNamespacedPod(podName, namespace, null);
        PodLogs logs = new PodLogs();
        InputStream inputStream = logs.streamNamespacedPodLog(v1Pod);
        Streams.copy(inputStream, System.out);
    }

    public V1ConfigMap getNamespacedConfigMap(String kubeConfig, String namespace, String name) throws IOException, ApiException {
        CoreV1Api coreV1Api = buildCoreV1Api(kubeConfig);
        return coreV1Api.readNamespacedConfigMap(name, namespace, null);
    }

    public boolean isPodReady(List<V1ContainerStatus> containerStatuses) {
        return containerStatuses.stream()
                .allMatch(V1ContainerStatus::getReady);
    }

    public List<V1Pod> listNamespacedPod(String kubeConfig, String namespace) throws IOException, ApiException {
        CoreV1Api coreV1Api = buildCoreV1Api(kubeConfig);
        V1PodList v1PodList = coreV1Api.listNamespacedPod(namespace, null, ALLOW_WATCH_BOOKMARKS, null, null, null, LIMIT, null, null, TIME_OUT_SECONDS, WATCH);
        return v1PodList.getItems();
    }

    public List<V1ConfigMap> listNamespacedConfigMap(String kubeConfig, String namespace) throws IOException, ApiException {
        CoreV1Api coreV1Api = buildCoreV1Api(kubeConfig);
        V1ConfigMapList v1ConfigMapList = coreV1Api.listNamespacedConfigMap(namespace, null, ALLOW_WATCH_BOOKMARKS, null, null, null, LIMIT, null, null, TIME_OUT_SECONDS, WATCH);
        return v1ConfigMapList.getItems();
    }

    private CoreV1Api buildCoreV1Api(String kubeConfig) throws IOException {
        ApiClient apiClient = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new StringReader(kubeConfig))).build();
        Configuration.setDefaultApiClient(apiClient);
        return new CoreV1Api(apiClient);
    }

    private AppsV1Api buildAppsV1Api(String kubeConfig) throws IOException {
        ApiClient apiClient = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new StringReader(kubeConfig))).build();
        Configuration.setDefaultApiClient(apiClient);
        return new AppsV1Api(apiClient);
    }
}

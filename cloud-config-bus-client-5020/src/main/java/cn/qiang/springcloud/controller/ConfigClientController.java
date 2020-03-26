package cn.qiang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //配置更新手动刷新 POST请求 http://localhost:5020/actuator/refresh
public class ConfigClientController {

    @Value("${server.port}")
    private String port;

    @Value("${config}")
    private String config_dev;

    @GetMapping("/client/configdev")
    public String getConfigDev() {
        return "端口：" + port + "，config=" + config_dev;
    }
}

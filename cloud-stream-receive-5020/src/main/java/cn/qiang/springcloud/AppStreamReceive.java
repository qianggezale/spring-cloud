package cn.qiang.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableBinding(Sink.class)
public class AppStreamReceive {
    public static void main(String[] args) {

        SpringApplication.run(AppStreamReceive.class, args);

    }

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message) {

        System.out.println("端口" + port + "接收:" + message.getPayload());
    }
}

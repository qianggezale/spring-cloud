package cn.qiang.springcloud.service.impl;

import cn.qiang.springcloud.service.StreamService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class StreamServiceImpl implements StreamService {

    @Resource
    private Source source;

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        Message<String> build = MessageBuilder.withPayload(uuid).build();
        boolean send = source.output().send(build);
        return String.valueOf(send) + ";" + uuid;
    }
}

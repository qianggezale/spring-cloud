package cn.qiang.springcloud.controller;

import cn.qiang.springcloud.service.StreamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendCotroller {

    @Resource
    private StreamService streamService;

    @GetMapping("/send")
    public void send(){
        streamService.send();
    }

}

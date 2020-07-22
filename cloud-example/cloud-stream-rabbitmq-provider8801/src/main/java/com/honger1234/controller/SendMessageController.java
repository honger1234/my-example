package com.honger1234.controller;

import com.honger1234.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    @RequestMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}

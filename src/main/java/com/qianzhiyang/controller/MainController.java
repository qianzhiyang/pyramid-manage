package com.qianzhiyang.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.qianzhiyang.vo.MainEnterParam;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@RestController
@Slf4j
public class MainController {

    @PostMapping(value = "main")
    public Map<String, Object> mainEnter(@RequestBody MainEnterParam mainEnterParam) {
        log.info("mainEnterParam is {}", mainEnterParam);
        return Maps.newHashMap();
    }

}

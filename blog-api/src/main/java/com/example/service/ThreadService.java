package com.example.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    /**
     * 开启多线程，在用户点击文章后，更新观看数
     * @param article_id
     * @param template
     * @param view_count
     */
    @Async("taskExecutor")
    public void updateView_count(long article_id, RedisTemplate<String, String> template,long view_count) {
        view_count++;
        template.opsForValue().set("article_id"+article_id, JSON.toJSONString(view_count));
    }
}

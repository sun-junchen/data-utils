package com.example;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.entity.AccountDTO;
import com.example.utils.JsonUtils;
import com.example.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@SpringBootTest
public class RedisTest {

    @Test
    void testGet(){
        AccountDTO account = RedisUtils.get("account",AccountDTO.class);
        String user = RedisUtils.get("user",String.class);
        String user1 = RedisUtils.getStr("user");
        Long user2 = RedisUtils.getExpire("user",TimeUnit.SECONDS);
        Long account1 = RedisUtils.getExpire("account",TimeUnit.SECONDS);
        Pattern compile = Pattern.compile("^user*$");
        List<String> user3 = RedisUtils.scan("user*");
        List<String> user4= RedisUtils.findKeysForPage("user*",2,2);
        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("user1");
//        Long user5 = RedisUtils.inc("123", 1, TimeUnit.SECONDS);
//        System.out.println(user5);
        List<String> mget = RedisUtils.mget(list, String.class);
        System.out.println(mget);
        System.out.println(account);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(account1);
        System.out.println(user3);
        System.out.println(user4);
    }
    @Test
    public void testSet(){
        RedisUtils.set("account",new AccountDTO());
        RedisUtils.set("user","user");
        RedisUtils.set("user1","user1");
        RedisUtils.set("user2","user2");
//        Boolean user2 = RedisUtils.expire("user",2, TimeUnit.HOURS);
    }

    @Test
    public void testSet1(){
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date(),1);
        String jsonStr = JSONUtil.toJsonStr(accountDTO);
        AccountDTO obj = JsonUtils.toObj(jsonStr, AccountDTO.class);
        System.out.println(obj);
//        RedisUtils.set("account",new AccountDTO());
//        RedisUtils.set("user","user");
//        RedisUtils.set("user1","user1");
//        RedisUtils.set("user2","user2");
//        Boolean user2 = RedisUtils.expire("user",2, TimeUnit.HOURS);
    }
}

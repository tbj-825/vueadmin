package com.tbj;

import com.tbj.entity.SysUser;
import com.tbj.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class VueadminApplicationTests {

    @Resource
    private SysUserService service;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }


    @Test
    public void test1(){
        List<SysUser> sysUserList = service.listUser();
        sysUserList.forEach(System.out::println);
    }

}

package com.tbj.web;


import com.google.code.kaptcha.Producer;
import com.tbj.entity.Result;
import com.tbj.utils.ResultUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthController {

    @Resource
    private Producer producer;


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/captcha")
    public Result getCaptcha() throws IOException {

        String key = UUID.randomUUID().toString();

        String code = producer.createText();
        BufferedImage image = producer.createImage(code);

        //创建缓冲区
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());


        redisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("captchaImg", base64Img);
        return ResultUtil.sucResult(map);
    }
}

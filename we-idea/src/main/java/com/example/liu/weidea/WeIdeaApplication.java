package com.example.liu.weidea;

import com.example.liu.weidea.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@ServletComponentScan("com")
//@EntityScan(basePackages = {"com"})
//@EnableScheduling
//// 允许通过AopContext.currentProxy() 获取代理类
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
//@EnableAsync
public class WeIdeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeIdeaApplication.class, args);
//        // 启动netty服务器
//        try {
//            new NettyServer(8091).start();
//        } catch (Exception e) {
//            System.out.println("NettyServerError:" + e.getMessage());
//        }
    }



}

class Text {
    public static void main(String[] args) {
//        String str="a;b;c;d;e";
//        List<String> strArray = Arrays.asList(str.split(";"));
//        for (int a = 0; a < strArray.size(); a++) {
//            System.out.print(strArray.get(a) + "\t");
//        }

    }
}

package com.ins.helloautoconfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "ins.msg")
public class HelloProperties {
    /**
     * HelloProperties 开关
     */
    boolean enable = false;

    /**
     * 需要打招呼的成员列表
     */
    List<String> members = new ArrayList<>();
}

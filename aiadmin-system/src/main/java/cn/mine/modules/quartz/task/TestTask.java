package cn.mine.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试用
 * @author Zheng Jie
 * @date 2019-01-08
 */
@Slf4j
@Component
public class TestTask {

    public void run(String params){
        log.info("执行成功" + "  " + params);
    }

    public void run1(String str){
        log.info("执行成功，参数为： {}" + str);
    }
}

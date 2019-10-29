package me.zhengjie.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.quartz.repository.QuartzLogRepository;
import me.zhengjie.modules.quartz.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "alarmTask")
public class AlarmTask {

    @Autowired
    private QuartzLogRepository quartzLogRepository;

    @Autowired
    private QuartzJobService quartzJobService;

//    @Autowired
//    private

    public void run(String param){

    }
}

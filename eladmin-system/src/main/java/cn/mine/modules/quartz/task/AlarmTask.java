package cn.mine.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import cn.mine.modules.quartz.service.QuartzJobService;
import cn.mine.modules.system.service.AlgorithmService;
import cn.mine.modules.system.service.DeviceService;
import cn.mine.modules.system.service.dto.AlarmPolicyDTO;
import cn.mine.modules.system.service.dto.AlgorithmDTO;
import cn.mine.modules.system.service.dto.DeviceDTO;
import cn.mine.modules.system.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component(value = "alarmTask")
public class AlarmTask {
    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AlgorithmService algorithmService;

    public void run(String params) throws Exception {
        String[] idParams = params.split(",");
        if (idParams == null || idParams.length != 3){
            throw new Exception("任务参数异常");
        }
        Long deviceId = Long.valueOf(idParams[0]);
        Long algorithmId = Long.valueOf(idParams[1]);
        Integer count = Integer.valueOf(idParams[2]);
        DeviceDTO device = deviceService.findById(deviceId);
        AlgorithmDTO algorithm = algorithmService.findById(algorithmId);
        AlarmPolicyDTO alarmPolicy = algorithm.getAlarmPolicy();
        if (device == null || algorithm == null || alarmPolicy == null){
            throw new Exception("设备、算法或报警策略已被删除");
        }
        int threshold = alarmPolicy.getThreshold();
        String exception = algorithm.getException();
        Set<UserDTO> users = algorithm.getUsers();
        String deviceName = device.getDeviceName();
        String nvrName = device.getNvr().getName();
        if (count > threshold){
            String upgradeManner = alarmPolicy.getUpgradeAlarmManner();

        }else {
            String manner = alarmPolicy.getAlarmManner();

        }
    }
}

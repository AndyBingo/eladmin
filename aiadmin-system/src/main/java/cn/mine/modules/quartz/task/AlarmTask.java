package cn.mine.modules.quartz.task;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import lombok.extern.slf4j.Slf4j;
import cn.mine.modules.quartz.service.QuartzJobService;
import cn.mine.modules.system.service.AlgorithmService;
import cn.mine.modules.system.service.DeviceService;
import cn.mine.modules.system.service.dto.AlarmPolicyDTO;
import cn.mine.modules.system.service.dto.AlgorithmDTO;
import cn.mine.modules.system.service.dto.DeviceDTO;
import cn.mine.modules.system.service.dto.UserDTO;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private IAcsClient iAcsClient;

    @Value("${aliyun.sms.domain}")
    private String domain;

    @Value("${aliyun.sms.version}")
    private String version;

    @Value("${aliyun.sms.action}")
    private String action;

    @Value("${aliyun.sms.acceesskey}")
    private String acceesskey;

    @Value("${aliyun.sms.secret}")
    private String secret;

    @Value("${aliyun.sms.param.region}")
    private String region;

    @Value("${aliyun.sms.param.sign}")
    private String sign;

    @Value("${aliyun.sms.param.template}")
    private String template;



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
        boolean isText = false;
        boolean isMail = false;
        boolean isPhone = false;
        String policyManner = "";
        if (count > threshold){
            policyManner = alarmPolicy.getUpgradeAlarmManner();

        }else {
            policyManner = alarmPolicy.getAlarmManner();

        }
        isText = policyManner != null && policyManner.contains("0");
        isMail = policyManner != null && policyManner.contains("1");
        isPhone = policyManner != null && policyManner.contains("2");

        if (isText) {
            textToConcerns(nvrName, deviceName,exception, users);
        }
        if (isMail) {
            mailToConcerns();
        }

//        if (isPhone) {
//
//        }
    }

    private void textToConcerns(String nvr, String device, String exception, Set<UserDTO> users){
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("RegionId", region);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", template);
        StringBuilder sb = new StringBuilder();
        sb.append("NVR设备").append(nvr).append("上通道").append(device).append("触发").append(exception);
        request.putQueryParameter("TemplateParam","{\"content\":\"" + sb.toString() + "\",\"time\":\"" + LocalDateTime.now().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")) + "\"}");
        for(UserDTO user : users){
            request.putQueryParameter("PhoneNumbers", user.getPhone());
            try {
                CommonResponse response = iAcsClient.getCommonResponse(request);
                log.debug(response.getData());
            }catch(Exception e) {
                log.error("用户{}，手机号为{}信息发送失败", user.getUsername(),user.getPhone());
            }
        }
    }

    private void mailToConcerns(){

    }
}

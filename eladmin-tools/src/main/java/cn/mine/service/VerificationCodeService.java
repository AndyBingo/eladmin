package cn.mine.service;

import cn.mine.domain.VerificationCode;
import cn.mine.domain.vo.EmailVo;

/**
 * @author andy
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}

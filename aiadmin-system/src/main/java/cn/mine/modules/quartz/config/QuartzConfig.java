package cn.mine.modules.quartz.config;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 定时任务配置
 * @author
 * @date 2019-01-07
 */
@Configuration
public class QuartzConfig {

	/**
	 * 解决Job中注入Spring Bean为null的问题
	 */
	@Component("quartzJobFactory")
	public class QuartzJobFactory extends AdaptableJobFactory {

		@Autowired
		private AutowireCapableBeanFactory capableBeanFactory;

		@Override
		protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

			//调用父类的方法
			Object jobInstance = super.createJobInstance(bundle);
			capableBeanFactory.autowireBean(jobInstance);
			return jobInstance;
		}
	}

	/**
	 * 注入scheduler到spring
	 * @param quartzJobFactory
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "scheduler")
	public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception {
		SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
		factoryBean.setJobFactory(quartzJobFactory);
		factoryBean.afterPropertiesSet();
		Scheduler scheduler=factoryBean.getScheduler();
		scheduler.start();
		return scheduler;
	}

	@Bean
	public IAcsClient iAcsClient() {
		DefaultProfile profile =
				DefaultProfile.getProfile("cn-hangzhou", "LTAI4FpheMztrj2utFfHoMqe", "ZnM4xqrW8wdlRrMvrML5eyTd5OARsH");
		IAcsClient client = new DefaultAcsClient(profile);
		return client;
	}
}

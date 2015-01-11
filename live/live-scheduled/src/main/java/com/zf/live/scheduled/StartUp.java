package com.zf.live.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

	private static final Logger log = LoggerFactory.getLogger(StartUp.class);

	public static void main(String[] args) {

		@SuppressWarnings({ "resource", "unused" })
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		log.info("live-scheduler启动成功！");
		
		/*ScheduledTaskConfigure scheduledTaskConfigure = applicationContext.getBean(ScheduledTaskConfigure.class);
		if(scheduledTaskConfigure == null){
			log.error("com.zf.live.scheduled.ScheduledTaskConfigure bean未配置");
			System.exit(0); 
			return ;
		}

		List<? extends LiveTask> tasks =  scheduledTaskConfigure.getTasks();
		if(tasks == null || tasks.size() == 0){
			log.warn("任务列表为空，程序停止！");
			System.exit(0); 
			return ;
		}

		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
		BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext
				.getBeanFactory();  

		List<Trigger> triggers = new ArrayList<Trigger>();
		for (LiveTask liveTask : tasks) {
			
			String jobBeanName = liveTask.getClass().getSimpleName() + "Job" ;
			BeanDefinitionBuilder jobBeanDefitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MethodInvokingJobDetailFactoryBean.class);
			jobBeanDefitionBuilder.addPropertyReference("targetObject", liveTask.getClass().getSimpleName()) ;
			jobBeanDefitionBuilder.addPropertyValue("targetMethod", "execute");
			beanDefinitonRegistry.registerBeanDefinition(jobBeanName, jobBeanDefitionBuilder.getBeanDefinition()); 
			
			String triggerBeanName = liveTask.getClass().getSimpleName() + "Trigger" ;
			BeanDefinitionBuilder triggerBeanDefitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CronTriggerFactoryBean.class);
			triggerBeanDefitionBuilder.addPropertyReference("jobDetail", jobBeanName);
			triggerBeanDefitionBuilder.addPropertyValue("cronExpression", liveTask.getCronExpression());
			beanDefinitonRegistry.registerBeanDefinition(triggerBeanName, triggerBeanDefitionBuilder.getBeanDefinition()); 
			
			triggers.add((Trigger) applicationContext.getBean(triggerBeanName));
		}
		
		BeanDefinitionBuilder schedulerFactoryBeanDefintion = BeanDefinitionBuilder.genericBeanDefinition(SchedulerFactoryBean.class);
		schedulerFactoryBeanDefintion.addPropertyValue("triggers", triggers);
		schedulerFactoryBeanDefintion.setLazyInit(false);
		beanDefinitonRegistry.registerBeanDefinition("liveSchedulerFactoryBean", schedulerFactoryBeanDefintion.getBeanDefinition()); 
		
		log.info("live-scheduler启动成功...");  
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	} 

}

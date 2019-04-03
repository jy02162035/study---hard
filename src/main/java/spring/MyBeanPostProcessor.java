package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：Bean的后置处理器，在属性赋值后，init方法前后执行
 *
 * <p>
 * 创建时间：2019/01/22 14:54:54
 * </p>
 * <p>
 * 作者：小D课堂
 * </p>
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public Object postProcessAfterInitialization(Object bean, String
	// beanName) throws BeansException {
	// log.debug("***************postProcessAfterInitialization... beanClass[" +
	// bean + "] beanName[" + beanName + "]");
	//
	// return BeanPostProcessor.super.postProcessAfterInitialization(bean,
	// beanName);
	// }
	//
	// @Override
	// public Object postProcessBeforeInitialization(Object bean, String
	// beanName) throws BeansException {
	// log.debug("***************postProcessBeforeInitialization... beanClass["
	// + bean + "] beanName[" + beanName + "]");
	//// // 处理指定的bena
	//// if ("car".equals(beanName)) {
	//// System.out.println("***************car is come in");
	//// }
	//
	// Object object =
	// BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	// return object;
	// }

}

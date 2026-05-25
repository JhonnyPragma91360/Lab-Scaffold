package co.com.bancolombia.mq.config;

import co.com.bancolombia.commons.jms.api.MQMessageSender;
import co.com.bancolombia.commons.jms.mq.ReqReply;
import co.com.bancolombia.commons.jms.mq.config.MQSpringResolver;
import co.com.bancolombia.commons.jms.mq.config.factory.MQReqReplyFactory;
import co.com.bancolombia.commons.jms.mq.config.factory.MQSenderFactory;
import co.com.bancolombia.commons.jms.mq.config.senders.MQSenderContainer;
import co.com.bancolombia.mq.reqreply.ReqReplyGateway;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQGatewayConfig {
	@Bean
	public MQSenderContainer mqSenderContainer() {
		return new MQSenderContainer();
	}

	@Bean
	public MQMessageSender mqMessageSender(MQSpringResolver resolver) {
		ReqReply annotation = ReqReplyGateway.class.getAnnotation(ReqReply.class);
		return (MQMessageSender) MQSenderFactory.fromReqReply(annotation, resolver, ReqReplyGateway.class.getSimpleName());
	}

	@Bean
	public ReqReplyGateway reqReplyGateway(MQSpringResolver resolver) {
		ReqReply annotation = ReqReplyGateway.class.getAnnotation(ReqReply.class);
		Object target = MQReqReplyFactory.createMQReqReply(annotation, resolver, ReqReplyGateway.class.getSimpleName());

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		proxyFactory.setInterfaces(ReqReplyGateway.class);
		return (ReqReplyGateway) proxyFactory.getProxy();
	}
}

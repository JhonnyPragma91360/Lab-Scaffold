package co.com.bancolombia.mq.reqreply;

import co.com.bancolombia.commons.jms.api.MQRequestReply;
import co.com.bancolombia.commons.jms.internal.models.MQListenerConfig.QueueType;
import co.com.bancolombia.commons.jms.mq.ReqReply;

// TODO: Change the queue name, or use from properties like ${my.queue}
@ReqReply(requestQueue = "${commons.jms.output-queue}" ,replyQueue = "${commons.jms.input-queue}", queueType = QueueType.FIXED)
public interface ReqReplyGateway extends MQRequestReply {
}
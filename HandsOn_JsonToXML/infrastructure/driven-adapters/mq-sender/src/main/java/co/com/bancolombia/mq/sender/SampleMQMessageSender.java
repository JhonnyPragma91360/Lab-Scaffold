package co.com.bancolombia.mq.sender;

import co.com.bancolombia.commons.jms.api.MQMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SampleMQMessageSender /* implements SomeGateway */ {
    private final MQMessageSender sender;
//    private final MQQueuesContainer container; // Inject it to reference a queue

    public Mono<String> send(String message) {
        return sender.send(context ->
//                  Message textMessage = context.createTextMessage(message);
//                  textMessage.setJMSReplyTo(container.get("any-custom-value")); // Inject the reply to queue from container
                    context.createTextMessage(message)
                )
                .name("mq_send_message")
                .tag("operation", "my-operation") // TODO: Change operation name
                .metrics();
    }
}

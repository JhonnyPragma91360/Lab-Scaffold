package co.com.bancolombia.mq.listener;

import co.com.bancolombia.commons.jms.api.MQMessageSender;
import co.com.bancolombia.commons.jms.mq.EnableMQMessageSender;
import co.com.bancolombia.commons.jms.mq.MQListener;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
@EnableMQMessageSender
public class SampleMQMessageListener {

    private final MQMessageSender sender;
    private final int delay;

    public SampleMQMessageListener(MQMessageSender sender, @Value("${mq-response.delay}") int delay) {
        this.sender = sender;
        this.delay = delay;
    }

    // For fixed queues
    @MQListener
    public Mono<Void> process(Message message) throws JMSException {
        return send(message)
                .then();
    }

    private Mono<String> send(Message message) throws JMSException {
        String text = ((TextMessage) message).getText();
        String textR = "<NS1:esbXML xmlns:NS1=\"http://grupobancolombia.com/intf/IL/esbXML/V3.0\"> <Header> <systemId>AW0611001</systemId> <messageId>reactive</messageId> </Header> <Body> <NS1:consultarDatosPersonaResponse xmlns:NS1=\"http://grupobancolombia.com/intf/Clientes/GestionClientes/ConsultaDatosPersona/V1.0\"> <nombre>Juliana Leon</nombre> <edad>50</edad> </NS1:consultarDatosPersonaResponse> </Body> </NS1:esbXML>";
        Destination destination = message.getJMSReplyTo();
        String correlationID = message.getJMSMessageID();

        log.info("ReplyTo: {}", message.getJMSReplyTo());
        log.info("CorrelationID: {}", correlationID);
        log.info("Message received: {}", text);
        log.info("Message response: {}", textR);

        return Mono.just(0)
                .delayElement(Duration.ofMillis(delay))
                .flatMap(x -> sender.send(destination, context -> {
                    Message textMessage = context.createTextMessage(textR);
                    textMessage.setJMSCorrelationID(correlationID);

                    return textMessage;
                }));
    }

    // For an automatic generated temporary queue
    // @MQListener(tempQueueAlias = "any-custom-value")
    // public Mono<Void> processFromTemporaryQueue(Message message) throws JMSException {
    //     timer.record(System.currentTimeMillis() - message.getJMSTimestamp(), TimeUnit.MILLISECONDS);
    //     String text = ((TextMessage) message).getText();
    //     // return useCase.sample(text);
    //     return Mono.empty();
    // }
}

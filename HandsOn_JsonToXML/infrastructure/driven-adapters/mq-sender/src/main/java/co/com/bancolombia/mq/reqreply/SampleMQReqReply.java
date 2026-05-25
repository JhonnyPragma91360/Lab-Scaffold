package co.com.bancolombia.mq.reqreply;

import co.com.bancolombia.model.consultadatos.gateways.ConsultaDatosService;
import co.com.bancolombia.model.consultadatos.request.ConsultaDatosRq;
import co.com.bancolombia.model.consultadatos.response.ConsultaDatosRs;
import co.com.bancolombia.mq.config.XmlMapperWrapper;
import co.com.bancolombia.mq.dtos.commons.Header;
import co.com.bancolombia.mq.dtos.request.Body;
import co.com.bancolombia.mq.dtos.request.ConsultaDatosRqDTO;
import co.com.bancolombia.mq.dtos.request.ConsultarDatosPersona;
import co.com.bancolombia.mq.dtos.response.ConsultaDatosRsDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Component
@RequiredArgsConstructor
public class SampleMQReqReply implements ConsultaDatosService {
    private final ReqReplyGateway sender;

    private final XmlMapperWrapper xmlMapperWrapper;

    public Mono<String> doRequest(String message) {
        return sender.requestReply(message)
                .name("mq_req_reply")
                .tag("operation", "my-operation") // TODO: Change operation name
                .metrics()
                .map(this::extractResponse);
    }

    @SneakyThrows
    private String extractResponse(Message message) {
        TextMessage textMessage = (TextMessage) message;
        return textMessage.getText();
    }

    @Override
    public Mono<ConsultaDatosRs> consultarDatosPersona(ConsultaDatosRq consultaDatosRq) {
        return Mono.just(transformModelToDTOToXML(consultaDatosRq))
                .flatMap(x->doRequest(x))
                .flatMap(y->transformDTOToModel(y));

    }

    //Método que se encarga de traer los datos ya guardados del modelo para inyectar la información en los DTOs
    @SneakyThrows
    private String transformModelToDTOToXML(ConsultaDatosRq consultaDatosRq){

        ConsultaDatosRqDTO consultaDatosRqDTO=  ConsultaDatosRqDTO.builder()
                .header(Header.builder()
                        .systemId(consultaDatosRq.getSystemId())
                        .messageId(consultaDatosRq.getMessageId())
                        .build())
                .body(Body.builder()
                        .consultarDatosPersona(ConsultarDatosPersona.builder()
                                .numeroDocumento(consultaDatosRq.getNumeroDoc())
                                .tipoDocumento(consultaDatosRq.getTipoDoc())
                                .build())
                        .build())
                .build();

        return xmlMapperWrapper.xmlMapper().writeValueAsString(consultaDatosRqDTO); //Objeto de la librería Jackson que permite escribir los textos XML
    }

    @SneakyThrows
    private Mono<ConsultaDatosRs> transformDTOToModel(String response){
        ConsultaDatosRsDTO consultaDatosRsDTO=  xmlMapperWrapper.xmlMapper().readValue(response,ConsultaDatosRsDTO.class);
        return  Mono.just(ConsultaDatosRs.builder()
                .nombre(consultaDatosRsDTO.getBody().getConsultarDatosPersonaResponse().getNombre())
                .edad(consultaDatosRsDTO.getBody().getConsultarDatosPersonaResponse().getEdad())
                .build());
    }


}

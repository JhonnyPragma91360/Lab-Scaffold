package co.com.bancolombia.api;

import co.com.bancolombia.api.dtos.commons.MetaDTO;
import co.com.bancolombia.api.dtos.request.ConsultaDatosRqDTO;
import co.com.bancolombia.api.dtos.response.ConsultaDatosRsDTO;
import co.com.bancolombia.api.dtos.response.DataRsDTO;
import co.com.bancolombia.api.dtos.response.InformacionPersonaDTO;
import co.com.bancolombia.model.consultadatos.request.ConsultaDatosRq;
import co.com.bancolombia.model.consultadatos.response.ConsultaDatosRs;
import co.com.bancolombia.usecase.consultadatos.ConsultaDatosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;
private  final ConsultaDatosUseCase consultaDatosUseCase;

    public Mono<ServerResponse> listenPOSTConsultaDatosPersona(ServerRequest serverRequest) {
        // usecase.logic();
        return serverRequest.bodyToMono(ConsultaDatosRqDTO.class)
                .map(dto -> consultaDatosRqDTO2ConsultaDatosRq(dto, serverRequest.headers()))
                .flatMap(consultaDatosUseCase::consultarDatosPersona)
                .map(model -> consultaDatosRs2consultaDatosRsDTO(model,serverRequest.headers()))
                .flatMap(x -> ServerResponse.ok().bodyValue(x));
    }

    // Función transformar DTO > ModelRq
    private ConsultaDatosRq consultaDatosRqDTO2ConsultaDatosRq (ConsultaDatosRqDTO consultaDatosRqDTO, ServerRequest.Headers headers){
        return ConsultaDatosRq.builder()
                .systemId(headers.firstHeader("id-consumer"))
                .messageId(headers.firstHeader("message-id"))
                .tipoDoc(consultaDatosRqDTO.getData().getIdentificacionCliente().getTipoDoc())
                .numeroDoc(consultaDatosRqDTO.getData().getIdentificacionCliente().getNumeroDoc())
                .build();
    }

    private ConsultaDatosRsDTO consultaDatosRs2consultaDatosRsDTO (ConsultaDatosRs consultaDatosRs, ServerRequest.Headers headers){
        return ConsultaDatosRsDTO.builder()
                .meta(MetaDTO.builder()
                        .messageId(headers.firstHeader("message-id"))
                        .consumerId(headers.firstHeader("id-consumer"))
                        .build())
                .data(DataRsDTO.builder()
                        .informacionPersonaDTO(InformacionPersonaDTO.builder()
                                .edad(consultaDatosRs.getEdad())
                                .nombre(consultaDatosRs.getNombre())
                                .build())
                        .build())
                .build();

    }
}

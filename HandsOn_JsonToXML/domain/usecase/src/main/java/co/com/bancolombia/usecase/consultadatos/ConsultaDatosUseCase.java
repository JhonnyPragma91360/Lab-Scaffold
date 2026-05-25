package co.com.bancolombia.usecase.consultadatos;

import co.com.bancolombia.model.consultadatos.gateways.ConsultaDatosService;
import co.com.bancolombia.model.consultadatos.request.ConsultaDatosRq;
import co.com.bancolombia.model.consultadatos.response.ConsultaDatosRs;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor

public class ConsultaDatosUseCase {

    private final ConsultaDatosService consultaDatosService;

    public Mono<ConsultaDatosRs> consultarDatosPersona(ConsultaDatosRq consultaDatosRq){
        return consultaDatosService.consultarDatosPersona(consultaDatosRq);
    }
}
package co.com.bancolombia.model.consultadatos.gateways;

import co.com.bancolombia.model.consultadatos.request.ConsultaDatosRq;
import co.com.bancolombia.model.consultadatos.response.ConsultaDatosRs;
import reactor.core.publisher.Mono;

public interface ConsultaDatosService {
    Mono<ConsultaDatosRs> consultarDatosPersona (ConsultaDatosRq consultaDatosRq);
}
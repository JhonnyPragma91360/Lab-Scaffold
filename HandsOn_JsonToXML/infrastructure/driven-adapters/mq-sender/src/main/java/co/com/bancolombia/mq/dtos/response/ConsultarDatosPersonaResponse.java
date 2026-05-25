package co.com.bancolombia.mq.dtos.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JacksonXmlRootElement(localName = "NS1:consultarDatosPersonaResponse", namespace = "http://grupobancolombia.com/intf/Clientes/GestionClientes/ConsultaDatosPersona/V1.0")
public class ConsultarDatosPersonaResponse {
    @JacksonXmlProperty(localName = "nombre")
    public String nombre;
    @JacksonXmlProperty(localName = "edad")
    public int edad;
}
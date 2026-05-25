package co.com.bancolombia.mq.dtos.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "NS2:consultarDatosPersona")
public class ConsultarDatosPersona {
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:NS2")
    private final String xmlnNS2 = "http://grupobancolombia.com/intf/Clientes/GestionClientes/ConsultaDatosPersona/V1.0";
    @JacksonXmlProperty(localName = "tipoDocumento")
    private String tipoDocumento;
    @JacksonXmlProperty(localName = "numeroDocumento")
    private String numeroDocumento;
}

package co.com.bancolombia.mq.dtos.response;

import co.com.bancolombia.mq.dtos.commons.Header;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "esbXML", namespace = "http://grupobancolombia.com/intf/IL/esbXML/V3.0")
public class ConsultaDatosRsDTO {
    @JacksonXmlProperty(localName = "Header")
    private Header header;
    @JacksonXmlProperty(localName = "Body")
    private Body body;
}
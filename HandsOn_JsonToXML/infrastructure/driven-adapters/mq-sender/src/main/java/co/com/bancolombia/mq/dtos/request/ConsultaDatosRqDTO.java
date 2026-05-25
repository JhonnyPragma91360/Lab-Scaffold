package co.com.bancolombia.mq.dtos.request;

import co.com.bancolombia.mq.dtos.commons.Header;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "NS1:esbXML")
public class ConsultaDatosRqDTO {
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:NS1")
    private final String xmlns = "http://grupobancolombia.com/intf/IL/esbXML/V3.0";

    @JacksonXmlProperty(localName = "Header")
    private Header header;

    @JacksonXmlProperty(localName = "Body")
    private Body body;
}
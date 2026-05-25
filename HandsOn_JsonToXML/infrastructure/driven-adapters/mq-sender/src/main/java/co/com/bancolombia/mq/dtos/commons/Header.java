package co.com.bancolombia.mq.dtos.commons;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

//Esta es la clase Header
@Getter
@Setter
@Builder
@JacksonXmlRootElement(localName = "Header")
@AllArgsConstructor
@NoArgsConstructor
public class Header {

    @JacksonXmlProperty(localName = "systemId")
    private String systemId;
    @JacksonXmlProperty(localName = "messageId")
    private String messageId;
}
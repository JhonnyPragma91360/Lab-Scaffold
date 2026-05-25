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
@JacksonXmlRootElement(localName = "Body")
@AllArgsConstructor
public class Body {

    /*
       Este es el xml que se desea representar:

       <NS1:esbXML xmlns:NS1="http://grupobancolombia.com/intf/EAI/esbXML/V1.0">
	<Header>
		<systemId>AW0611001</systemId>
		<messageId>reactive</messageId>
	</Header>
	<Body>
         <NS2:consultarDatosPersona xmlns:NS2="http://grupobancolombia.com/intf/Clientes/GestionClientes/ConsultaDatosPersona/V1.0">
                <tipoDocumento>FS001</tipoDocumento>
                <numeroDocumento>1989647332</numeroDocumento>
            </NS2:consultarDatosPersona>
     </Body>
    </NS1:esbXML>

     */
    @JacksonXmlProperty(localName = "NS2:consultarDatosPersona")
    private ConsultarDatosPersona consultarDatosPersona;
}
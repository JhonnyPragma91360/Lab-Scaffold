package co.com.bancolombia.mq.dtos.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Builder
@JacksonXmlRootElement(localName = "Body")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Body {


    /*
       Este es el xml que se desea representar:

       <NS1:esbXML xmlns:NS1="http://grupobancolombia.com/intf/EAI/esbXML/V1.0">
	<Header>
		<systemId>AW0611001</systemId>
		<messageId>reactive</messageId>
	</Header>
	<Body>
		<NS1:consultarDatosPersonaResponse xmlns:NS1="http://grupobancolombia.com/intf/Clientes/GestionClientes/ConsultaDatosPersona/V1.0">
			<nombre>Laura Juliana</nombre>
			<edad>50</edad>
			<correo>juliana@correoscaffold.com</correo>
			<direccion>Bogotá DC</direccion>
			<fechaCreacion>12-06-2021</fechaCreacion>
		</NS1:consultarDatosPersonaResponse>
	</Body>
</NS1:esbXML>

     */
    @JacksonXmlProperty(localName = "consultarDatosPersonaResponse")
    public ConsultarDatosPersonaResponse consultarDatosPersonaResponse;
}
package co.com.bancolombia.model.consultadatos.request;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConsultaDatosRq {
    private String systemId;
    private String messageId;
    private String tipoDoc;
    private String numeroDoc;
}
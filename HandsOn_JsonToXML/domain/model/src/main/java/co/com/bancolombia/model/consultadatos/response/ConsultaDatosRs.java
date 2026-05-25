package co.com.bancolombia.model.consultadatos.response;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConsultaDatosRs {
    private String nombre;
    private int edad;
}
package co.com.bancolombia.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentClienteDTO {
    @JsonProperty("tipoDocumento")
    private String tipoDoc;
    @JsonProperty("numeroDocumento")
    private String numeroDoc;
}
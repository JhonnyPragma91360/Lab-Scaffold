package co.com.bancolombia.api.dtos.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacionPersonaDTO {
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("edad")
    private int edad;
}
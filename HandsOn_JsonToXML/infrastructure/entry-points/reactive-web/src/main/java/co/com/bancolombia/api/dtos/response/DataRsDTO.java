package co.com.bancolombia.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataRsDTO {
    @JsonProperty("Persona")
    private InformacionPersonaDTO informacionPersonaDTO;
}
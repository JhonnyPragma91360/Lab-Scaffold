package co.com.bancolombia.api.dtos.response;

import co.com.bancolombia.api.dtos.commons.MetaDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaDatosRsDTO {
    private MetaDTO meta;
    private DataRsDTO data;
}
package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoResponseDTO {

    private Date fechaHora;
    private Map<String,Object> rawData;
    private String categoria;
    private String subCategoria;
    private String identificador;
}

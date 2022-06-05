package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoDTO {

    private Date fechaHora;
    private String rawData;
    private String categoria;
    private String subCategoria;
    private String identificador;
}

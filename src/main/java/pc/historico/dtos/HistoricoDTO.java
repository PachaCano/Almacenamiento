package pc.historico.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoDTO {

    private int id;

    private Long fechaHoraCreacion;
    private String rawData;
    private String categoria;
    private String subCategoria;
    private String identificador;

    @Override
    public String toString() {
        return "{\"id\":" + id +
                "\"fechaHoraCreacion\":" + fechaHoraCreacion +
                "\"rawData\":" + rawData +
                ", \"categoria\":\"" + categoria + '\"' +
                ", \"subCategoria\":\"" + subCategoria + '\"' +
                ", \"identificador\":\"" + identificador + '\"' +
                '}';
    }
}


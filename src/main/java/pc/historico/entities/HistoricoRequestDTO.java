package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoRequestDTO {

    private RawData rawData;
    private String categoria;
    private String subCategoria;
    private String identificador;

    @Override
    public String toString() {
        return "{\"rawData\":" + rawData +
                ", \"categoria\":\"" + categoria + '\"' +
                ", \"subCategoria\":\"" + subCategoria + '\"' +
                ", \"identificador\":\"" + identificador + '\"' +
                '}';
    }
}

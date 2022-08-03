package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RawData {

    private Double temperatura;
    private Double humedad;
    private Double presion;
    private Double puntoRocio;
    private Double altitud;
    private String fechaHora;
    private String localizacion;

    @Override
    public String toString() {
        String fechaHoraAux = '\"' + fechaHora + '\"';
        String localizacionAux = '\"' + localizacion + '\"';
        return "{\"temperatura\":" + temperatura +
                ", \"humedad\":" + humedad +
                ", \"presion\":" + presion +
                ", \"puntoRocio\":" + puntoRocio +
                ", \"altitud\":" + altitud +
                ", \"fechaHora\":" + fechaHoraAux +
                ", \"localizacion\":" + localizacionAux +
                '}';
    }
}

package pc.historico.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import pc.historico.entities.Historico;
import pc.historico.entities.RawData;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoDTO {

    public HistoricoDTO (Historico h) {
        this.id = h.getId();
        this.categoria = h.getCategoria();
        this.subCategoria = h.getSubCategoria();
        this.identificador = h.getIdentificador();
        JSONObject json = new JSONObject(h.getRawData());
        this.rawData = new RawData();
        this.rawData.setAltitud(json.getDouble("altitud"));
        String fechaHora = json.getString("fechaHora").replace("T", " ");
        this.rawData.setFechaHora(fechaHora);
        this.rawData.setHumedad(json.getDouble("humedad"));
        this.rawData.setPresion(json.getDouble("presion"));
        this.rawData.setPuntoRocio(json.getDouble("puntoRocio"));
        this.rawData.setTemperatura(json.getDouble("temperatura"));
        this.rawData.setLocalizacion(json.getString("localizacion"));
    }

    private Long id;
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

package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Historico")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Historico{
    @Override
    public String toString() {
        return "{\"id\":" + id +
                ", \"fechaHoraCreacion\":" + fechaHoraCreacion +
                ", \"rawData\":" + rawData +
                ", \"categoria\":\"" + categoria + '\"' +
                ", \"subCategoria\":\"" + subCategoria + '\"' +
                ", \"identificador\":\"" + identificador + '\"' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //se está autogenerando?

    @Column(nullable = false)
    private Date fechaHoraCreacion;
    private String rawData; //Usar función de String a JSON y viceversa después

    @Column(length = 100, nullable = false)
    private String categoria;
    @Column(length = 100)
    private String subCategoria;
    @Column(length = 100, nullable = false)
    private String identificador;
}

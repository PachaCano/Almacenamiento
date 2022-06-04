package pc.Historico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Historico")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Historico{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private java.sql.Timestamp fechaHoraCreacion;
    private String rawData; //Usar función de String a JSON y viceversa después

    @Column(length = 100, nullable = false)
    private String categoria;
    @Column(length = 100)
    private String subCategoria;
    @Column(length = 100, nullable = false)
    private String identificador;
}

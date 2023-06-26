package contanet.api.spring.modelos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campo {
    @Id
    private int id_campo;
    private String nomb_campo;
    private String descripcion;
    private int id_carpeta;
    private int id_modulo;
    
}

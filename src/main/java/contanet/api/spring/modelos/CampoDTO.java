package contanet.api.spring.modelos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampoDTO {

    private int id_campo;
    private String nomb_campo;
    private String descripcion;
    private int id_carpeta;
    private String nomb_carpeta;
    private int id_modulo;
    private String nomb_modulo;
    
}

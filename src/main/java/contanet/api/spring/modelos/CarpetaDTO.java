package contanet.api.spring.modelos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarpetaDTO {
    private int id_carpeta;
    private String nomb_carpeta;
    private String descripcion;
    private int id_modulo;
    private String nomb_modulo;
}

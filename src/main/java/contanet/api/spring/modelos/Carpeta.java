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
public class Carpeta {
    @Id
    private int id_carpeta;
    private String nomb_carpeta;
    private String descripcion;
    private int id_modulo;
}
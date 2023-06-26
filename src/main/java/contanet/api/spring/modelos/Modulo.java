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
public class Modulo {
    @Id
    private int id_modulo;
    private String nomb_modulo;
    private String descripcion;
}
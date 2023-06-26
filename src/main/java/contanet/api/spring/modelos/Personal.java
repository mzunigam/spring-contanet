package contanet.api.spring.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Personal")
public class Personal implements Serializable {
    @Id
    private int id_personal;
    private String nomb_personal;
    private String ape_personal;
    private String fecha_registro;
    private String email_personal;
    private int id_tipousuario;
    private String usuario;
    private String password;
}
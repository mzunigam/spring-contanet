package contanet.api.spring.modelos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta<T> implements Serializable{
    List<T> data;
    boolean status;
    String message;

}

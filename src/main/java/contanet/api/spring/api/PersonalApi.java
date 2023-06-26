package contanet.api.spring.api;

import contanet.api.spring.modelos.Personal;
import contanet.api.spring.repositorios.PersonalRepository;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PersonalApi {

    final PersonalRepository personalRepository;

    public PersonalApi(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @GetMapping("/personal")
    public List<Personal> getPersonales() {
        return personalRepository.findAll();
    }

    @PostMapping("/personal")
    public Personal savePersonal(@RequestBody Personal personal) {
        return personalRepository.save(personal);
    }

    @GetMapping("/personal/{usuario}")
    public Personal getPersonal(@PathVariable("usuario") String usuario) {
        return personalRepository.findByUsuario(usuario);
    }

    @PutMapping("/personal")
    public Personal updatePersonal(@RequestBody Personal personal) {
        return personalRepository.save(personal);
    }

    @DeleteMapping("/personal/{usuario}")
    public String deletePersonal(@PathVariable("usuario") String usuario) {
        Personal personal = personalRepository.findByUsuario(usuario);
        if (personal == null) {
            return new JSONObject().put("message", "Personal no encontrado").put("status", false).toString();
        }else{
        personalRepository.delete(personal);
        return new JSONObject().put("message", "Personal eliminado satisfactoriamente").put("status", true).toString();
        }
    }

    @PostMapping(produces = "application/json", value = "/login")
    public ResponseEntity<String> login(@RequestBody String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);
        String usuario = jsonObject.getString("usuario");
        String password = jsonObject.getString("password");
        Personal personal = personalRepository.findByUsuario(usuario);
        if (personal != null) {
            if (personal.getPassword().equals(password)) {
                return ResponseEntity.ok()
                        .body(new JSONObject().put("message", "Login exitoso").put("status", true).put("type",personal.getId_tipousuario()).toString());
            } else {
                return ResponseEntity.ok()
                        .body(new JSONObject().put("message", "Contraseña incorrecta").put("status", false).toString());
            }
        }
        return ResponseEntity.ok()
                .body(new JSONObject().put("message", "Usuario no encontrado").put("status", false).toString());
    }
}
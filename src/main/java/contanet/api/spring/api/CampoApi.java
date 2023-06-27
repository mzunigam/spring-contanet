package contanet.api.spring.api;

import contanet.api.spring.modelos.Campo;
import contanet.api.spring.modelos.CampoDTO;
import contanet.api.spring.modelos.Respuesta;
import contanet.api.spring.repositorios.CampoRepository;
import contanet.api.spring.servicios.CampoService;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CampoApi {

    CampoRepository campoRepository;
    CampoService campoService;

    public CampoApi(CampoRepository campoRepository,CampoService campoService) {
        this.campoRepository = campoRepository;
        this.campoService = campoService;
    }

    @GetMapping("/campo")
    public Respuesta<Campo> getCampos() {
        List<Campo> listaCampos = campoRepository.findAll();
        if (listaCampos.size() != 0) {
            return new Respuesta<Campo>(listaCampos, true, "Campos encontrados satisfactoriamente");
        } else {
            return new Respuesta<Campo>(listaCampos, false, "No se encontraron campos");
        }

    }

    @PostMapping("/campo/get")
    public Respuesta<CampoDTO> getCampos(@RequestBody String json) {
        System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);
        List<CampoDTO> listaCampos = campoService.getCampos(jsonObject);
        if (listaCampos.size() != 0) {
            return new Respuesta<CampoDTO>(listaCampos, true, "Campos encontrados satisfactoriamente");
        } else {
            return new Respuesta<CampoDTO>(listaCampos, false, "No se encontraron campos");
        }
    }


    @PostMapping("/campo")
    public Respuesta<Campo> saveCampo(@RequestBody String campo) {
        System.out.println(campo.toString());
        if(campoService.createCampo(new JSONObject(campo)) != null){
            return new Respuesta<Campo>(null, true, "Campo creado satisfactoriamente");
        }else{
            return new Respuesta<Campo>(null, false, "Error al crear campo");
        }
    }

    @GetMapping("/campo/{id}")
    public Campo getCampo(@PathVariable("id") Integer id) {
        Optional<Campo> campo = campoRepository.findById(id);
        if (campo.isPresent()) {
            return campo.get();
        } else {
            return null;
        }
    }

    @PutMapping("/campo")
    public Respuesta<Campo>updateCampo(@RequestBody Campo campo) {
        Optional<Campo> campoOptional = campoRepository.findById(campo.getId_campo());
        if (campoOptional.isPresent()) {
            campoRepository.save(campo);
            return new Respuesta<Campo>(null, true, "Campo actualizado satisfactoriamente");
        } else {
            return new Respuesta<Campo>(null, false, "Error al actualizar campo");
        }
    }

    @DeleteMapping("/campo/{id}")
    public String deleteCampo(@PathVariable("id") Integer id) {
        Optional<Campo> campo = campoRepository.findById(id);
        if (!campo.isPresent()) {
            return new JSONObject().put("message", "Campo no encontrado").put("status", false).toString();
        } else {
            campoRepository.delete(campo.get());
            return new JSONObject().put("message", "Campo eliminado satisfactoriamente").put("status", true).toString();
        }
    }

}
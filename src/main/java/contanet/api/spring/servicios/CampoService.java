package contanet.api.spring.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contanet.api.spring.comunes.Utilidades;
import contanet.api.spring.modelos.Campo;
import contanet.api.spring.modelos.CampoDTO;
import contanet.api.spring.modelos.Carpeta;
import contanet.api.spring.repositorios.CampoRepository;
import contanet.api.spring.repositorios.CarpetaRepository;
import contanet.api.spring.repositorios.ModuloRepository;

@Service
public class CampoService {

    @Autowired
    private CampoRepository campoRepository;
    @Autowired
    private CarpetaRepository carpetaRepository;
    @Autowired
    private ModuloRepository moduloRepository;

    public JSONObject validateCampo(JSONObject obj) {
        if (Utilidades.hasAllFields(obj, Campo.class)) {
            JSONObject errores = new JSONObject();
            int id_carpeta = obj.getInt("id_carpeta");
            Optional<Carpeta> carpeta = carpetaRepository.findById(id_carpeta);
            if (!carpeta.isPresent()) {
                errores.put("id_carpeta", "No existe carpeta con id: " + id_carpeta);
            }
            if (errores.length() > 0) {
                return new JSONObject().put("message", "Errores de validacion").put("status", false).put("errors",
                        errores);
            } else {
                return new JSONObject().put("message", "OK").put("status", true).put("id_modulo",
                        carpeta.get().getId_modulo());
            }
        } else {
            return new JSONObject().put("message", "Faltan campos").put("status", false).put("missingFields",
                    Utilidades.missingFields(obj, Campo.class));
        }
    }

    public Campo createCampo(JSONObject obj) {
        System.out.println(obj);
        JSONObject response = validateCampo(obj);
        if (response.getBoolean("status")) {
            Campo campo = new Campo();
            if(obj.isNull("id_campo") || obj.getInt("id_campo") == 0){
                campoRepository.findAll().stream().mapToInt(x -> x.getId_campo()).max().ifPresent(x -> campo.setId_campo(x + 2)
                );
            }else{
                campo.setId_campo(obj.getInt("id_campo"));
            }
            campo.setNomb_campo(obj.getString("nomb_campo"));
            campo.setDescripcion(obj.getString("descripcion"));
            campo.setId_carpeta(obj.getInt("id_carpeta"));
            campo.setId_modulo(response.getInt("id_modulo"));
            return campoRepository.save(campo);
        } else {
            throw new RuntimeException(response.toString());
        }
    }

    public List<CampoDTO> getCampos(JSONObject params) {
        int id_carpeta = params.isNull("id_carpeta") ? 0 : params.getInt("id_carpeta");
        int id_modulo = params.isNull("id_modulo") ? 0 : params.getInt("id_modulo");
        String textoCampo = params.isNull("campo") ? "" : params.getString("campo").toLowerCase();

        List<Campo> campos = campoRepository.findAll();
        if (campos.size() != 0) {

            if (id_carpeta != 0) {
            campos = campos.stream().filter( x -> x.getId_carpeta() == id_carpeta).collect(Collectors.toList());
            }
            if (id_modulo != 0) {
            campos = campos.stream().filter( x -> x.getId_modulo() == id_modulo).collect(Collectors.toList());
            }

            if(!textoCampo.isEmpty()){
            campos = campos.stream().filter( x -> x.getNomb_campo().toLowerCase().contains(textoCampo) 
            || x.getDescripcion().toLowerCase().contains(textoCampo)).collect(Collectors.toList());
            }
        
            return campos.stream().map( x -> {
                Campo c =  x;
                int id_campo1 = c.getId_campo();
                String nomb_campo = c.getNomb_campo();
                String descripcion = c.getDescripcion();
                int id_carpeta1 = c.getId_carpeta();
                String nomb_carpeta = id_carpeta1 == 0 ? "" :carpetaRepository.findById(id_carpeta1).get().getNomb_carpeta();
                int id_modulo1 = c.getId_modulo();
                String nomb_modulo = id_modulo1 == 0 ? "" : moduloRepository.findById(id_modulo1).get().getNomb_modulo();
                return new CampoDTO(id_campo1, nomb_campo, descripcion, id_carpeta1, nomb_carpeta, id_modulo1, nomb_modulo);
            }).collect(Collectors.toList());
 
        } else {
            return new ArrayList<CampoDTO>();
        }
    }
}

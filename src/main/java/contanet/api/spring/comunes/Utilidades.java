package contanet.api.spring.comunes;

import java.lang.reflect.Field;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utilidades {

    public static <T> JSONArray missingFields(JSONObject jsonObject, Class<T> clazz) {
        JSONArray response = new JSONArray();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!jsonObject.has(field.getName())) {
                response.put(field.getName());
            }
        }
        return response;
    }

    public static <T> Boolean hasAllFields(JSONObject jsonObject, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!jsonObject.has(field.getName())) {
                return false;
            }
        }
        return true;

    }

    
}

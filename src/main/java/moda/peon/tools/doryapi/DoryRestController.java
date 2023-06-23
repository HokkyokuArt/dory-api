package moda.peon.tools.doryapi;

import moda.peon.tools.doryapi.exceptions.UUIDNotProvided;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v0")
public class DoryRestController {

    @GetMapping
    public Map getAll() {
        return DoryPercistance.instance.collections;
    }

    @GetMapping("{collection}")
    public Object getOneCollection(@PathVariable String collection) {
        return DoryPercistance.instance.collections.get(collection);
    }

    @GetMapping("{collection}/{uuid}")
    public Object getOne(@PathVariable String collection, @PathVariable String uuid) {
        return DoryPercistance.instance.collections.get(collection).get(uuid);
    }

    @PostMapping("{collection}")
    public ResponseEntity<Map> postObject(@PathVariable String collection, @RequestBody Map<String, Object> body) {
        Map<String, Object> c = DoryPercistance.instance.collections.computeIfAbsent(collection, k -> new HashMap<>());
        String uuid = (String) body.get("uuid");
        if(uuid == null){
            throw new UUIDNotProvided();
        }
        c.put(uuid, body);
        return ResponseEntity.ok(body);
    }
}
package moda.peon.tools.doryapi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v0")
public class DoryRestController {

    @GetMapping
    public Map getAll() {
        return DoryPercistance.instance.collections;
    }

    @GetMapping("{collection}/{uuid}")
    public Object getOne(@PathVariable String collection, @PathVariable String uuid) {
        return DoryPercistance.instance.collections.get(collection).get(uuid);
    }

    @PostMapping("{collection}")
    public Map postObject(@PathVariable String collection, @RequestBody Map<String, Object> body) {
        Map<String, Object> c = DoryPercistance.instance.collections.computeIfAbsent(collection, k -> new HashMap<>());
        String uuid = (String) body.get("uuid");
        c.put(uuid, body);
        return body;
    }

}
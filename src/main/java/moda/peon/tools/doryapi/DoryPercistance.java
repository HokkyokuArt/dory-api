package moda.peon.tools.doryapi;

import java.util.HashMap;
import java.util.Map;

public class DoryPercistance {

    public static final DoryPercistance instance = new DoryPercistance();

    public final Map<String, Map<String, Object>> collections;

    private DoryPercistance() {
        this.collections = new HashMap<>();
    }
}
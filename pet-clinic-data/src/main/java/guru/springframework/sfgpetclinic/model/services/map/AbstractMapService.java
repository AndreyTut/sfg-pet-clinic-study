package guru.springframework.sfgpetclinic.model.services.map;

import java.util.*;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    T save(ID id, T t) {
        map.put(id, t);
        return t;
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    void delete(T t) {
        map.entrySet().removeIf(entry -> t.equals(entry.getValue()));
        //map.values().removeIf(t::equals);
    }

    void deleteById(ID id) {
        map.remove(id);
    }

}

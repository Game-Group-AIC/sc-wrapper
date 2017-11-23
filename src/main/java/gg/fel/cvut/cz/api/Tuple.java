package gg.fel.cvut.cz.api;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Optional;

@EqualsAndHashCode(of = {"first", "second"})
public class Tuple<K extends Serializable, V extends Serializable> implements Serializable {
    private final K first;
    private final V second;

    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public Optional<K> getFirst() {
        return Optional.ofNullable(first);
    }

    public Optional<V> getSecond() {
        return Optional.ofNullable(second);
    }
}

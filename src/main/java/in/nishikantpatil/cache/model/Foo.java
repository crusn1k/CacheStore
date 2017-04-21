package in.nishikantpatil.cache.model;

import in.nishikantpatil.cache.contract.Cacheable;

import java.util.UUID;

public class Foo implements Cacheable<String> {
    private final String s = UUID.randomUUID().toString();

    @Override
    public String toString() {
        return "Foo{" +
                "s=" + s +
                '}';
    }

    @Override
    public String getKey() {
        return s;
    }
}

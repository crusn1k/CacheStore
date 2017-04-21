package in.nishikantpatil.cache.model;

import in.nishikantpatil.cache.contract.Cacheable;

public class Bar implements Cacheable<Integer> {
    private final int j = 2;

    @Override
    public String toString() {
        return "Bar{" +
                "j=" + j +
                '}';
    }

    @Override
    public Integer getKey() {
        return j;
    }
}

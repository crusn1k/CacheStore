package in.nishikantpatil.cache.contract;

import java.io.Serializable;

public interface Cacheable<Key> extends Serializable {
    Key getKey();
}

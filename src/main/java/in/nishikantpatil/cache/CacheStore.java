package in.nishikantpatil.cache;

import in.nishikantpatil.cache.contract.Cacheable;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class CacheStore {
    private static final ConcurrentMap<Class<? extends Cacheable>, ConcurrentMap<Serializable, Cacheable<? extends Serializable>>> store = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Class<? extends Cacheable<? extends Serializable>>, Long> sequenceMap = new ConcurrentHashMap<>();

    public static long getNextSequenceNumber(Class<? extends Cacheable<? extends Serializable>> clazz) {
        sequenceMap.putIfAbsent(clazz, Long.MIN_VALUE);
        sequenceMap.put(clazz, sequenceMap.get(clazz) + 1);
        return sequenceMap.get(clazz);
    }

    public static Cacheable<? extends Serializable> put(Cacheable<? extends Serializable> item) {
        if (null == item) {
            throw new IllegalArgumentException("Item cannot be null!");
        }
        store.putIfAbsent(item.getClass(), new ConcurrentHashMap<>());
        return store.get(item.getClass()).put(item.getKey(), item);
    }

    public static Cacheable<? extends Serializable> get(Serializable key, Class<? extends Cacheable<? extends Serializable>> clazz) {
        if (store.containsKey(clazz) && store.get(clazz).containsKey(key)) {
            return store.get(clazz).get(key);
        }
        return null;
    }

    public static void dump() {
        store.forEach((key, value) -> {
            System.out.printf("---------------------------%s---------------------------\n", key);
            value.values().forEach(System.out::println);
        });
    }
}

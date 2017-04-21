package in.nishikantpatil.cache;

import in.nishikantpatil.cache.contract.Cacheable;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class CacheStore {
    private static final ConcurrentMap<Class<? extends Cacheable>, ConcurrentMap<Serializable, Cacheable<? extends Serializable>>> store = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Class<? extends Cacheable<Serializable>>, Long> sequenceMap = new ConcurrentHashMap<>();

    public static <T extends Cacheable<Serializable>> long getNextSequenceNumber(Class<T> clazz) {
        sequenceMap.putIfAbsent(clazz, Long.MIN_VALUE);
        sequenceMap.put(clazz, sequenceMap.get(clazz) + 1);
        return sequenceMap.get(clazz);
    }

    public static <T extends Cacheable<? extends Serializable>> T put(T item) {
        if (null == item) {
            throw new IllegalArgumentException("Item cannot be null!");
        }
        store.putIfAbsent(item.getClass(), new ConcurrentHashMap<>());
        store.get(item.getClass()).put(item.getKey(), item);
        return item;
    }

    public static <T extends Cacheable<? extends Serializable>> T get(Serializable key, Class<T> clazz) {
        if (store.containsKey(clazz) && store.get(clazz).containsKey(key)) {
            return (T) store.get(clazz).get(key);
        }
        return null;
    }

    public static void dump() {
        store.entrySet().forEach(storeEntry -> {
            System.out.printf("---------------------------%s---------------------------\n", storeEntry.getKey());
            storeEntry.getValue().values().forEach(System.out::println);
        });
    }
}

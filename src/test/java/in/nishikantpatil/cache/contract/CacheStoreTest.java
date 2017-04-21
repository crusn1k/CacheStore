package in.nishikantpatil.cache.contract;

import in.nishikantpatil.cache.CacheStore;
import in.nishikantpatil.cache.model.Bar;
import in.nishikantpatil.cache.model.Foo;
import org.junit.Assert;
import org.junit.Test;

public class CacheStoreTest {
    @Test
    public void testStorePutAndGet() {
        Foo foo = new Foo();
        Bar bar = new Bar();
        CacheStore.put(foo);
        CacheStore.put(bar);
        Assert.assertEquals(foo, CacheStore.get(foo.getKey(), Foo.class));
        Assert.assertEquals(bar, CacheStore.get(bar.getKey(), Bar.class));
    }

    @Test
    public void dumpTest() {
        Foo foo1 = new Foo();
        Foo foo2 = new Foo();
        Bar bar = new Bar();
        CacheStore.put(foo1);
        CacheStore.put(foo2);
        CacheStore.put(bar);
        CacheStore.dump();
    }
}



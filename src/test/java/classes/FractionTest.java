package classes;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void Cache() throws NoSuchFieldException, IllegalAccessException {
        Fraction fraction = new Fraction(20,40);

        ProxyFractionable fractionable = new ProxyFractionable(fraction);

        Fractionable pFractionable = Utils.cache(fraction, fractionable);

        pFractionable.doubleValue();

        Class aClass = fractionable.getClass();

        Field field = aClass.getDeclaredField("cache");
        field.setAccessible(true);

        Double resultCache = (Double) field.get(fractionable);
        System.out.println("Field Cache = " + resultCache);
        assertEquals(fraction.doubleValue(), resultCache);
    }

    @Test
    void resetCache() throws NoSuchFieldException, IllegalAccessException {
        Fraction fraction = new Fraction(20,40);

        ProxyFractionable fractionable = new ProxyFractionable(fraction);

        Fractionable pFractionable = Utils.cache(fraction, fractionable);

        pFractionable.doubleValue();

        Class aClass = fractionable.getClass();

        Field field = aClass.getDeclaredField("cache");
        field.setAccessible(true);

        Double resultCache = (Double) field.get(fractionable);
        assertNotEquals(null, resultCache);

        pFractionable.setNum(10);

        resultCache = (Double) field.get(fractionable);
        assertEquals(null, resultCache);

        pFractionable.doubleValue();

        resultCache = (Double) field.get(fractionable);
        assertNotEquals(null, resultCache);

        pFractionable.setDenum(50);

        resultCache = (Double) field.get(fractionable);
        assertEquals(null, resultCache);
    }
}
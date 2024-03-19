package classes;

import annotations.Cache;
import annotations.Mutator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFractionable implements InvocationHandler {

    private Fractionable fractionable;
    private Double cache = null;

    public ProxyFractionable(Fractionable fractionable) {
        this.fractionable = fractionable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method m = fractionable.getClass().getMethod(method.getName(),method.getParameterTypes());

        if (m.isAnnotationPresent(Cache.class) && cache == null) {
            System.out.println("cached!");
            cache = (Double) method.invoke(fractionable, args);
            return cache;
        }
        else if (m.isAnnotationPresent(Cache.class) && cache != null)
        {
            return cache;
        }

        if (m.isAnnotationPresent(Mutator.class)) {
            System.out.println("reset cache!");
            cache = null;
            return method.invoke(fractionable, args);
        }

        return method.invoke(fractionable, args);
    }
}

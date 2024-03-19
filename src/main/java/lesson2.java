import classes.Fraction;
import classes.Fractionable;
import classes.ProxyFractionable;
import classes.Utils;

public class lesson2 {

    public static void main(String[] args) {
        Fraction fraction = new Fraction(10,20);

        Fractionable pFractionable = Utils.cache(fraction, new ProxyFractionable(fraction));

        pFractionable.doubleValue();
        pFractionable.doubleValue();
        pFractionable.doubleValue();
        pFractionable.setNum(20);
        pFractionable.doubleValue();

    }
}

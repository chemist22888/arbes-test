import com.arbes.telephone.service.TelephoneBillCalculator;
import com.arbes.telephone.service.TelephoneBillCalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

public class TelephoneBillCalculatorTest {
    @Test
    public void simpleTestWithCheapPrice() {
        TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();
        double res = calculator.calculate("12,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                "122,18-01-2020 08:59:20,18-01-2020 09:10:00").doubleValue();
        Assert.assertEquals(3, res, 0.01);

    }

    @Test
    public void simpleTestWithoutCheapPrice() {
        TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();
        double res = calculator.calculate("1,13-01-2020 18:10:15,13-01-2020 18:11:57\n" +
                "12,18-01-2020 08:59:20,18-01-2020 09:01:00").doubleValue();
        Assert.assertEquals(2, res, 0.01);

    }

    @Test
    public void testEmpty() {
        TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();
        double res = calculator.calculate("").doubleValue();
        Assert.assertEquals(0, res, 0.01);
    }
}

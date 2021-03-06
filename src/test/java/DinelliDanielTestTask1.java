//package cm;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DinelliDanielTestTask1 {

    Rate rate;
    ArrayList<Period> reducedPeriods;
    ArrayList<Period> normalPeriods;

    @Test
    @DisplayName("normal rate < 0 it will return IllegalArgumentException")
    void testCase1(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,15));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,11));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Staff(),new BigDecimal(-1),new BigDecimal(0),reducedPeriods,normalPeriods));
        Assertions.assertEquals("A rate cannot be negative", exception.getMessage());
    }
    @Test
    @DisplayName("normalRate < reducedRate it will return IllegalArgumentException")
    void testCase2(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,16));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,10));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Staff(),new BigDecimal(2),new BigDecimal(3),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The normal rate cannot be less or equal to the reduced rate", exception.getMessage());
    }
    @Test
    @DisplayName("reducedRate < 0 it will return IllegalArgumentException")
    void testCase3(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(12,18));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,12));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Staff(),new BigDecimal(0),new BigDecimal(-1),reducedPeriods,normalPeriods));
        Assertions.assertEquals("A rate cannot be negative", exception.getMessage());
    }
    @Test
    @DisplayName("overlap between normalPeriods and reducedPeriods")
    void testCase4(){/////
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,13));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(12,16));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Student(),new BigDecimal(50),new BigDecimal(40),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The periods overlaps", exception.getMessage());
    }
    @Test
    @DisplayName("reducedRate && normalRate == 0 both lower boundary values")
    void testCase5(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(12,14));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,12));
        }};
        rate = new Rate(new Management(),new BigDecimal(0),new BigDecimal(0),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(0);
        Assertions.assertEquals(expected,rate.calculate(new Period(12,13)));
    }
    @Test
    @DisplayName("check charge rate if start of stay is in free hours it should return 360")
    void testCase6(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,10));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(10,16));
        }};
        rate = new Rate(new Staff(),new BigDecimal(55),new BigDecimal(50),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(16);
        Assertions.assertEquals(expected,rate.calculate(new Period(5,15)));
    }
    @Test
    @DisplayName("check valid charge rules")
    void testCase7(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,10));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(10,14));
        }};
        rate = new Rate(new Staff(),new BigDecimal(45),new BigDecimal(40),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(16);
        Assertions.assertEquals(expected,rate.calculate(new Period(9,14)));
    }
    @Test
    @DisplayName("period of stay is in normal hours only")
    void testCase8(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(15,20));
            add(1,new Period(20,23));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,15));
        }};
        rate = new Rate(new Management(),new BigDecimal(5),new BigDecimal(2),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(30);
        Assertions.assertEquals(expected,rate.calculate(new Period(15,21)));
    }
    @Test
    @DisplayName("period of stay in reduced hours only")
    void testCase9(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(16,20));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,12));
            add(1,new Period(12,15));
        }};
        rate = new Rate(new Visitor(),new BigDecimal(10),new BigDecimal(4),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(5);
        Assertions.assertEquals(expected,rate.calculate(new Period(9,14)));
    }
    @Test
    @DisplayName("overlap of periods in reduced periods")
    void testCase10(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(12,15));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,10));
            add(1,new Period(9,11));
        }};
        //setUp(3,1,normalPeriods,reducedPeriods,CarParkKind.STUDENT);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Student(),new BigDecimal(3),new BigDecimal(1),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The periods are not valid individually", exception.getMessage());
    }
    @Test
    @DisplayName("overlap of periods in normal periods")
    void testCase11(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(8,10));
            add(1,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(12,15));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Visitor(),new BigDecimal(6),new BigDecimal(3),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The periods are not valid individually", exception.getMessage());
    }
    @Test
    @DisplayName("normalRate > reducedRate")
    void testCase12(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,9));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,24));
        }};
        rate = new Rate(new Student(),new BigDecimal(2),new BigDecimal(1),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(2);
        Assertions.assertEquals(expected,rate.calculate(new Period(7,8)));
    }
    @Test//new test
    @DisplayName("reducedPeriods == null")
    void testCase13(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,9));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Management(),new BigDecimal(2),new BigDecimal(1),null,normalPeriods));
        Assertions.assertEquals("periods cannot be null", exception.getMessage());
    }
    @Test
    @DisplayName("normalPeriods == null")
    void testCase14(){
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,24));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Management(),new BigDecimal(2),new BigDecimal(1),reducedPeriods,null));
        Assertions.assertEquals("periods cannot be null", exception.getMessage());
    }
    @Test
    @DisplayName("normalRate == null")
    void testCase15(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,9));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,24));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Student(),null,new BigDecimal(1),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The rates cannot be null", exception.getMessage());
    }
    @Test
    @DisplayName("reducedRate == null")
    void testCase16(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(7,9));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,24));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Visitor(),new BigDecimal(2),null,reducedPeriods,normalPeriods));
        Assertions.assertEquals("The rates cannot be null", exception.getMessage());
    }
    @Test
    @DisplayName("set three normal overlapping periods and one reduced period that doesn't overlap")
    void testCase17(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(0,3));
            add(1,new Period(1,4));
            add(2,new Period(2,3));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,10));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Student(),new BigDecimal(2),new BigDecimal(1),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The periods are not valid individually", exception.getMessage());
    }
    @Test
    @DisplayName("two reduced periods two normal periods overlap between first normal and first reduced period")
    void testCase18(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,12));
            add(1,new Period(20,21));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,12));
            add(1,new Period(12,13));
        }};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                rate = new Rate(new Student(),new BigDecimal(2),new BigDecimal(1),reducedPeriods,normalPeriods));
        Assertions.assertEquals("The periods overlaps", exception.getMessage());
    }
    @Test
    @DisplayName("Check Visitor reduction over 10")
    void visitorReductionOver(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Visitor(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(3);
        BigDecimal result =  rate.calculate(new Period(9,13));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check Visitor reduction under 10")
    void visitorReductionUnder(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Visitor(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(0);
        BigDecimal result =  rate.calculate(new Period(9,10));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check management minimum payable is 4.00")
    void managementReductionUnder(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Management(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(4);
        BigDecimal result =  rate.calculate(new Period(11,12));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check management minimum payable is over 4.00")
    void managementReductionOver(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Management(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(13);
        BigDecimal result =  rate.calculate(new Period(9,12));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check student reduction above 5.50")
    void studentReductionUnder(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Student(),new BigDecimal(5.50),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(7);
        BigDecimal result =  rate.calculate(new Period(10,13));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check student reduction is 5.50")
    void studentReductionOver(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,13));
        }};
        rate = new Rate(new Student(),new BigDecimal(5),new BigDecimal(0.50),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(5.50);
        BigDecimal result =  rate.calculate(new Period(10,12));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check staff amount above 16")
    void staffReductionOver(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,14));
        }};
        rate = new Rate(new Staff(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(16);
        BigDecimal result =  rate.calculate(new Period(9,14));
        Assertions.assertEquals(expected,result);
    }
    @Test
    @DisplayName("Check staff amount below 16")
    void staffReductionUnder(){
        normalPeriods = new ArrayList<Period>() {{
            add(0,new Period(9,11));
        }};
        reducedPeriods = new ArrayList<Period>() {{
            add(0,new Period(11,14));
        }};
        rate = new Rate(new Staff(),new BigDecimal(5),new BigDecimal(3),reducedPeriods,normalPeriods);
        BigDecimal expected = new BigDecimal(11);
        BigDecimal result =  rate.calculate(new Period(10,13));
        Assertions.assertEquals(expected,result);
    }
    @AfterEach
    void tearThis(){
        rate = null;
        normalPeriods = null;
        reducedPeriods = null;
    }
}
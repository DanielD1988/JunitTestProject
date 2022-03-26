import java.math.BigDecimal;

public class Visitor implements RateReduction {

    public Visitor() {

    }
    //visitor first 10.00 is free, 50% reduction above that - 10.00 from price than reduction of 50
    @Override
    public double calculateReduction(double passedAmount) {
        if(passedAmount > 10.00){
            double totalPayableAmount =  passedAmount - 10.00;
            double fiftyPercentReduction = totalPayableAmount / 2;
            return fiftyPercentReduction;
        }
        return 0;
    }
}


import java.math.BigDecimal;

public class Staff implements RateReduction {
    public Staff(){

    }

    @Override
    public double calculateReduction(double passedAmount) {
        if(passedAmount > 16){
            return 16;
        }
        return passedAmount;
    }
}

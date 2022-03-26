import java.math.BigDecimal;

public class Management implements RateReduction {

    public Management() {

    }
    //MANAGEMENT: minimum payable is 4.00 >= 4.00
    @Override
    public double calculateReduction(double passedAmount) {
        if(passedAmount >= 1 && passedAmount <= 4){
            return 4;
        }
        return passedAmount;
    }
}

import java.math.BigDecimal;

public class Student implements RateReduction {

    public Student() {

    }
    //STUDENT: 25% reduction on any amount above 5.50
    @Override
    public double calculateReduction(double passedAmount) {
        if(passedAmount > 5.50){
            double removeAmount = passedAmount - 5.50;
            double twentyFivePercentReduction = removeAmount / 4;
            return 5.50 + twentyFivePercentReduction;
        }
        return passedAmount;
    }
}

import java.util.concurrent.TimeUnit;

public class TimeUnitUtils {
    public static TimeUnit stringToTimeUnit(String timeUnitString){
        try{
            return TimeUnit.valueOf(timeUnitString);
        }catch(IllegalArgumentException e){
            return TimeUnit.SECONDS;
        }
    }
}

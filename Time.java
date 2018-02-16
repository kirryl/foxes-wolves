
/**
 * Write a description of class Time here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Time
{
    // instance variables - replace the example below with your own
    private int dayTicks;
    private String dayOrNight;
    private int countsInToday;
    
    

    /**
     * Constructor for objects of class Time
     */
    public Time()
    {
        dayTicks = 100;
        dayOrNight = "day";
        countsInToday = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String checkDayNight(int currentCounter)
    {
        countsInToday = currentCounter % dayTicks;
        if (countsInToday <= (dayTicks/2)) {
            return "day";
        }
        else {
            return "night";
        }
        
    }
}

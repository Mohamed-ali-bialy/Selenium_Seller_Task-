import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

public class TimeFormatter {
    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        int minutes = currentTime.getMinute();
        int seconds = currentTime.getSecond();
        return String.format("%02d"+"-"+"%02d", minutes, seconds);
    }
}
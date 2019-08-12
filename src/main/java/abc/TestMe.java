package abc;

#define MY_API_2

#if MY_API_1
import java.util.Date;
#else
import abc.api.NewInterface;
import java.time.LocalDateTime;
#endif

public class TestMe #if MY_API_2 implements NewInterface #endif {
#if MY_API_1
  /** Version 1 supports {@code Date} */
  public void setDate(Date date) { System.out.println(date); }
#elif MY_API_2
  /** Version 2 supports {@code LocalDateTime} via interface */
  @Override
  public void setDate(LocalDateTime date) { System.out.println(date); }
#else
  #error "Missing or invalid API target"
#endif
}
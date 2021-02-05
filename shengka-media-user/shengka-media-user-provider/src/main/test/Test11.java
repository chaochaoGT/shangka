import com.geek.shengka.user.utils.CommonUtil;
import org.junit.Test;

import java.time.Instant;

/**
 * @author qubianzhong
 * @date 2019/8/13 16:57
 */
public class Test11 {

    @Test
    public void test1() {
        String skCode = String.valueOf(Instant.now().toEpochMilli()).substring(4) + CommonUtil.getRandomNumber(5);
        System.err.println(skCode);
    }
}



/*import static org.junit.jupiter.api.Assertions.*;*/

import com.yava.util.SimHashUtil;
import org.junit.jupiter.api.Test;

// 核心类测试
class SimHashUtilTest {

    @Test
    void getHash() {
        System.out.println(SimHashUtil.getHash("213123"));
    }

   @Test
    void getSimHash() {
       System.out.println(SimHashUtil.getSimHash("124123123"));
    }

    @Test
    void getHammingDistance() {
    }

    @Test
    void getSimilarity() {
        String simHash1 = SimHashUtil.getSimHash("hasdoihasiodhoiasd");
        String simHash2 = SimHashUtil.getSimHash("hasdoihasiodhoiasd");
        System.out.println(SimHashUtil.getSimilarity(simHash1,simHash2));
    }
}
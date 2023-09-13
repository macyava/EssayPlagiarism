import com.yava.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

//工具类测试
class FileUtilTest {

    @Test
    void readFile() throws IOException {
        FileUtil.readFile("D:\\test.txt");
    }

    @Test
    void writeFile() throws IOException {
        FileUtil.writeFile("dshjc");
    }
}
package fun.puppet17.earendil;

import fun.puppet17.earendil.domain.ocr.OcrService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class EarendilApplicationTests {
    
    @Test
    void contextLoads() {
    }
    
    @Resource
    private OcrService ocrService;
    
    @Test
    public void testOcr() {
        String url = "https://s3.bmp.ovh/imgs/2024/11/04/c5d03364472b1346.jpg";
        
        System.out.println(ocrService.getOcrResult(url));
    }
}

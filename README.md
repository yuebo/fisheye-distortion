鱼眼图片矫正算法
-----------------------
```java

package com.example.pic.fisheye;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
class FisheyeApplicationTests {

    @Test
    void contextLoads()  throws Exception{
        FisheyeImage fisheyeImage = new FisheyeImage("y.jpg");
        fisheyeImage.distortion(new FileOutputStream("result.jpg"));
    }
}


```
## 修正前
![鱼眼图片](y.jpg)
## 修正后
![修正图片](result.jpg)
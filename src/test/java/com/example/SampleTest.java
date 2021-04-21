package com.example;

import com.example.dao.UserMapper;
import com.example.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.cert.X509Certificate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void  Test() throws IOException {
        String jsonStr = "";
        ClassPathResource classPathResource = new ClassPathResource("static/test.txt");
        InputStream inputStream = classPathResource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        int ch = 0;
        while ((ch = bufferedReader.read()) != -1) {
            sb.append((char) ch);
        }
        bufferedReader.close();
        jsonStr = sb.toString();
        System.out.println("jsonStr = " + jsonStr);
    }

    @Test
    public void Test2() throws IOException {
        String imgPath = "D://2.jpg";
        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
        int width = image.getWidth();
        System.out.println("width = " + width);
        int height = image.getHeight();
        System.out.println("height = " + height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                System.out.println("x="+i+", y="+j+":rgb = " + rgb);
            }
        }

    }

    @Test
    public  void  Test3(){
        double f1 = 1.3d;
        double f2 = 1.1d;
        double result = f2-f1;
        if (result==0.2f){
            System.out.println("result = " + result);
        }else {
            System.out.println(result);
        }
        System.out.println(0.5==0.5f);
        System.out.println(0.1==0.1f);

        if(Math.abs(1.3d - 1.3d) < 0.00000001d){
            System.out.println("true = " + true);
        }
    }

}
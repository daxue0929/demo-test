package com.test.demotest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * @author daxue0929
 * @date 2022/2/15
 */

@RestController
public class TestController {



    @GetMapping("/test")
    public String test() throws IOException {
        //try (CloseableHttpClient httpclient = HttpClients.createDefault()){
        //    HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        //
        //    try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
        //        System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
        //        HttpEntity entity1 = response1.getEntity();
        //        // do something useful with the response body
        //        // and ensure it is fully consumed
        //        EntityUtils.consume(entity1);
        //    }
        //}
        return "current this is" + new Date().getTime() + "----------";
    }
}

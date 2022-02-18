package com.test.demotest;

import com.test.demotest.client.RestClientImpl;
import static com.test.demotest.client.RestClient.jsonHeader;
import static com.test.demotest.client.RestClientImpl.genRequestUri;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTestApplicationTests {

    @Autowired
    TestService service;

    @Autowired
    RestClientImpl restClient;

    //@Test
    public void contextLoads() throws Exception {

        URI uri = genRequestUri("https://demo123-admin.cig.tencentcs.com/contacts/api/v1/users");
        HttpHeaders httpHeaders = jsonHeader();
        httpHeaders.add("Cookie", "access_token=eyJraWQiOiJlNmIyMGVhN2JlYjIxZjQzOTY5N2E0YWFmZjQyN2UxZDRmNjI4ZTQwIiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiYWNjb3VudF90eXBlIjoidXNlckFjY291bnQiLCJ1c2VyX2lkIjoidXMtMjRhMjExZjExZWIxNDZlZWIzZTNlOGUwNDI0YjdhNGYiLCJwZXJtaXNzaW9ucyI6WyJ1c2VyOmVkaXQiLCJ1c2VyOnJlYWQiLCJhZG1pbiJdLCJ0bnRfaWQiOiJ0bi0wY2JhM2IzZDAyNGQ0ODE1YWU2MGJjMjczNWQ3ZWJjYiIsInNjb3BlIjoiYWRtaW4iLCJpc3MiOiJhZG1pbiIsImV4cCI6MTY0NTAxMDEwOSwiaWF0IjoxNjQ1MDA5NTA5LCJqdGkiOiI3MjI3Mzg5Yy0zYzk5LTRlODItOGFmZS1lMjUwMDhlZWYzMTQifQ.NwmCpKMlc8z1guZRgENbaPvEWQ0XeOZkKPDaWVjWtE9HrjqvTvzAJzgGdFuAFtL3Nb6W0WRabTIml7fwQhAVYn5o2fXvu-YZ9KBhsJzZYav-HuGllPSBOM19upuauqi3QBcc5SR4l9qR8HSNlVrNKchgWKXFfn257miXtbaSIT2aawO5r-M3FT4jOSUSZQeJFJ7UD0vdwPNhDYL6gPapUiMYC_sLjuWrm1ErcVcOXT3d6FTKrDz5XgtA64JlRtNBuHPQCW7Jt7cjtkiX94Ts1uxDtbZOmgwRkN6pq540nq4xSDB8kMp8Wcj2YZhYuIBR9gpSMm3AGevIGr52sg4AsA");
        ResponseEntity<Map<String, Object>> responseEntity = restClient.get(uri, httpHeaders);
        Assert.assertNotEquals(null, uri);

    }


}

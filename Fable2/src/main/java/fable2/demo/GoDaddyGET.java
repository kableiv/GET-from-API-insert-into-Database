package fable2.demo;

import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import net.minidev.json.JSONArray;
import com.jayway.jsonpath.DocumentContext;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GoDaddyGET {

    @Qualifier("datasource")
    @Autowired
    DataSource dataSource;


    Properties prop = new Properties();


    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\nerdi\\FableMedia\\src\\main\\java\\files\\env.properties");
        prop.load(fis);

        //prop.getProperty("HOST");
    }


    public void Test() throws ParseException {

        // write your code here

        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("GODADDYHOST");

        Response res = given().
                header("Authorization", prop.getProperty("GODADDYKEY")).log().all().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                param("includes", "contacts").
                when().
                get(Resources.godaddyGetData()).
                then().assertThat().statusCode(200).
                extract().response();







        JsonPath js = ReusableMethods.rawToJson(res);


        int count = js.get("array.size()");

        for (int i = 0; i < count; i++){

            String domainName = js.getString("domain["+i+"]");
            int domainId = js.getInt("domainId["+i+"]");
            String status = js.getString("status["+i+"]");
            String renewAuto = js.getString("renewAuto["+i+"]");


            ArrayList<String> expireArray = new ArrayList<String>();
            String expiresSource = js.getString("expires["+i+"]");
            expireArray.add(expiresSource);

            ArrayList<String> createdArray = new ArrayList<String>();
            String createdAtSource = js.getString("createdAt["+i+"]");
            createdArray.add(createdAtSource);


            String expiresFinal = "";
            String createdFinal = "";


            System.out.println(domainName);
            System.out.println(domainId);
            System.out.println(status);

            for (String dateStr : expireArray){
                if (dateStr != null){

                    LocalDateTime parse = LocalDateTime.parse(expiresSource, DateTimeFormatter.ISO_DATE_TIME);
                    expiresFinal = parse.format(DateTimeFormatter.ofPattern("dd MM yyyy hha"));
                    System.out.println(expiresFinal);
                }
            }

            System.out.println("Auto renew = " + renewAuto);

            for (String dateStr : createdArray){
                if (dateStr != null){

                    LocalDateTime parse2 = LocalDateTime.parse(createdAtSource, DateTimeFormatter.ISO_DATE_TIME);
                    createdFinal = parse2.format(DateTimeFormatter.ofPattern("dd MM yyyy hha"));
                    System.out.println(createdFinal);
                }
            }


            ReusableMethods.insertData(domainId, domainName, status, expiresFinal, renewAuto, createdFinal, "Godaddy" );

        }
        System.out.println(count);







    }
}





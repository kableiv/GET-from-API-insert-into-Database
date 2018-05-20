package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReusableMethods {


    public static XmlPath rawToXML(Response res){

        String responseString = res.asString();
        XmlPath x = new XmlPath(responseString);

        return x;
    }


    public static JsonPath rawToJson(Response res){

        String responseString = res.asString();
        JsonPath js = new JsonPath(responseString);

        return js;
    }

    public static void insertData(int domainIdd,  String domainNamee, String statuss, String expiress, String autoRenew, String createdAtt, String domainProviderr){

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("INSERT INTO domain(domainId, domainName, status, expires, renewAuto, createdAt, domainProvider)" + "VALUES (?,?,?,?,?,?,?) domainIdd, domainNamee, statuss, expiress, autoRenew, createdAtt, domainProviderr ");
    }

}

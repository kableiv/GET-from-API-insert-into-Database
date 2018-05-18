package domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int domainId;

    private String domainName;
    private String status;
    private String expires;
    private String renewAuto;
    private String createdAt;
    private String domainProvider;


    public Order() {
    }

    public Order(int domainId, String domainName, String status, String expires, String autoRenew, String createdAt, String domainProvider) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.status = status;
        this.expires = expires;
        this.renewAuto = autoRenew;
        this.createdAt = createdAt;
        this.domainProvider = domainProvider;
    }

    public int getDomainId() {
        return domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getStatus() {
        return status;
    }

    public String getExpires() {
        return expires;
    }

    public String getRenewAuto() {
        return renewAuto;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public void setRenewAuto(String renewAuto) {
        this.renewAuto = renewAuto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDomainProvider() {
        return domainProvider;
    }

    public void setDomainProvider(String domainProvider) {
        this.domainProvider = domainProvider;
    }

    @Override
    public String toString() {
        return "Order{" +
                "domainId=" + domainId +
                ", domainName='" + domainName + '\'' +
                ", status='" + status + '\'' +
                ", expires='" + expires + '\'' +
                ", renewAuto='" + renewAuto + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", domainProvider='" + domainProvider + '\'' +
                '}';
    }
}
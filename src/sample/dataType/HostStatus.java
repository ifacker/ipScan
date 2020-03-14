package sample.dataType;

/**
 * @Description: IP是否存活$
 * @Author: ifacker
 * @Date: $
 */
public class HostStatus {
    private Boolean status;
    private String host;

    public void setStatus(Boolean status){
        this.status = status;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getHost() {
        return host;
    }
}

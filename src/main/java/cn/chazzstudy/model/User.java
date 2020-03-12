package cn.chazzstudy.model;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.12 15:28
 */
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gtmCreate;
    private long gtmModified;

    public User() {
    }

    public User(Integer id, String name, String accountId, String token, long gtmCreate, long gtmModified) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.token = token;
        this.gtmCreate = gtmCreate;
        this.gtmModified = gtmModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(long gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public long getGtmModified() {
        return gtmModified;
    }

    public void setGtmModified(long gtmModified) {
        this.gtmModified = gtmModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", token='" + token + '\'' +
                ", gtmCreate=" + gtmCreate +
                ", gtmModified=" + gtmModified +
                '}';
    }
}

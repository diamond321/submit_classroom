//添加用户实体类

/**
 * Created by xsw on 2017/6/4.
 */
public class adduserDepart {
    private String uname;
    private String id;
    private String pwd;
    private String gender;
    private String telNum;
    private String email;

    //构造方法
    public adduserDepart (){}
    public adduserDepart(String uname, String pwd, String gender, String telNum,String email){
        this.uname=uname;
        this.pwd =pwd;
        this.gender=gender;
        this.telNum=telNum;
        this.email=email;

    }
    public adduserDepart (String uname,String id,  String pwd, String gender, String telNum,String email){
        this.id=id;
        this.uname =uname;
        this.pwd =pwd;
        this.gender=gender;
        this.telNum=telNum;
        this.email=email;

    }
    public String getUname() {return uname;}
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getId() { return id; }
    public void setId (String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNum() {
        return telNum;
    }
    public void setTelNum(String telNum) {this.telNum = telNum;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

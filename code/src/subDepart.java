/**
 * Created by xsw on 2017/6/2.
 */
/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

    //user_name, user_id, telNum, email, clr_num,data_begin,data_end,duration,purpose
public class subDepart {
    private String user_name;
    private String  user_id;
    private String telNum;
    private String email;
    private String clr_num;
    private String  date_begin;
   // private Object date_end1=(String);
    //private Object duration;
    private String purpose;
    private String date_end;
    private String duration;
    //构造方法
    public subDepart (){}
    public subDepart(String user_name,String user_id,String telNum, String email, String clr_num,String date_begin,String date_end,String duration,String purpose){
        this.user_name=user_name;
        this.user_id =user_id;
        this.telNum=telNum;
        this.email=email;
        this.clr_num=clr_num;
        this.date_begin =date_begin;
        this.date_end=date_end;
        this.duration=duration;
        this.purpose=purpose;
    }
   /* public subDepart (String user_name, String user_id, String telNum, String email, String clr_num,String date_begin,String date_end1,String duration1,String purpose){
        this.user_name=user_name;
        this.user_id =user_id;
        this.telNum=telNum;
        this.email=email;
        this.clr_num=clr_num;
        this.date_begin =date_begin;
        this.date_end=date_end;
        this.duration=duration;
        this.purpose=purpose;
    }*/
    public String getUser_name() { return user_name; }
    public void setUser_name (String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String academy) {
        this.user_id = user_id;
    }

    public String getTelNum() {
        return telNum;
    }
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getClr_num() {
        return clr_num;
    }
    public void setClr_num(String clr_num) {this.clr_num = clr_num;}

    public String getDate_begin() {
        return date_begin;
    }
    public void setDate_begin(String date_begin) {
        this.date_begin = date_begin;
    }


    public String getDate_end() {
        return date_end;
    }
    public void CsetDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDuration() {
        return duration;
    }
    public void CsetDuration(String duration) {this.duration = duration;}

    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}


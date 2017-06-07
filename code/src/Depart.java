/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

public class Depart {
    private String clr_id;
    private String  academy;
    private String place;
    private String equipment;
    private String peoNum;
    //private String use_time;

    //构造方法
    public Depart (){}
    public Depart(String academy,String place,String equipment,String peoNum){
        this.academy=academy;
        this.place=place;
        this.equipment=equipment;
        this.peoNum=peoNum;
        //this.use_time=use_time;
    }
    public Depart (String clr_id,String academy,String place,String equipment,String peoNum){
        this.clr_id=clr_id;
        this.academy=academy;
        this.place=place;
        this.equipment=equipment;
        this.peoNum=peoNum;
        //this.use_time=use_time;
    }
    public String getClr_id() {
        return clr_id;
    }
    public void setClr_id (String clr_id) {
        this.clr_id = clr_id;
    }

    public String getAcademy() {
        return academy;
    }
    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getPeoNum() {
        return peoNum;
    }
    public void setPeoNum(String peoNum) {
        this.peoNum = peoNum;
    }

    /*public String getUse_time(){return use_time;}
    public void setUse_time(String use_time){this.use_time=use_time;}*/
}

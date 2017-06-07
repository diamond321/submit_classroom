
import java.math.BigInteger;
import java.security.MessageDigest;
/*
MD5(Message Digest algorithm 5，信息摘要算法)
通常我们不直接使用上述MD5加密。通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串
Digest:汇编
*/
public class MD5 {
    public static final String KEY_MD5 = "MD5";


    public static  String  getResult(String inputStr)
    {
        System.out.println("=======加密前的数据:"+inputStr);
        BigInteger bigInteger=null;

        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("MD5加密后:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }

    public static String MD5(String inputStr)
    {

            return getResult(inputStr);



    }

}

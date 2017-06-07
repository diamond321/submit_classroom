import java.awt.*;
import javax.swing.JFrame;
/**
 * Created by xsw on 2017/5/26.
 */
public class WindowUtils {
    public static void displayOnDesktopCenter(JFrame frame){
        //窗体居中方法
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension dim=toolkit.getScreenSize();
        int screenWidth=dim.width;
        int screenHeight=dim.height;
        int w=frame.getWidth();
        int h=frame.getHeight();
        int x=(screenWidth-w)/2;
        int y=(screenHeight-h)/2;
        frame.setLocation(x,y);
    }
}
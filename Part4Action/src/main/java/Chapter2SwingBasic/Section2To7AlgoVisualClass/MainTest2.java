/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 21:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section2To7AlgoVisualClass;

import java.awt.*;

public class MainTest2 {
    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;
        // 圆的半径
        int R = 50;
        Circle[] circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            // x和y方向的速度，在15——5之间
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                while (true) {
                    // 绘制
                    frame.render(circles);
                    // 暂停20ms
                    AlgoVisualHelper.pause(20);
                    // 更新圆的位置
                    for (Circle circle : circles) {
                        circle.move(0, 0, sceneWidth, sceneHeight);
                    }
                }
            }).start();
        });
    }
}

package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Bullet {
    double x, y; // 弾の座標
    double v; // 弾の速度
    boolean exist; // 弾の存在フラグ

    public Bullet() {
        v = -1.0;
        exist = false;
    }

    public void enter(Player player) {
        exist = true;
        x = player.x;
        y = player.y;
    }

    public void move() {
        y += v;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, 8, 32);
    }
}

public class Player {
    double x, y; // プレイヤーの座標
    static final int BULLET_MAX = 10; // 弾の最大数
    Bullet[] bullet = new Bullet[BULLET_MAX]; // 弾

    public Player() {
        for(int i = 0;i < BULLET_MAX;i++) { // Bulletのインスタンス化
            bullet[i] = new Bullet();
        }
    }

    public void move(byte[] key) {
        x += (key[Main.KEY_RIGHT] - key[Main.KEY_LEFT]);
        y += (key[Main.KEY_DOWN ] - key[Main.KEY_UP  ]);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED); // 赤色に設定
        gc.fillOval(x, y, 32, 32); // 自機を描画
    }
}

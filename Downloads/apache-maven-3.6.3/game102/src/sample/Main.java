package sample;


import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;

public class Main extends Application {

    static final int WIDTH = 600; // ウィンドウの幅
    static final int HEIGHT = 600; // ウィンドウの高さ
    static final Color COLOR = Color.BLACK; // ウィンドウの背景色
    double x, y; // プレイヤーの座標

    static final int KEY_RIGHT = 0;
    static final int KEY_LEFT  = 1;
    static final int KEY_UP    = 2;
    static final int KEY_DOWN  = 3;
    static final int KEY_Z     = 4;
    byte[] key = new byte[5];

    Player player; // Playerクラスのインスタンス

    @Override public void start(Stage stage) {
        Group root = new Group(); // Group を作成
        Scene scene = new Scene(root, WIDTH, HEIGHT, COLOR); // Scene を作成
        stage.setScene(scene); // Scene を設定
        stage.show(); // ウィンドウを表示

        Canvas canvas = new Canvas(WIDTH, HEIGHT); // Canvasを作成
        GraphicsContext gc = canvas.getGraphicsContext2D(); // GraphicsContextを取得
        root.getChildren().add(canvas); // Canvasを設置

        player = new Player(); // Playerのインスタンス化

        Thread thread = new Thread(() -> { // メインスレッド
            while(true) {
                gc.clearRect(0, 0, WIDTH, HEIGHT); // 全画面消去

                player.move(key); // プレイヤーの操作
                player.draw(gc); // プレイヤーの描画

                for(int i = 0;i < Player.BULLET_MAX;i++) {
                    if(player.bullet[i].exist) {
                        player.bullet[i].move();
                        player.bullet[i].draw(gc);
                    }
                }

                if(key[KEY_Z] == 1) { // Zキーが押されたら
                    for(int i = 0;i < Player.BULLET_MAX;i++) {
                        if(!player.bullet[i].exist) { // 存在していなかったら
                            player.bullet[i].enter(player); // 発射
                            break;
                        }
                    }
                }

                try {
                    Thread.sleep(2); // 2ms休止
                }catch(Exception e) {
                }
            }
        });
        thread.setDaemon(true); // スレッドをデーモン化
        thread.start(); // メインループを開始

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case RIGHT: key[KEY_RIGHT] = 1; break;
                case LEFT : key[KEY_LEFT ] = 1; break;
                case UP   : key[KEY_UP   ] = 1; break;
                case DOWN : key[KEY_DOWN ] = 1; break;
                case Z    : key[KEY_Z    ] = 1; break;
            }
        });
        scene.setOnKeyReleased(e -> { // キー離上処理
            switch(e.getCode()) {
                case RIGHT: key[KEY_RIGHT] = 0; break;
                case LEFT : key[KEY_LEFT ] = 0; break;
                case UP   : key[KEY_UP   ] = 0; break;
                case DOWN : key[KEY_DOWN ] = 0; break;
                case Z    : key[KEY_Z    ] = 0; break;
            }
        });
    }
}
//実験

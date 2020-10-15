package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public ImageView img = new ImageView(new File("sample3.png").toURI().toString());

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane p = new Pane();
        Scene scene = new Scene(p, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        //表示
        p.getChildren().add(img);

        //キーイベントの登録
        scene.setOnKeyPressed(e -> keyPressed(e));
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void keyPressed(KeyEvent e) {
        //上下左右キーを押した時imgの座標を移動させる。
        switch (e.getCode()) {
            case LEFT:
                //左キーを押したらxを減算させ、画像を左に移動させる
                img.setX(img.getX() - 3);
                break;
            case RIGHT:
                //右キーを押したらxを加算させ、画像を右に移動させる
                img.setX(img.getX() + 3);
                break;
            case UP:
                //上キーを押したらyを減算させ、画像を上に移動させる
                img.setY(img.getY() - 3);
                break;
            case DOWN:
                //下キーを押したらyを加算させ、画像を下に移動させる
                img.setY(img.getY() + 3);
                break;
            default:
                break;
        }
    }
}

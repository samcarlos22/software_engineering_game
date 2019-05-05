package View;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class View {


    public void launchView(Stage stage) {
        stage.show();
    }

    public void closeView(Stage stage){
        stage.close();
    }

    public void changeScene(Stage stage, Scene scene){
        stage.setScene(scene);
    }
}

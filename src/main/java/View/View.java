package View;

import javafx.scene.layout.BorderPane;


public class View extends BorderPane {

    private BorderPane view;

    public View(){
        view = new BorderPane();
    }

    public BorderPane getView() {
        return view;
    }

    public void setView(BorderPane view) {
        this.view = view;
    }

}

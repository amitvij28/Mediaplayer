package amedia;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Video_Player extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Java Video Player");
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            int flag=0;
            @Override
            public void handle(MouseEvent doubleClicked) {
                
                // found and removed fullscreen bug
                if(doubleClicked.getClickCount() == 2) {
                    if(flag==0){
                    
                    stage.setFullScreen(true);
                    flag=1;}
                    
                    else if(flag==1){
                    stage.setFullScreen(false);
                    flag=0;
                    }
                    
                 
                }
                
                
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amedia;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {
   
    private MediaPlayer mediaPlayer;
    
    
    
    
    
    
    @FXML
    private MediaView mediaView; 
    
    private String filePath;
    
    @FXML
    private Slider slider;
   
    
    
     @FXML
    private Slider seekSlider;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //found and removed bug for opening new file
        
        if(mediaPlayer!=null)
        {mediaPlayer.pause();}
        ArrayList<String> exts = new ArrayList<>();
        exts.add("*.mp4");
        exts.add("*.mp3");
        
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file(*.mp4)",exts);
        fileChooser.getExtensionFilters().add(filter);
        File file= fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if(mediaPlayer!=null)
        {mediaPlayer.dispose();}
        
        if(filePath!=null){
        Media media = new Media(filePath);
        
        
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
           DoubleProperty width =mediaView.fitWidthProperty();
           
           DoubleProperty height =mediaView.fitHeightProperty();
           
           width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
           height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            InvalidationListener ChangeListener;
           
           
           
           
           slider.setValue(mediaPlayer.getVolume()*100);
           slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(slider.getValue()/100);
                
                
            }
        });
           // found and removes bug for short slider
           mediaPlayer.setOnReady(new Runnable() {

        @Override
        public void run() {
         
seekSlider.setMin(0);
seekSlider.setMax(media.getDuration().toSeconds());
        }
    });
           
           
           
       mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        seekSlider.setValue(newValue.toSeconds());
                    }
                });
                
                    seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                        
                    }
});
      
       
        //ddw  
        mediaPlayer.play();
        
         
        }
        
    }
    
    @FXML
    private void playvideo(ActionEvent event){
    mediaPlayer.play();
    mediaPlayer.setRate(1);
    
    }
    
    @FXML
    private void pausevideo(ActionEvent event){
    mediaPlayer.pause();  
    }
    
    @FXML
    private void stopvideo(ActionEvent event){
    mediaPlayer.stop();
    }
    
    @FXML
    private void fastvideo(ActionEvent event){
    mediaPlayer.setRate(1.5);
    }
    
    @FXML
    private void fastervideo(ActionEvent event){
    mediaPlayer.setRate(2);
    }
    
    @FXML
    private void slowvideo(ActionEvent event){
    mediaPlayer.setRate(0.75);
    }
    
    @FXML
    private void slowervideo(ActionEvent event){
    mediaPlayer.setRate(0.5);
    
    }
    
    @FXML
    private void exitvideo(ActionEvent event){
    System.exit(0);
    
    }
    
  
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    
}

package imagescanner;

import java.io.*;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class ImageScanner extends Application{
    
    Text imagelabel,filelabel,filetypelabel;
    TextField imagePathTextField,filePathTextField;
    Button chooseImage,chooseFile,extract,readImage;
    String imagePath,filePath;
    String imageText;
    Slider slider;
    TextArea imageTextFile;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Image Scanner");
        // Creating Objects of Label
        imagelabel = new Text("Image Path : ");
        filelabel = new Text("Extract To : ");
        filetypelabel = new Text("Choose File Type : ");
        // Setting Properties of Label
        imagelabel.setFont(new Font("Arial",15));
        filelabel.setFont(new Font("Arial",15));
        
        
        // Creating Objects of TextField
        imagePathTextField = new TextField();
        filePathTextField = new TextField();
        // Setting properties of TextField
        imagePathTextField.setPrefColumnCount(25);
        filePathTextField.setPrefColumnCount(25);
        
        // Creating Objects of Button
        chooseImage = new Button("Choose Image");
        chooseFile = new Button("Choose File");
        extract = new Button("Extract Text");
        readImage = new Button("Read Image");
        // Setting properties of Button (Making both buttons of same size)
        chooseImage.setMinSize(100, 20);
        chooseFile.setMinSize(100, 20);
        extract.setMinSize(60,20);
        readImage.setMinSize(60,20);
        
        // Creating Slider
        slider = new Slider(10,50,10);
        // Setting Properties of Slider
        slider.setMajorTickUnit(5);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(e->imageTextFile.setFont(Font.font(slider.getValue())));
        
        imageTextFile = new TextArea();
        
        chooseImage.setOnAction(e->{
            FileChooser fc = new FileChooser();
            File image = fc.showOpenDialog(stage);
            
            imagePath = image.getPath();
            
            imagePathTextField.setText(imagePath);
        });
        
        chooseFile.setOnAction(e->{
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage);
            
            filePath = file.getPath();
            filePathTextField.setText(filePath);
        });
    
        extract.setOnAction(e->{
            
            ImageExtract img = new ImageExtract();
            imageText = img.getText(imagePath);
            
            SaveInFile sf = new SaveInFile(imageText);
            try {
                sf.saveTxt(filePath);
            } catch (IOException ex) {
                System.out.println("Error in Saving Text File !!!");
            }
            
            try
            {
                
                imageTextFile.setText(imageText);
            }
            catch(Exception ex)
            {
                imageTextFile.setText("Select Valid JPG File");
            }
        });
        
        readImage.setOnAction((e->{
            TextToSpeech tts = new TextToSpeech();
            tts.setString(imageText);
            tts.read();
        }));
        
        HBox h1 = new HBox();
        h1.getChildren().add(imagelabel);
        h1.setAlignment(Pos.CENTER);
        
        HBox h2 = new HBox();
        h2.getChildren().addAll(imagePathTextField,chooseImage);
        h2.setSpacing(8);
        h2.setAlignment(Pos.CENTER);
        
        HBox h3 = new HBox();
        h3.getChildren().add(filelabel);
        h3.setAlignment(Pos.CENTER);
        
        HBox h4 = new HBox();
        h4.getChildren().addAll(filePathTextField,chooseFile);
        h4.setSpacing(8);
        h4.setAlignment(Pos.CENTER);
        
        HBox h5 = new HBox();
        h5.getChildren().addAll(extract,readImage);
        h5.setSpacing(20);
        h5.setAlignment(Pos.CENTER);
        
        HBox h6 = new HBox();
        h6.getChildren().add(slider);
        h6.setAlignment(Pos.CENTER);
        
        HBox h7 = new HBox();
        h7.getChildren().add(imageTextFile);
        h7.setAlignment(Pos.CENTER);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        
        Scene sc = new Scene(vb,500,500);
        stage.setScene(sc);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

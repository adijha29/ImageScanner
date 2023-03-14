package imagescanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SaveInFile {
    String text;
    
    public SaveInFile(String text) {
        this.text = text;
    }
    
    boolean saveTxt(String path) throws IOException{
        File f = new File(path);
        clearTheFile(path);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(path,true);
            try {
                fout.write(32);
            } catch (IOException ex) {
                
            }
        } catch (FileNotFoundException ex) {
            
        }
        
        String arrOfStr[] = text.split(" ");
        
        for(int i=0;i<arrOfStr.length;i++){
            char ch[] = arrOfStr[i].toCharArray();
            for(int j=0;j<ch.length+1;j++){
                try {
                    if(j<ch.length){
                        fout.write(ch[j]);
                    } else{
                        fout.write(' ');
                    }
                } catch (IOException ex) {
                    
                }
            }
        }
            
        try {
            fout.close();
        } catch (IOException ex) {
            System.out.println("Some ERROR Occurred !!");
        }
        
        return true;
    }
    
    public void clearTheFile(String path) throws IOException {
        FileWriter fwOb = new FileWriter(path, false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}

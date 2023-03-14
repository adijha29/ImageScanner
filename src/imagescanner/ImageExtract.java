package imagescanner;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageExtract{
    private String text;
    public String getText(String path) {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("src/imagescanner/tessdata");
            text = tesseract.doOCR(new File(path));
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }
        
        return text;
    }
}

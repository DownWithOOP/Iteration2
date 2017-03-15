package view.utilities;

/**
 * Created by Konrad on 3/15/2017.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class FileWriter {

    private PrintWriter printWriter = null;

    public void initialize(){
        String filename = "PlayerControls.txt";
        File f = new File(filename);
        try{
            printWriter = new PrintWriter(f);
        } catch(FileNotFoundException e){

        }

    }

    public void writeData(String data){
            printWriter.write(data);
            printWriter.flush();
    }
    public void stopWriting(){
        this.printWriter.close();
    }



}

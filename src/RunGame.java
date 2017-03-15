import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by Konrad on 2/17/2017.
 */


public class RunGame extends  Application{

    private boolean WelcomeViewLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // we set the title of the stage
        primaryStage.setTitle("Space Cats!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/welcomeView.fxml"));
        String cssSheet = this.getClass().getResource("/resources/style.css").toExternalForm();
        GameLoader.load(primaryStage, loader, cssSheet);
    }
    // Starting point of the game
    public static void main(String args[]){
        // entry point of the application
        // lets create the game object
        System.out.println("Game has started running");
          launch(args); // launch the GUI
        System.out.println("we get here");
    }
}

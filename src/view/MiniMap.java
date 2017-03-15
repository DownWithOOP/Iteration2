package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.RenderInformation.TileRenderObject;
import utilities.ObserverInterfaces.MiniMapObserver;
import view.utilities.Assets;

/**
 * Created by Konrad on 3/9/2017.
 */
public class MiniMap implements MiniMapObserver{
    private Canvas canvas;

    Image border = Assets.getInstance().MINIMAPBORDER;
    Image dot =Assets.getInstance().SMALLPURPLE;

    public MiniMap(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void update(int rightX, int leftX, int rightY, int leftY, TileRenderObject[][] renderData) {

       GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.AQUA);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.drawImage(border,(0-rightX)/14.5,-leftX/14 +74);

        for(int i=0; i<renderData.length; i++){
            for(int j=0; j<renderData[0].length; j++){
                TileRenderObject check = renderData[i][j];
                if(check.getVisibilityLevel() == 2){
                    gc.drawImage(dot,(200/36)*i,(116/16)*-j+100);
                }
                if(check.getVisibilityLevel() == 1){
                    // TODO add a different image
                    gc.drawImage(dot,(200/36)*i,(116/16)*-j+100);
                }
            }
        }
    }
}

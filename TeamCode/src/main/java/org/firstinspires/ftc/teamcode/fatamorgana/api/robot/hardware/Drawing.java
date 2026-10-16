package org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware;


import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;

import com.bylazar.field.Canvas;
import com.bylazar.field.Drawable;
import com.bylazar.field.Rectangle;
import com.bylazar.field.Style;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    public static void draw(){
        Canvas canvas = new Canvas();
        List<Drawable> shapes = new ArrayList<Drawable>();
        shapes.add(
                new Rectangle(
                        drive().x(),
                        drive().y(),
                        10,
                        16,
                        new Style("blue", "white", 1.0)
                )
        );
        canvas.setItems(shapes);
    }
}

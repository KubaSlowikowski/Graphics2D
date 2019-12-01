package zetCodeTutorial.Lesson10;

//http://zetcode.com/gfx/java2d/textfonts/

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class AllFontsEx {

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();

        for (Font font : fonts) {
            System.out.print(font.getFontName() + " : ");
            System.out.println(font.getFamily());
        }
    }
}
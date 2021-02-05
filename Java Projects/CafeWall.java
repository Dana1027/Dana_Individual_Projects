

import java.awt.*;

// Dana Im
// CSE 142
// Assignment #3
// TA: Kendra Specht
public class CafeWall {
    // These codes will draw six square-row sets on a drawing panel 
    // It is consisted with black and white squares.
    // There are upper left(1 row), midleft(1 row), lower left(8 rows), lower middle(6 rows), 
    // lower right(10 rows), and upper right(4 rows). 
    // The drawing panel will be gray on the backgorund and has the size of 650 X 400 pixels.
        public static void main(String[] args) {
            DrawingPanel panel = new DrawingPanel(650, 400);
            panel.setBackground(Color.GRAY);
            Graphics g = panel.getGraphics();

            drawSquareRow(g, 0, 0, 20, 4);
            drawSquareRow(g, 50, 70, 30, 5);
            drawSquareGrid(g, 10, 150, 25, 4, 8, 0);
            drawSquareGrid(g, 250, 200, 25, 3, 6, 10);
            drawSquareGrid(g, 425,180, 20, 5, 10, 10);
            drawSquareGrid(g, 400, 20, 35, 2, 4, 35);
        }

        // this method is going to draw square pairs of black and white squares.
        // I put White square first because blue line is supposed to be drawn at the very last.
        // If the white square is drawn at the very last it hide part of the blue line and will 
        // not match with the original picture.
        public static void drawSquarePair(Graphics g, int x, int y, int size) {
            g.setColor(Color.WHITE);
            g.fillRect(x + size, y, size, size);
            g.setColor(Color.BLACK);
            g.fillRect(x, y, size, size);
            g.setColor(Color.BLUE);
            g.drawLine(x, y, x + size, y + size);
            g.drawLine(x, y + size, x + size, y);
        }

        // This method is going to produce square-rows consisted with square-pairs.
        // the intxMovement is to move the x coordinates to each given set of coordinates.
        // I added 2 * size to move two size right since this method is moving 2 squares together.
        // pair <= pair is to draw pairs up to the number passed down.
        public static void drawSquareRow(Graphics g, int x, int y, int size, int pairs) {
            int xMovement = 0;
            for(int pair = 1; pair <= pairs; pair++) {
                drawSquarePair(g, x + xMovement, y, size); 
                xMovement = xMovement + 2 * size;
            }
        }

        // This method is going to produce grids which produce square-rows downwards.
        // the intyMovement is to move the y coordinates to draw rows downwards.
        // Since offsets are different I made two lines of drawSquare to 
        // seperately draw the lines.
        // the row = row + 1; in the middle add + 1 to add row so that 
        // first and the second method draw only half the rows then the number passed down.
        // However, on the drawing panel all the rows will appear because
        // two set of rows will be added together so the number will double 
        // which will be same as the number passed down to the int rows. 
        public static void drawSquareGrid(
            Graphics g, int x, int y, int size, int pairs, int rows, int offset
        ) {
            int yMovement = 0;
            for(int row = 1; row <= rows; row++) {
                drawSquareRow(g, x, y + yMovement, size, pairs); 
                yMovement = 2 + yMovement + 1 * size;
                row = row + 1;
                drawSquareRow(g, x + offset, y + yMovement, size, pairs); 
                yMovement = 2 + yMovement + 1 * size;
            }
        }
    }

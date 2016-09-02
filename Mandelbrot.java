import java.awt.Color;
/*
 *Michael Gunn
 *HW #2
 *9/1/16
 */
public class Mandelbrot {

    private int limit;	// Maximum iterations for membership testing
    private int size; //number of pixels
    private double bailout;
    private double centerReal;
    private double centerImaginary;
    private boolean escape = false; //used to see which colorization to use
    private boolean smooth = false; //^^^
    private double width;
    private static ColorPallete pallete = ColorPallete.GRAY; //default to gray

    public static enum ColorPallete {
        BLACK,
        GRAY,
        RED, 
        GREEN,
        BLUE
    }

    public Mandelbrot(double centerReal, double centerImaginary, double bailout, int iterations, int size, String member, double width, String pallete) {
        this.limit = iterations;
        this.bailout = bailout;
        this.centerReal = centerReal;
        this.centerImaginary = centerImaginary;
        this.size = size;
        this.width = width;

        try {
            this.pallete = ColorPallete.valueOf(pallete);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Color");
        }

        switch (member) {
            case "-escape":
                this.escape = true;
                break;
            case "-smooth":
                this.smooth = true;
                break;
            case "-member": //already defaults to member, so don't need to do anything
                break;
            default:
                System.out.println("Illegal escape time version"); //anything but those 3 defaults to member
        }
    }

    // Test to see if a point in the complex plane is a member of 
    // the Mandelbrot set.  A point (x) is in the set if the sequence
    // of (complex) values z[i] = z[i-1] * z[i-1] + x does not diverge.
    // It can be proved that once a value z[i] in this sequence has a
    // modulus greater than 2.0, it will diverge.  Membership in the
    // set is approximated by checking that the sequence of the first
    // 100 elements of the series all have modulus <= 2.0\
    
    public boolean isMember(Complex x) { //basic black and white escape calculator
        Complex z = x;
        for (int i = 0; i < limit; i++) {
            if (z.modulusSquared() > 4.0) {
                return false;
            }
            z = z.square().add(x);
        }
        return true;
    }

    public int escapeTime(Complex x) {//basic escape time that returns iterations to escape
        Complex z = x;
        for (int i = 0; i < limit; i++) {
            if (z.modulusSquared() > 4.0) {
                return i; //return i when it diverges
            }
            z = z.square().add(x);
        }

        return limit;//return the limit if it didn't diverge
    }

    public double smoothEscapeTime(Complex x) {//smooth escape time algorithem
        Complex z = x;
        for (int i = 0; i < limit; i++) {
            if (z.modulusSquared() > bailout) {
                return z.modulusSquared();
            }
            z = z.square().add(x);
        }
        return z.modulusSquared();
    }

    public void draw() {
        // Dimensions of the canvas in the complex plane.
        double xMin = centerReal - width / 2;
        double xMax = centerReal + width / 2;
        double yMin = centerImaginary - width / 2;
        double yMax = centerImaginary + width / 2;
        double xRange = xMax - xMin;
        double yRange = yMax - yMin;

        // Set the bounds of the canvas in the complex plane as
        // well as the size of the canvas in pixels.
        StdDraw.setCanvasSize(size, size);
        StdDraw.setXscale(xMin, xMax);
        StdDraw.setYscale(yMin, yMax);
        StdDraw.setPenRadius(0); // One pixel wide.

        // For each pixel in the image, determine its coordinates in
        // the complex plane and check to see if it is in the Mandelbrot
        // set or not.  Paint the pixel black if it is in the set.
        for (int row = 0; row < size; row++) { //what should I replace rows/columns with?
            for (int col = 0; col < size; col++) {
                double re = xMin + xRange * (double) col / (double) size; // to fix
                double im = yMin + yRange * (double) row / (double) size; //to fix
                Complex z = new Complex(re, im);
                Color color;
                if (escape) { //colorize using normal escape time algorithm
                    int n = escapeTime(z)%255;
                    n=((limit-n)*60)%255;
                    switch (pallete){ 
                        case RED:
                            color = new Color (n,0,0);
                            break;
                        case GREEN:
                            color = new Color (0,n,0);
                            break;
                        case BLUE:
                            color = new Color (0,0,n);
                            break;
                        case GRAY:
                            color = new Color (n, n, n);
                            break;
                        case BLACK:
                            if (isMember(z)){
                                color = Color.BLACK;
                            }
                            else {
                                color = Color.WHITE;
                            }
                            break;
                        default:
                            color = new Color (0, n,0); //defaults to green
                    }
                    StdDraw.setPenColor(color);
                    StdDraw.point(re, im);
                    
                } else if (smooth) {//colorize based on smoothEscapeTime algorithm
                    int n = (int) Math.log10(smoothEscapeTime(z));
                    n = Math.abs((n * 500) % (255)); //-j*3
                    switch (pallete){ 
                        case RED:
                            color = new Color (n,0,0);
                            break;
                        case GREEN:
                            color = new Color (0,n,0);
                            break;
                        case BLUE:
                            color = new Color (0,0,n);
                            break;
                        case BLACK:
                            if (isMember(z)){
                                color = Color.BLACK;
                            }
                            else {
                                color = Color.WHITE;
                            }
                            break;
                        default:
                            color = new Color (n,n,n); //defaults to gray 
                    }
                    StdDraw.setPenColor(color);
                    StdDraw.point(re, im);

                } else { //when "-member" it is always in black and white
                    if (isMember(z)) {//default case uses standard black and white colorization
                        StdDraw.point(re, im);
                    }
                }
            }
        }
    }
}

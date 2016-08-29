
import java.awt.Color;


public class Mandelbrot {
    private int rows = 100;
    private int columns = 150; //testing
    
    
    private int limit = 255;	// Maximum iterations for membership testing
    private int size = 150; //number of pixels
    private double bailout = 1E20;
    private double centerReal = 0.0;
    private double centerImaginary = 0.0;
    private boolean member = false;
    private boolean escape = false; //can replace all 3 with an enum
    private boolean smooth = false;
    private double width = 4.0;
    

    public Mandelbrot(double centerReal, double centerImaginary, double bailout, int iterations, int size, String member, double width) {
        this.limit = iterations;
        this.bailout = bailout;
        this.centerReal = centerReal;
        this.centerImaginary = centerImaginary;
        this.size = size;
        
        switch (member){
            case "member":
                this.member = true;
                break;
            case "escape":
                this.escape = true;
                break;
            case "smooth":
                this.smooth = true;
                break;
        }    
    }

    // Test to see if a point in the complex plane is a member of 
    // the Mandelbrot set.  A point (x) is in the set if the sequence
    // of (complex) values z[i] = z[i-1] * z[i-1] + x does not diverge.
    // It can be proved that once a value z[i] in this sequence has a
    // modulus greater than 2.0, it will diverge.  Membership in the
    // set is approximated by checking that the sequence of the first
    // 100 elements of the series all have modulus <= 2.0\
    public boolean isMember(Complex x){ //basic black and white escape calculator
        Complex z = x;
        for (int i=0; i<limit; i++){
            if (z.modulusSquared() > 4.0){
                return false;
            }
            z = z.square().add(x);
        }
        return true;
    }
    
    public int escapeTime(Complex x) {//basic escape time that returns i. Creates banding
        Complex z = x;
        for (int i = 0; i < limit; i++) {
            if (z.modulusSquared() > 4.0) {
                return i; //return i when it diverges
            }
            z = z.square().add(x);
        }

        return limit;//return the limit if it didn't diverge
    }

    public double smoothEscapeTime(Complex x) {//smooth escape time algorithem, no banding
        Complex z = x;
        for (int i=0; i<limit; i++){
            if (z.modulusSquared()<bailout){
                return z.modulusSquared();
            }
            z=z.square().add(x);
        }
        return z.modulusSquared();
    }
    public void draw() {
        // Dimensions of the canvas in the complex plane.
//        double xMin = centerReal - width/2;
//        double xMax = centerReal + width/2; //figure out what this is supposed to be
//        double yMin = centerImaginary - width/2;
//        double yMax = centerImaginary + width/2;
        double xMin = centerReal-width/2;
        double xMax = centerReal+width/2;
        double yMin = centerImaginary-width/2;
        double yMax = centerImaginary+width/2;
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
            for (int col = 0; col < size ; col++) {
                double re = xMin + xRange * (double) col / (double) size; // to fix
                double im = yMin + yRange * (double) row / (double) size; //to fix
                Complex z = new Complex(re, im);

                
                if (escape){
                    //colorize based on escapeTime returned value
                }
                else if (smooth){
                    int n = escapeTime(z)%256;
                    double d = (n*256)%256;
                    System.out.println(d);
                    Color color = new Color ((int)d, 0, 0);
                    StdDraw.setPenColor(color);
                    StdDraw.point(re, im);
                    
                    //colorize based on smoothEscapeTime returned value
                }
                else {
                    if (isMember(z)) {//default case uses standard black and white colorization
                        StdDraw.point(re, im);
                    }
                }
            }
        }
    }
}

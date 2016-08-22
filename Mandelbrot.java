public class Mandelbrot {

    private int limit = 100;	// Maximum iterations for membership testing
    private int rows = 100;	// Horizontal size (in pixels) of drawing
    private int columns = 150;	// Vertical size (in pixels) of drawing


    public Mandelbrot(int rows, int columns, int iterations) {
        this.rows = rows;
        this.columns = columns;
        this.limit = iterations;
    }


    // Test to see if a point in the complex plane is a member of 
    // the Mandelbrot set.  A point (x) is in the set if the sequence
    // of (complex) values z[i] = z[i-1] * z[i-1] + x does not diverge.
    // It can be proved that once a value z[i] in this sequence has a
    // modulus greater than 2.0, it will diverge.  Membership in the
    // set is approximated by checking that the sequence of the first
    // 100 elements of the series all have modulus <= 2.0

    public boolean isMember(Complex x) {
	Complex z = x;

        for (int i = 0; i < limit; i++) {
	    if (z.modulusSquared () > 4.0) return false;
	    z = z.square().add(x);
	}

	return true;
    }


    public void draw() {
	
 	// Dimensions of the canvas in the complex plane.

        double xMin = -2.0;
        double xMax = +1.0;
        double yMin = -1.0;
        double yMax = +1.0;
	double xRange = xMax - xMin;
	double yRange = yMax - yMin;

	// Set the bounds of the canvas in the complex plane as
	// well as the size of the canvas in pixels.

	StdDraw.setCanvasSize(columns, rows);
        StdDraw.setXscale(xMin, xMax);
	StdDraw.setYscale(yMin, yMax);
	StdDraw.setPenRadius(0); // One pixel wide.

	// For each pixel in the image, determine its coordinates in
	// the complex plane and check to see if it is in the Mandelbrot
        // set or not.  Paint the pixel black if it is in the set.

	for (int row = 0; row < rows; row++) {
	    for (int col = 0; col < columns; col++) {
		double re = xMin + xRange * (double) col / (double) columns;
		double im = yMin + yRange * (double) row / (double) rows;
		Complex z = new Complex (re, im);

		if (isMember(z)) {
		    StdDraw.point(re, im);
		}
	    }
        }
    }

}

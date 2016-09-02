/*
 *Michael Gunn
 *HW #2
 *9/1/16
 */
class HW2_Gunn {
    private static double centerReal = 0.0; //Math.PI/10 -.088
    private static double centerImaginary = 0.0; //0.655
    private static double width = 4.0; //.1  .004
    private static int size = 250;
    private static int iters = 255;
    private static double bail = 1.0E15;
    private static String color = "GRAY";
    private static String member = "-member";

    public static final class BadIntegerException extends NumberFormatException {

        public BadIntegerException(int value) {
            super("Invalid value: " + value);
        }
    }

    public static final class BadDoubleException extends NumberFormatException {

        public BadDoubleException(double value) {
            super("Invalid value: " + value);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String s = args[i];
                try {
                    switch (s) { //each command line argument case
                        case "-re":
                            if (i < args.length - 1) {
                                setDouble(args[i], args[i + 1]);
                                centerReal = Double.parseDouble(args[i + 1]); 
                            }
                            break;
                        case "-im":
                            if (i < args.length - 1) {
                                setDouble(args[i], args[i + 1]);
                                centerImaginary = Double.parseDouble(args[i + 1]);
                            }
                            break;
                        case "-width":
                            if (i < args.length - 1) {
                                setDouble(args[i], args[i + 1]);
                                width = Double.parseDouble(args[i + 1]);
                            }
                            break;
                        case "-size":
                            if (i < args.length - 1) {
                                setInt(args[i], args[i + 1]);
                                size = Integer.parseInt(args[i + 1]);
                            }
                            break;
                        case "-iters":
                            if (i < args.length - 1) {
                                setInt(args[i], args[i + 1]);
                                iters = Integer.parseInt(args[i + 1]);
                            }
                            break;
                        case "-bail":
                            if (i < args.length - 1) {
                                setInt(args[i], args[i + 1]);
                                bail = Integer.parseInt(args[i + 1]);
                            }
                            break;
                        case "-color":
                            if (i < args.length - 1) {
                                color = args[i + 1];
                            }
                            break;
                        case "-member":
                            member = args[i];
                            break;
                        case "-escape":
                            member = args[i];
                            break;
                        case "-smooth":
                            member = args[i];
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        Mandelbrot m = new Mandelbrot(centerReal, centerImaginary, bail, iters, size, member, width, color);
        m.draw(); //draw it
    }

    /*
     * throws exceptions
    */
    public static void setInt(String option, String arg) throws NumberFormatException {
        int input = Integer.parseInt(arg);
        if ((!option.equals("-re") || !option.equals("-im")) && input < 0) {
            throw new BadIntegerException(input);
        }
    }

    public static void setDouble(String option, String arg) throws NumberFormatException {
        double input = Double.parseDouble(arg);
        if ((!option.equals("-re") || !option.equals("-im")) && input < 0.0) {
            throw new BadDoubleException(input);
        }
    }
}

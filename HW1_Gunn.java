/*
 *Michael Gunn
 *HW #1
 *8/25/16
 */

class HW1_Gunn {

    private static double centerReal = 0.0;
    private static double centerImaginary = 0.0;
    private static double width = 4.0;
    private static int size = 150;
    private static int iters = 100;
    private static double bail = 1.0E15;
    private static ColorPallete color = ColorPallete.BLACK;

    public static enum ColorPallete {

        BLACK,
        GRAY,
        CUSTOM
    }

    public static class BadIntegerException extends NumberFormatException {

        public BadIntegerException(int value) {
            super("Invalid value: " + value);
        }
    }

    public static class BadDoubleException extends NumberFormatException {

        public BadDoubleException(double value) {
            super("Invalid value: " + value);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length - 1; i++) {
                String s = args[i];
                try {
                    switch (s) { //need to handle exceptions for numberFormat, BadInteger, BadDouble
                        case "-re":
                            setDouble(args[i], args[i + 1]);
                            centerReal = Double.parseDouble(args[i + 1]); //all of these need to be done through trying a set method 
                            break;                                        //that throws exceptions for bad args
                        case "-im":
                            setDouble(args[i], args[i + 1]);
                            centerImaginary = Double.parseDouble(args[i + 1]);
                            break;
                        case "-width":
                            setInt(args[i], args[i + 1]);
                            width = Double.parseDouble(args[i + 1]);
                            break;
                        case "-size":
                            setInt(args[i], args[i + 1]);
                            size = Integer.parseInt(args[i + 1]);
                            break;
                        case "-iters":
                            setInt(args[i], args[i + 1]);
                            iters = Integer.parseInt(args[i + 1]);
                            break;
                        case "-bail":
                            setInt(args[i], args[i + 1]);
                            bail = Integer.parseInt(args[i + 1]);
                            break;
                        case "-color":
                            try {
                                color = ColorPallete.valueOf(s);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            }
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        Mandelbrot m = new Mandelbrot(0.0, 0.0, 1.0E20, 500, 500, "smooth", 4.0);
        m.draw();
    }

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

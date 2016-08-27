/*
 *Michael Gunn
 *HW #1
 *8/25/16
 */

class HW1_Gunn {

    public static void main(String[] args) {
        int columns = 150;
        int rows = 100;
        double centerReal = 0.0;
        double centerImaginary = 0.0;
        if (args.length > 0) {
            try {
                for (int i = 0; i < args.length; i++) {
                    String s = args[i];
                    switch (s){
			case "-re":
                            centerReal = Double.parseDouble(args[i + 1]);
                            break;
                        case "-im":
                            centerImaginary = Double.parseDouble(args[i + 1]);
                            break;
                        case "-width":
                            //to write
                            break;
                        case "-size":
                            //to write
                            break;
                        case "-iters":
                            //to write
                            break;
                        case "-bail":
                            //to write
                            break;

                    
                    }
                }
            }
            catch (Exception e){
                System.out.println("Invalid Columns or Rows parameter");
            }
                }
//                Mandelbrot m = new Mandelbrot(rows, columns, 100);
//                m.draw();
        

            }
        }

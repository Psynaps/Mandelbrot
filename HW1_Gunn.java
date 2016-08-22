/**
 * **@MichaelGunn *Data Structures and Algorithms
**/
class HW1_Gunn {
    public static void main(String[] args) {
        int columns = 150;
        int rows = 100;
        if (args.length>0){
            try{
            columns = Integer.parseInt(args[0]);
            rows = Integer.parseInt(args[1]);
            }
            catch (Exception e){
                System.out.println("Invalid Columns or Rows parameter");
            }
        }
        Mandelbrot m = new Mandelbrot (rows,columns,100);
        m.draw();
       
    }
}

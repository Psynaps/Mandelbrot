/**
 * **@MichaelGunn *Data Structures and Algorithms
**/
class Complex {
    private final double real;
    private final double imaginary;
    public Complex (double real, double imaginary){
        this.real = real; //set instance variable real to the parameter
        this.imaginary = imaginary; //set instance variable imaginary to the parameter
    }
    
    //adds the two complex numbers
    public Complex add (Complex n){ 
        double a = this.getReal() + n.getReal(); // two real components added together
        double b = this.getImaginary() + n.getImaginary(); //the two imaginary components added together
        Complex r = new Complex (a,b);
        return r;
    }
    
    //squares the complex number
    public Complex square(){ 
        double a = this.getReal(); //real component of the input complex number
        double b = this.getImaginary(); //imaginary component of the input complex number
        double real = (a*a)-(b*b); //first-last
        double imaginary = a*b*2; //outer + inner
        Complex r = new Complex (real, imaginary);
        return r;
    }
    
    //performs the modulus squared operation on the complex number
    public double modulusSquared(){
        double a = this.getReal();
        double b = this.getImaginary();
        double r = (a*a)+(b*b);
        return r;
    }
    
    //need to figure out override 
    public boolean equals(Complex n){ //checks whether the two complex numbers are equal
        if (this.getReal()==n.getReal()&&this.getImaginary()==n.getImaginary()){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals (Object o){
        if (o==null) return false;
        if (o instanceof Complex){
            return equals((Complex)o);
        }
        return false;
    }
    
    //subtracts the input complex number from 'this'
    public Complex subtract (Complex n){
        double a = this.getReal()-n.getReal(); //subtract the real component
        double b = this.getImaginary()-n.getImaginary(); //subtract the imaginary component
        Complex r = new Complex (a,b);
        return r;
    }
    
    public double getReal(){
        return this.real;
    }
    
    public double getImaginary(){
        return this.imaginary;
    }
}

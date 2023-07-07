/**
 * IntBag
 */
/*Recall that the number of elements an array has must be specified when you create it and cannot be changed later. However, in many situations,
it is impossible to know in advance how many elements will actually be needed. In such cases, the only solution is to allocate an array large
enough to handle the worst-case scenario and then use a subset of its elements as needed. There are various ways to specify the subset, the
most common of which is to store data values sequentially from the beginning of the array and to maintain a count of the number of such data 
values, thus making it easy to ensure that only those elements that contain valid data are processed. The following exercises ask you to use
a slightly different technique to create a class that can hold a varying number of integer values and to use this to solve a number of simple
problems.

Part a (15 points). Install Git in your local pc. You can follow the instructions for installation here. Signup to GitHub and optionally get an
Education pack with your student id card. Create a private repository using your own account which is called CS102_lab01. Double-check that your
repository is actually private because if it's not and someone copies your lab homework, you will get into trouble because of the plagiarism. 
Now you need to initialize your repository in your local project's folder. To do that and to refresh what was covered in the tutorial, go here. 
For parts b, c and d once you are done with the part, add everything you have coded so far to the staging area, then commit the changes with the 
message "Part {b, c or d} is done". After you committed the code, push them to your private repository. Go to your GitHub account and open the 
CS102_lab01 and check whether the code is updated in the repository, or check whether the latest commit that shows there is the one you have 
just made. If you needed to change something in one of the parts after you already committed and pushed everything, just add everything again, 
commit with the message that explains the changes you made and push the changes to the remote CS102_lab01 repository.

Part b (35 points). Design and implement a class, IntBag, that allows a variable-sized collection of non-negative integer values to be stored.
Your class should have a single property, an int array called bag that will hold the values of the collection. The values themselves must be
stored contiguously starting from the beginning of the array. The last value is followed by a negative (sentinel) value. The class should
have one constructor which creates an empty collection (with room for up to 4 values). Provide methods to add a value to the end of the 
collection and to add a value at a particular index location i within it (moving other values "up" to make room, after checking i is within
bounds). If there is no room for another value, the array should expand so that its size is doubled. Also, write methods to remove the 
value at a given index (placing the last element of the array into that index) and another to test whether the collection contains a given 
value or not. Provide a toString method that returns a String representation of the collection, a method, size, that returns the number of
values currently in the collection, and finally, a method that will allow you to get the value at location i within the collection.

Part c (25 points). Design & implement a program to efficiently compute & display the first 40 Fibonacci numbers by making use of the fact
that the first two Fibonacci numbers are 0 and 1, and each subsequent number is the sum of the previous two. Use an instance of your IntBag
class to keep a collection of Fibonacci numbers found so far. Initially, it should contain only the numbers 0 and 1. Generate and add the 
rest of the Fibonacci numbers into your IntBag instance.

Part d (25 points). Add a method, removeAll, to your IntBag class which removes all instances of a given value in the collection. To demonstrate
and test this method, write a program that presents the user with a menu having the following options (which can be selected in any order by 
typing the corresponding number):

1-Create a new empty collection(any previous values are lost!)
2-Read a set of positive values into the collection (use zero to indicate all the values have been entered.)
3-Print the collection of values.
4-Add a value to the collection of values at a specified location
5-Remove the value at a specified location from the collection of values
6-Remove all instances of a value within the collection* (see note below).
7-Quit the program.

* note about menu option 6:
Assume your collection of values contains 3, 5, 4, 5, 2, 7, 5, 2
Given a value of 5 (using menu option 6) the new method, removeAll , should remove the instances of 5 in the collection so the collection will contain 3, 4, 2, 7, 2. */
import java.util.Scanner;
public class IntBag {
    
    private int bagCapacity = 4 ;
    private int bagSize = bagCapacity + 1 ;
    private int numberOfElements = 0 ;
    private final int sentinelValue = -1 ;
    private int[] IntBag ;
    
    public IntBag(){
        IntBag = new int[bagSize];
        IntBag[numberOfElements] = sentinelValue ;
    }
    public int[] getIntBag(){
        return IntBag;
    }
    public void setIntBag(int index , int num){ // just writing for fibonacci series since the IntBag should normally only contain positive integers
        if(index < bagSize );{
            if (numberOfElements == bagCapacity){
                bagExpand();
            }
            for (int i = bagCapacity ; i >= index ; i--) {
                if(IntBag[i] != 0){
                    IntBag[i+1] = IntBag[i];
                }
            }
            IntBag[index] = num ;
            numberOfElements++;
        }
        if(index >= bagSize){
            System.out.println("out of bounds index");
        }
    }

    private void bagExpand(){
        int tempIntBag[] = new int[bagSize];
        int i = 0 ;
        for (int a : IntBag) {
            tempIntBag[i] = a ;
            if(i<bagSize){
                i++;
            }
        }
        bagSize = 2 * bagSize - 1 ;
        bagCapacity = bagSize - 1 ;
        IntBag = new int[bagSize];
        i = 0 ;
        for (int a : tempIntBag) {
            IntBag[i] = a ;
            if(i<bagSize){
                i++;
            }
        }
    }

    public void addToEnd(int num){
        if ( num > 0 ){
            if (numberOfElements == bagCapacity){
                bagExpand();
            }
            int location = 0 ;
            for (int i : IntBag) {
                    if( i < 0 ){
                    IntBag[location] = num ;
                    IntBag[location+1] = sentinelValue ;
                    numberOfElements++;
                    }
                    else{
                        location++;
                    }
            }
        }
    }

    public void addToIndex(int index , int num){
        if(index < bagSize );{
            if ( num > 0 ){
            if (numberOfElements == bagCapacity){
                bagExpand();
            }
            for (int i = bagCapacity ; i >= index ; i--) {
                if(IntBag[i] != 0){
                    IntBag[i+1] = IntBag[i];
                }
            }
            IntBag[index] = num ;
            numberOfElements++;
        }
        }
        if(index >= bagSize){
            System.out.println("out of bounds index");
        }
        if ( num <= 0 ){
            System.out.println("input a positive number");
        }
    }

    public void removeFromIndex(int index){
        boolean break1 = false ;
         if(index >= bagSize){
            System.out.println("out of bounds index");
            break1 = true ;
        }
        for (int i = bagCapacity ; i > index ; i--) {
                if(IntBag[i] != 0 && numberOfElements > 1 && !break1 ){
                    IntBag[index] = IntBag[i - 1] ;
                    IntBag[i - 1] = IntBag[i] ;
                    IntBag[i] = 0 ;
                    numberOfElements -- ;
                    break1 = true ;
                }
            }
    }


    public boolean doesContain(int value){
        if(value > 0) {
            for (int i : IntBag) {
                if(value == i){
                    return true;
                }
            }
        }
        return false;
    }

    public int getSize(){
        return numberOfElements ;
    }

    public int get(int index){
         if(index < bagSize && index >= 0 ){
            return IntBag[index];
         }
         else{
            System.out.println("out of bounds index");
            return 0 ;
         }
    }

    public String toString(){
        String str = "[ " ;
        for (int i : IntBag) {
            if(i >= 0){
                str += i + " " ;
            }
            if( i < 0 ){
            str += "]" ;
            return str ;  
            }
            
        }
        str += "]" ;
        return str ;
    }
    public void removeAll(int value) {
    
    int shift = 0;
    
    for (int i = 0;i < numberOfElements;i++) {
        if (IntBag[i] == value) {
            shift++;
        } else {
            IntBag[i - shift] = IntBag[i];
        }
        
    }
        numberOfElements -= shift;
    }



    public static void main(String[] args) {
        int userInput = 0 ;
        IntBag intBag = new IntBag() ;
        Scanner in = new Scanner(System.in);
        while(userInput != 7){
            System.out.println("1-Create a new empty collection(any previous values are lost!)");
            System.out.println("2-Read a set of positive values into the collection (use zero to indicate all the values have been entered.)");
            System.out.println("3-Print the collection of values.");
            System.out.println("4-Add a value to the collection of values at a specified location");
            System.out.println("5-Remove the value at a specified location from the collection of values");
            System.out.println("6-Remove all instances of a value within the collection* (see note below).");
            System.out.println("7-Quit the program.");
            System.out.println("8-Calculate the fibonacci number");
            System.out.print("Make a choice...");
            userInput = in.nextInt();
            in.nextLine();
            if(userInput == 1){
                intBag = new IntBag() ;
            }
            if(userInput == 2){
                int addingNumber = 1;
                while (addingNumber != 0){
                        System.out.print("Input positive integers. To stop enter 0...");
                        addingNumber = in.nextInt() ;
                        if(addingNumber > 0){
                             intBag.addToEnd(addingNumber);
                        }
                        else if(addingNumber < 0){
                            System.out.println("Input a positive integer");
                        }
                }
                       
            }
            if(userInput == 3){
                System.out.println(intBag);
            }
            if(userInput == 4){
                int addingNumber = 1;
                int addingNumberLocation = 1;

                    System.out.print("Input positive integer...");
                        addingNumber = in.nextInt() ;
                    System.out.print("Input the location...");
                        addingNumberLocation = in.nextInt();
                        if(addingNumber > 0){
                             intBag.addToIndex(addingNumberLocation, addingNumber);
                        }
                        else if(addingNumber < 0){
                            System.out.println("Input a positive integer");
                        }
                    
                
            }
            if(userInput == 5){
                System.out.print("Location of the value you want to remove...");
                int removingIndex = in.nextInt();
                intBag.removeFromIndex(removingIndex);
            }
            if(userInput == 6){
                System.out.print("Value that you want to remove...");
                int removingValue = in.nextInt();
                intBag.removeAll(removingValue);
            }
            if(userInput == 8){
                System.out.print("Number of elements that you want to calculate of fibonacci...");
                int length = in.nextInt();
                System.out.println(fibonacciCalculator(length));
                
            }

        }
       
    }

    public static IntBag fibonacciCalculator(int wantedLength){
        IntBag fibonacciIntBag = new IntBag() ;
        fibonacciIntBag.setIntBag(0,0);
        fibonacciIntBag.setIntBag(1,1);
        for (int i = 2; i < wantedLength  ; i++) {
            fibonacciIntBag.addToEnd(fibonacciIntBag.get(i-2) + fibonacciIntBag.get(i-1) );
        }
        return fibonacciIntBag ;
    }
   
}
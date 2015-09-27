/*
    Programmer : Anthony D'Ambrosio

    Date       : 9/26/2015

    Purpose    : This program gets the sum of two numbers.

    Limitations: entered data must be numeric whole numbers. 
*/

//package lab05;
import java.util.*;
import java.lang.*;

public class Lab05
{
    static Scanner console = new Scanner(System.in);
    
    public static void main( String[] args )
    {
        //Declaring variables. 
        String firstNumberStr,
               secondNumberStr;
        
        int firstNumberIntArr[],
            secondNumberIntArr[],
            holderArray[],
            total[],
            arraySize,
            biggestSize;
        
        
        // Getting input from the user.
        System.out.print( "Enter the first number: " );
        firstNumberStr = console.next();
        System.out.print( "Enter the second number: " );
        secondNumberStr = console.next();
        
        // Creating arrays to store the strings as numbers. 
        firstNumberIntArr = new int[ firstNumberStr.length() ];
        secondNumberIntArr = new int[ secondNumberStr.length() ];
        
        
        // Getting the size of the larger of the two strings. 
        // Using this number later to set the arrays to equal size.
        biggestSize = 1;
        if ( firstNumberStr.length() < secondNumberStr.length() )
            biggestSize = secondNumberStr.length();
        else if ( secondNumberStr.length() < firstNumberStr.length() )
            biggestSize = firstNumberStr.length();
        if ( firstNumberStr.length() == secondNumberStr.length() )
            biggestSize = firstNumberStr.length();
        
        
        /* 
            This sets the array size to be one larger than the biggest of the 
            two numbers. This is to handle overflow. 99 + 1 are both at most 
            two digits, but when added become 3. 99 + 1 = 100. Thus we want 
            arraySize to be one larger than biggestSize, which is only as large
            as the longest String. 
        */ 
        arraySize = biggestSize + 1;
       
        
        /*
            Converting the strings into numeric arrays, element by element.
            For as long as the string is, we convert each value in the array
            into its numeric counterpart. Thus the -48. There's probably a 
            better way to do this.
        */
            // First string being converted.
        for ( int c = 0; c < firstNumberStr.length(); c++ )
            firstNumberIntArr[c] = ( ( firstNumberStr.charAt( c ) ) - 48 );
        
            // Second string being converted.
        for (int c = 0; c < secondNumberStr.length(); c++)
            secondNumberIntArr[c] = ( ( secondNumberStr.charAt( c ) ) - 48 );
        
        // Sending the first and second array to be reversed. 
        firstNumberIntArr = 
                reverseArr( firstNumberIntArr, firstNumberStr.length() );
        
        secondNumberIntArr = 
                reverseArr( secondNumberIntArr, secondNumberStr.length() );

        
        /*
            Expanding the smaller of the two arrays to be of equal size 
            to the second. This allows us to add the arrays without going
            out of bounds.
        */
        if ( firstNumberStr.length() < secondNumberStr.length() )
        {
            // Creating a dummy the same size as the larger array to hold all
            // of the smaller array.
            holderArray = new int[ secondNumberStr.length() ];
            
            
            // Copies all data of the smaller array into the larger array.
            for ( int c = 0; c < firstNumberStr.length(); c++ )
                holderArray[c] = firstNumberIntArr[c];
            
            
            // This recreates the first array to be the same size as the larger
            // array so that we can add them togther.
            firstNumberIntArr = new int[ secondNumberStr.length() ];
            
            
            // Copying all data from the holder array back into the newly
            // resized firstNumberIntArr.
            for ( int c = 0; c < firstNumberStr.length(); c++ )
                firstNumberIntArr[c] = holderArray[c];
        }
        else if ( secondNumberStr.length() < firstNumberStr.length() )
        {
            // Creating a dummy the same size as the larger array to hold all
            // of the smaller array.
            holderArray = new int[ firstNumberStr.length() ];
            
            
            // Copies all data of the smaller array into the larger array.
            for ( int c = 0; c < secondNumberStr.length(); c++ )
                holderArray[c] = secondNumberIntArr[c];
            
            
            // This recreates the first array to be the same size as the larger
            // array so that we can add them togther.
            secondNumberIntArr = new int[ firstNumberStr.length() ];
            
            
            // Copying all data from the holder array back into the newly
            // resized firstNumberIntArr.
            for ( int c = 0; c < secondNumberStr.length(); c++ )
                secondNumberIntArr[c] = holderArray[c];
        }
        
        
        // Getting the sum of both arrays.
        total = totalArr( firstNumberIntArr, secondNumberIntArr, biggestSize );
        
        // Reversing contents of total.
        total = reverseArr( total, arraySize );
        
        System.out.println( "  " + firstNumberStr );
        System.out.println( "+ " + secondNumberStr );
        System.out.print( "= " );
        
        // if a 0 exists at the beginning, remove it and then prent.
        if ( total[0] != 0 )
            printArr(total, arraySize, " = ");
        else if ( total[0] == 0 )
        {
            for ( int c = 0; c < biggestSize; c++ )
                total[c] = total[ c + 1 ];
                
            printArr(total, biggestSize, " = ");
        }
  
        System.out.println();
    }
    
    // This function accepts an array, a size and returns an array reversed.
    public static int[] reverseArr( int incomingArray[], int sizeOfArray )
    {
        // Declaring variables.
        int swap;
        
        
        // Loop that handles the reversing.
        for ( int c = 0; c < ( sizeOfArray / 2 ); c++ )
        {
            swap = incomingArray[ c ];
            incomingArray[ c ] = incomingArray[ sizeOfArray - c - 1 ];
            incomingArray[ sizeOfArray - c - 1 ] = swap;
        }
        
        
        // Returns the reversed array.
        return incomingArray;
    }
    
    public static void printArr( int incomingArray[], int sizeOfArray, 
            String arrayName )
    {
        // Prints the array
        for ( int c = 0; c < sizeOfArray; c++ )
            System.out.print( incomingArray[c] );
            // Formatting.
        System.out.println();
    }
    
    public static int[] totalArr( int incomingArray1[], int incomingArray2[], 
            int sizeOfArray )
    {
        // The array we will be returning at the end of the method.
        int sumOfArrays[] = new int [sizeOfArray + 1];
       
        
        for (int c = 0; c < sizeOfArray; c++)
        {
            // arr1 + arr2 - 10 gives us the value we want in that ele.
            // ex: 9 + 9 = 18. 18 - 10 = 8. we keep 8 there, move 1 to next.
            // then we add 1 to the next element. thats our carryover. 
             if ( ( incomingArray1[c] + incomingArray2[c] ) >= 10)
             {
                sumOfArrays[c] += ( ( incomingArray1[c] + incomingArray2[c] ) - 10 );
                sumOfArrays[c + 1] = 1;
             }
             // += is important incase theres a +1 from the previous if 
             // statment.
             else if ( ( incomingArray1[c] + incomingArray2[c] ) < 10)
                 sumOfArrays[c] += ( incomingArray1[c] + incomingArray2[c] );
             // If it happens to equal 10, we set that ele to 0 and bump one to 
             // the next.
             if (sumOfArrays[c] == 10)
             {
              sumOfArrays[c] = 0;
              sumOfArrays[c + 1] = 1;
             }
        }
        
        return sumOfArrays;
    }
}
    
 

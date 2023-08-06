import java.sql.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Test
{
    public static void main(String[] args)
    {
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();

        for( int i =0; i < 100000000; i++)
        {
            numbers.add(random.nextInt(1000000));
        }


        LocalDateTime start = LocalDateTime.now();
        mergeSort(numbers);

        LocalDateTime end = LocalDateTime.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    public static void mergeSort(ArrayList<Integer> inputArray)
    {
        //Get the length of the input Array
        int inputLength = inputArray.size();

        //check if the input length is less than two then it breaks
        if (inputLength < 2)
        {
            //break out of the method
            return;
        }

        //get the mid index
        int midIndex = inputLength / 2;

        //Create the left and right arrays
        ArrayList<Integer> leftHalf = new ArrayList<>();
        ArrayList<Integer> rightHalf = new ArrayList<>();

        //Populate the leftHalf array
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(inputArray.get(i));
        }

        //Populate the right half array
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf.add(inputArray.get(i));
        }

        //Call the merge sort to add recursion
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //Merge the two methods
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        //Create the merge indexes
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {
            if (leftHalf.get(i) <= rightHalf.get(j))
            {
                //Add the i index of the left half to the input array
                inputArray.set(k, leftHalf.get(i));

                //incriment i
                i++;
            }
            else
            {
                //Add the j index of the left half to the input array
                inputArray.set(k, rightHalf.get(j));

                //incriment j
                j++;
            }

            k++;
        }

        while (i < leftSize)
        {
            //Add the i index of the left half to the input array
            inputArray.set(k, leftHalf.get(i));

            //incriment i and k
            i++;
            k++;
        }

        while (j < rightSize)
        {
            //Add the j index of the left half to the input array
            inputArray.set(k, rightHalf.get(j));

            //incriment j and k
            j++;
            k++;
        }
    }
}

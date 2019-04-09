# Comb Sort

### Intro

* Created by Włodzimierz Dobosiewicz in 1980.
* It was rediscovered by Stephen Lacey and Richard Box later in 1991.
* Expands on bubble sort.
* Uses a gap to eliminate `turtles`, **small values in the end**.
* `Rabbits`, **large values in the beginning** are not a problem for either sort.

![Comb Sort](https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Comb_sort_demo.gif/220px-Comb_sort_demo.gif)

Notice how near the end, there are no large jumps up and down

------

### Explanation

###### Bubble sort

* Repeatedly goes through a list and swaps items if they do not meet a condition, e.x. to sort from smallest to largest.

* Bubble sort suffers from `turtles`, since it wastes a lot of comparisons to get the item all the way to the beginning.



###### Comb sort

* Items are compared with a gap between them.
* If `i` is the iteration of a loop, the two values compared are `i` and  `i + gap` .
* When `i + gap` reaches the last item in the list, the gap shrinks and `i` returns to 0.

* Rate of shrinking is called `shrink factor`, usually 1.3.

* It divides `gap` by the `shrink factor` every iteration.
* Once `gap` is 1, it uses regular bubble sort, but by this point, there are no turtles left.

------

### Code example

###### Creating an array to sort:

```java
//creates an unsorted array, a CombSort object, and runs the sort method
public static void main(String[] args) {
    int[] unsorted = {10, 5, 421, 19, 9, 2, 6};
    CombSort cs = new CombSort();
    cs.sort(unsorted);
}
```



###### The sort method:

Initialize variables

```java
public void sort(int[] testArray) {
    double gap = testArray.length; //sets initial gap size
    double shrinkFactor = 1.3; //sets shrink factor
    boolean sorted = false;
    
    ...
```

Inside, we have a while loop

```java
while (!sorted) { //loops until array is declared sorted
    if (gap > 1)
        gap = Math.floor(gap / shrinkFactor); //decreases gap size
    else //we can't do this when gap is not 1, since not all elements would be compared
        sorted = true;  //assume the array is sorted until we find out it's not
    
	//this for loop is what actually checks and swaps elements to sort
    //combs through the array once    
    for (int i = 0; i + gap < testArray.length; i++) {
        if (testArray[i] > testArray[(int) (i + gap)]) {
            //swaps the two values
            int temp = testArray[i];
            testArray[i] = testArray[(int) (i + gap)];
            testArray[(int) (i + gap)] = temp;

            sorted = false; //if a swap was made, the array was not actually sorted
        }
    }
}
...
```

Finally, some way to display the sorted array:


```java
//prints out now-sorted array
System.out.println(Arrays.toString(testArray));
```



Our original array:

```
[10, 5, 421, 19, 9, 2, 6]
```

The output:

```
[2, 5, 6, 9, 10, 19, 421]
```

The sort worked!

------

### Additional info

* 1.3 was determined to be the best value for a shrink factor after test on 200,000 arrays in the original study.
* A shrink factor too high does not give enough time to remove turtles, while a shrink factor too low causes many unnecessary iterations and comparisons.
* Worst case performance for both is ![O(n^{2})](https://wikimedia.org/api/rest_v1/media/math/render/svg/6cd9594a16cb898b8f2a2dff9227a385ec183392), but Comb sort is very often faster than Bubble sort, since it can avoid the problem of turtles.

------

### Conclusion

* Comb sort uses principles of Bubble sort.
* Comb sort uses a gap, which it divides by a shrink factor every iteration to reduce turtles.
* 1.3 is considered the best shrink factor
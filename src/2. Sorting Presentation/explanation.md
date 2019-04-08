# Comb Sort

### Intro

* Created by Włodzimierz Dobosiewicz in 1980
* Expands on bubble sort
* Uses a gap to eliminate *turtles*
* *Rabbits* are not a problem for either sort



### Explanation





### Code example

Creating an array to sort:

```java
//creates an unsorted array, a CombSort object, and runs the sort method
public static void main(String[] args) {
    int[] unsorted = {10, 5, 421, 19, 9, 2, 6};
    CombSort cs = new CombSort();
    cs.sort(unsorted);
}
```



The sort method:

```java
public void sort(int[] testArray) {
    double gap = testArray.length; //sets initial gap size
    double shrinkFactor = 1.3; //sets shrink factor
    boolean sorted = false;
    
    ...
```



Next, we have a while loop

```java
while (sorted == false) { //loops until array is declared sorted

    gap = Math.floor(gap / shrinkFactor); //decreases gap size

    if (gap <= 1) {
        gap = 1;
        sorted = true; //array is sorted when the gap == 1
    } else {
    }

    //combs through the array once
    int i = 0;
    while (i + gap < testArray.length) {
        if (testArray[i] > testArray[(int) (i + gap)]) {
            //swaps the two values
            int temp = testArray[i];
            testArray[i] = testArray[(int) (i + gap)];
            testArray[(int) (i + gap)] = temp;

            //array is still unsorted
            sorted = false;
        } else {
        }
        i++;
    }
}
...
```



Finally, some way to display the sorted array


```java
//prints out now-sorted array
for (int i : testArray) {
    System.out.println(i);
}
```


Our original array:

```
10, 5, 421, 19, 9, 2, 6
```

The output:

```
2
5
6
9
10
19
421
```



### Additional info





### Conclusion
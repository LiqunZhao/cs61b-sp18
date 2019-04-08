---
html:
    embed_local_images: true
    toc: true
toc:
    depth_from: 1
    depth_to: 2
    ordered: true
export_on_save:
    html: true
---


<!-- Importing styles for numbering sections from H1 -->
<!-- @import "C:\Users\aviat\.atom\mpe-styles\numbering-from-h1.less" -->
<!-- Importing fancy github-light theme -->
<!-- @import "C:/Users/aviat/.atom/mpe-styles/fancy-github-light.less" -->



# Week 1 - Lec.1 & Lec.2 {ignore=True .ignorenumbering}


## TOC {ignore=True .ignorenumbering}

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=2 orderedList=true} -->
<!-- code_chunk_output -->

1. [Lec.1 - Intro, Hello World Java](#lec1-intro-hello-world-java)
    1. [Hello world](#hello-world)
    2. [Static typing](#static-typing)
    3. [Declaring functions in Java](#declaring-functions-in-java)
2. [Lec.2 - Defining and Using Classes](#lec2-defining-and-using-classes)
    1. [Compilation](#compilation)
    2. [Defining and Instantiating Classes](#defining-and-instantiating-classes)
    3. [Arrays of Objects](#arrays-of-objects)
    4. [Static vs. Instance methods](#static-vs-instance-methods)
    5. [`public static void main(String[] args)` ?](#public-static-void-mainstring-args)

<!-- /code_chunk_output -->



# Lec.1 - Intro, Hello World Java

## Hello world

> HelloWorld.java

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world !");
    }
}
```

1. All code in Java must be part of a class
2. We delimit the beginning and end of segments of code
   using `{` and `}`
3. All statements in Java must end in a semi-colon ;
4. For code to run we need `public static void main(String[] args)`



## Static typing

> HelloNumbers.java
```java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.println(x);
            x = x + 1;
        }
        // x = "horse"; should cause error
    }
}
```

1. Before Java variables can be used, they must be declared
2. Java variables must have a specific type
3. Java variable types can never change
4. Types are verified before the code even runs !!!

<br>

In summary static typing brings us ...

- ***The goods***:
    - **Type check**: Types are checked before the program is *even run*, allowing developers to catch type errors with ease
    - **Type error free**: If the compiled version of a program is distributed, it is (mostly) guaranteed to be free of any type errors
        - makes the code more reliable
    - **Easy understanding**: Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code
    - **Efficiency**: Code can run more efficiently, e.g. no need to do expensive runtime type checks
- ***The bads***:
    - **Verbose**: Code is more verbose
    - **Less general**: Code is less general
        - -> Use generics in java


## Declaring functions in Java

> LargerDemo.java

```java
public class LargerDemo {
    /** Returns the larger of x and y. */
    public static int larger(int x, int y) {
    if (x > y) {
    return x;
    }
    return y;
    }

    public static void main(String[] args) {
    System.out.println(larger(5, 10));
    }
}
```

1. **Functions must be declared as part of a class in Java**
    * A function that is part of a class is called a **method**. So in Java, all functions are methods
2. To define a function in Java, we use `public static` (We will see alternate ways of defining functions later)
3. All parameters of a function must have a declared type, and the return value of the function must have a declared type.
    * Functions in Java return only one value!



# Lec.2 - Defining and Using Classes

## Compilation

Why make a `.class` file at all ?
- `.class` file has been type checked. Distributed code is safer
- `.class` files are *simpler* for machine to execute. Distributed code is faster
- Minor benefit: Protects our intellectual property. No need to give out source

## Defining and Instantiating Classes

- Every method (a.k.a. function) is associated with some class
- To run a class, we must define a `main` method
    - **Not all classes have a main method !**

Define class without main method
> Dog.java
```java
public class Dog {
    public static void makeNoise() {
        System.out.println("bark!");
    }
}
```

Calls a method from another class
> DogLauncher.java
```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog.makeNoise();
    }
}
```

To run,
> \$ javac DogLauncher.java
> \$ java DogLauncher

```
bark!
```

### Instantiating {ignore=True .ignorenumbering}

- Classes can contain not just methods, but also data
- Classes can be instantiated as objects
    * We'll create a single `Dog` class, and then create instances of this `Dog`
    * The class provides a blueprint that all `Dog` objects will follow

> Dog.java
```java
public class Dog {
    public int weightInPounds;

    /** One integer constructor for Dogs */
    public Dog(int w) {
        weightInPounds = w;
    }
    
    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark!");
        } else {
            System.out.println("woooof!");
        }
    }
}
```

> DogLauncher.java
```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(51);
        d.makeNoise();
    }
}
```

### Terminology {ignore=True .ignorenumbering}

> Dog.java
- `public int weightInPounds;`: **Instance variable**
    * Can have as many of these as we want
- `public Dog(int w) {...}`: **Constructor**
    * Determines how to instantiate the class (not a method)
- `public void makeNoise() {...}`: **Non-static method** a.k.a. **Instance method**
    * If the method is going to be invoked by an instance of the class, then it should be *non-static*

> DogLauncher.java
- `Dog smallDog;`: **Declaration**
- `new Dog(20);`: **Instantiation**
- `smallDog = new Dog(5);`: Instantiation & **Assignment**
- `Dog hugeDog = new Dog(150);`: Declaration, Instantiation & Assignment
- `hugeDog.makeNoise();`: **Invocation** (of the 150 lb `Dog`'s `makeNoise` method
    * `.` means we want to use **member** (a method or variable) belonging to `hugeDog`


## Arrays of Objects

To create an array of objects:

- First use the `new` keyword to create the array
- Then use `new` again for each object that we want to put in the array

```java
Dog[] dogs = new Dog[2];
dogs[0] = new Dog(8);
dogs[1] = new Dog(20);
dogs[0].makeNoise();
```

## Static vs. Instance methods

Key differences between static and non-static (a.k.a. instance) method

- Static method are invoked using the class name: `Dog.makeNoise();`
    * Can't access instance variables: can't access `weightInPounds`
- Instance methods are invoked using an instance name: `d.makeNoise();`


### Why Static Methods ? {ignore=True .ignorenumbering}

- Some classes are *never* instantiated. 
    - e.g. `x = Math.round(5.6);`
    - c.f. `Math m = new Math();` & `x = m.round();`

<br>

- A class may have a mix of static and non-static methods
    * A variable or method defined in a class is called a **member** of that class
    * Static members are accessed using class name: `Dog.binomen`
    * Non-static members *can't* be invoked using class name: ~~`Dog.makeNoise()`~~
    * Static methods must access instance variables via a specific instance: `d1.makeNoise()`


## `public static void main(String[] args)` ?

- One special role for `Strings[]`: **Command Line Arguments**

> ArgsDemo.java
```java
public class ArgsDemo {
    /** Prints out the 0th command line argument */
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

> \> java ArgsDemo hello test

```
hello
```

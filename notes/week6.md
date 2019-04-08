---
html:
    embed_local_images: true
    toc: true
toc:
    depth_from: 1
    depth_to: 2
    ordered: false
export_on_save:
    html: true
---

<!-- Importing styles for numbering sections from H1 -->
<!-- @import "C:\Users\aviat\.atom\mpe-styles\numbering-from-h1.less" -->
<!-- Importing fancy github-light theme -->
<!-- @import "C:/Users/aviat/.atom/mpe-styles/fancy-github-light.less" -->



# Week 6 - Lec.15 & Lec.16 {ignore=True .ignorenumbering}


## TOC {ignore=True .ignorenumbering}

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=2 orderedList=false} -->
<!-- code_chunk_output -->

* [Lec.15 - Package, Access Control, `Object` method](#lec15-package-access-control-object-method)
    * [Package](#package)
    * [Access Control (Again)](#access-control-again)
    * [`Object` methods](#object-methods)

<!-- /code_chunk_output -->



# Lec.15 - Package, Access Control, `Object` method


## Package

### What is Package (Again) {ignore=True .ignorenumbering}

To address the problem that classes might share names:
- A package is a ***namespace*** that organizes classes and interfaces
- Naming convention: Package name starts with website address (backwards)

To use a package from the outside:
- Use entire ***canonical namespace***: `org.junit.Assert.assertEquals(expected, actual);`
- Use `import` statement and use the ***simple name*** as inside of the package:
```java
import org.junit.Assert.assertEquals;
...
assertEquals(expected, actual)
```

### Creating Packages

Two steps:
- At the top of every file in the package, put the package name: e.g.: `org.aviatesk.animal;`
- Make sure that the file is stored in a folder with the appropriate folder name: e.g.: `org/aviatesk/animal/Dog.java`

IntelliJ can help the process: [link][slides]

[slides]: https://docs.google.com/presentation/d/1Tey6yhsjPE234mtDh7zu6NwxrXGdMvAMEDlqTCgWyZA/edit#

### Default Package

Any Java class without a package name at the top are part of the *default* package:
- Doesn't organize namespaces
- We cannot import code from the default package
- > We should avoid using the default package except for very small example programs (by *Dan Dyer*, from Stack Overflow)

From now on, when writing real programs, our Java files should always start with a package declaration.

### JAR Files

Suppose we've written a program that we want to share:
- Sharing dozens of .class files in special directories is annoying
- Can instead share *a single .jar file* that contains all of our .class files in the appropriate dependencies

IntelliJ helps us **build** .jar files: [link][slides]

#### Note on JAR Files {ignore=True .ignorenumbering}

JAR files are really just zip files, but with some extra info added:
- They do not keep our code safe !
- Easy to unzip and transform back into .java files

### Build System

To avoid the need to import a bunch of libraries, put files into the appropriate place, and so forth, there exist a number of *Build System*s:
- Very useful for large teams and large projects
- Popular build systems: Ant, Maven, Gradle (ascendant)


## Access Control (Again)

### Access Control with Inheritance and Packages

#### The `protected` Keyword

`protected` modifier allows {++packages-buddies and subclasses++} to use the class member (i.e. filed/method/constructor)

#### **Package-Private**

Using **no modifier** allows *classes from the same package, {++but not subclasses++}*, to access the class member

#### Java Access Control Table

| Modifier                       | Class | Package  | Subclass | Outside World |
|--------------------------------|-------|----------|----------|---------------|
| `public`                       | YES   | YES      | YES      | YES           |
| `protected`                    | YES   | YES      | YES      | {++NO++}      |
| None (***Package-Private***) | YES   | YES      | {++NO++} | {++NO++}      |
| `private`                      | YES   | {++NO++} | {++NO++} | {++NO++}      |

##### Why not: ` Package-Private | YES | NO | YES | NO` ?

- Talk about how we work:
    * Extending classes we didn't write is common
    * Packages are typically modified only by a specific team of people

#### Package-Private and Default Package

What about code that doesn't have a package declaration and any access modifiers ?
- Everything is package-private
- Everything is part of the same (unnamed) default package

Thus the classes below should work file:
```java
public class Dog {
    void bark() {
        System.out.println("bark !");
    }
}
```
```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.bark();
    }
}
```

### Access Control at the Top Level

c.f.: So far we've discussed how to control access to *members*
- Also possible to control access at the *top level* (i.e. an entire interface or class)

Two levels: Determines who can see the existence of the class
- `public`: Entire world can see
- No modifier(***package-private***): Just members of the same package can see

### Summary

Access levels:
- `private` declarations are parts of the implementation of a class that only that class needs
- ***Package-private*** declarations are parts of the implementation f a package that other members of the package will need to complete the implementation
- `protected` declarations are things that subtypes might need, but subtype clients will not
- `public` declarations are declarations of the specification for the package, i.e. what *clients* of the package can rely on
    * {++Once deployed, these should not change !++}


## `Object` methods

All classes are hyponyms of `Object` and inherit default methods below:
- `String toString()`
- `boolean equals(Object obj)`
- `Class<?> getClass()`
- `int hashCode()`
- `protected Object clone()`
- `protected void finalize()`
- `void notify()`
- `void notifyAll()`
- `void wait()`
- `void wait(long timeout)`
- `void wait(long timeout, int nanos)`

### `toString()`

If we want a custom `String` representation of an `Object`, create a `toString()` method

> Date.java
```java
public class Date {
    private final int month;
    private final int day;
    private final int year;
    
    ...
    
    @Override
    public String toString() {
        return "Date object: " + year + "-" + month + "-" + day;
    }

    public static void main(String[] args) {
        Date d = new Date(2, 9, 2019);
        System.out.println(d);
    }

}
```

### `equals()`

#### `equals()` vs `==`

`==` and `.equals()` behave differently:
- `==` checks that two variables refer to the same object (i.e. compares bits in boxes)
- `.equals()` can be overridden to check equality we want
    * The default implementation uses `==`

> Date.java
```java
public class Date {
    private final int month;
    private final int day;
    private final int year;
    
    ...

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        if (this == obj) {
            return true;
        }

        Date uddaDate = (Date) obj;
        if (month != uddaDate.month) {
            return false;
        }
        if (day != uddaDate.day) {
            return false;
        }
        if (year != uddaDate.year) {
            return false;
        }
        return true;
    }
    
}
```

**Important notes**:
- Takes an `Object` argument
    * Thus needs {++casting++}
- Never true for `null` and the other class objects
    * Use `.getClass()` for checking the class equality
- *Should be consistent*: If `x.equals(y)`, then `x` must continue to equal `y` as long as neither changes
- `this == obj` can cut wasteful equality check

Java convention is that `equals()` must be an equivalence rotation:
- Reflexive: `x.equals(x)` is true
- Symmetric: `x.equals(y)` is true if and only if `y.equals(x)`
- Transitive: `x.equals(y)` and `y.equals(z)` implies `x.equals(z)`

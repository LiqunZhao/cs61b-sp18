  
  
  
  
# cs61b-sp18 - aviatesk
  
  
This repository is for [aviatesk]'s self-learning of algorithms and data structure with the materials for [CS61B], taught at UC Berkeley in Spring 2018 by Josh Hug.
  
Beyond of all, I really, really appreciate this great learning-materials are made public, and want to say an infinite thanks to the instructor Josh and everyone who contributed to his lectures.
  
  
## Archives
  
  
- Week 1
    * [x] [note](./notes/week1.md )
        + [x] Lec.1: Intro to Java, Static Typing
        + [x] Lec.2: Classes
    * [ ] disc1
    * [x] [lab1](./lab1 ): javac, java, git (More Git details: [Using Git](https://sp18.datastructur.es/materials/guides/using-git.html ))
    * [x] [hw0](https://sp18.datastructur.es/materials/hw/hw0/hw0 ): Basic Java Syntaxes
- Week 2
    * [x] [note](./notes/week2.md )
        + [x] Lec.3: References
        + [x] Lec.4: Node Based List, Nested Class, Caching, Sentinel
        + [x] Lec.5: Generics, Array
    * [ ] disc2
    * [ ] examprep2
    * [x] [lab2setup](./lab2setup ): Create Projects or import libraries within IntelliJ (Link: [Setting Up IntelliJ](https://sp18.datastructur.es/materials/lab/lab2setup/lab2setup ))
    * [x] [lab2](./lab2 ): Debugger, JUnit Test
    * [x] [proj0](./proj0 ): Just Fun with Java
- Week 3
    * [x] [note](./notes/week3.md )
        + Lec.6: Array-based List
        + Lec.7: Testing
        + Lec.8: Interface, Implementation Inheritance, Dynamic Method Selection
    * [x] [disc3](https://sp18.datastructur.es/materials/discussion/disc03sol.pdf ): Linked List, Array
    * [ ] examprep3
    * [ ] [lab3](./lab3 ): Debugger, JUnit Test (**Implementing `reverse` method correctly remaining**)
    * [x] [proj1a](./proj1a ): Doubly-linked List, Doubly-linked Array
- Week 4
    * [x] [note](./notes/week4.md )
        + Lec.9: Implementation Inheritance, Encapsulation, Type Checking, Higher Order Function
        + Lec.10: Subtype Polymorphism
        + Lec.11: Libraries
    * [x] [disc4](https://sp18.datastructur.es/materials/discussion/disc04sol.pdf ): Inheritance
    * [ ] examprep4
    * lab4: Not available for ppl alone ...
    * [x] [proj1b](./proj1b ): Test Driven Development
    * [x] [proj1glod](./proj1gold ): Randomized Tests
- Week 5
    * [x] [note](./notes/week5.md )
        + Lec.13: Conversion, Immutability, Generics
        + Lec.14: Exception, `Iterator`
    * [x] [lab5](./proj2/byog/lab5 ): Pseudo random number, [Tile Rendering Engine](https://sp18.datastructur.es/materials/lab/lab5/lab5 )
    * [x] [disc5](https://sp18.datastructur.es/materials/discussion/disc05sol.pdf ): ADTs
    * [x] [examprep5](https://sp18.datastructur.es/materials/discussion/examprep05sol.pdf ): Using ADTs
    * [x] [hw1](./hw1 ): Package, Interface, Generics, Exception, Iteration
- Week 6
    * [x] [note](./notes/week6.md )
        + Lec.15: Package, Access Control, `Object` method
        + Lec.16: Programming Efficiency, API design, Views (No note but the slides available at [link](https://docs.google.com/presentation/d/1__Akx5EBZe7sMyCYBN1uToKkhrRuxi0mtxSj1DjU51M/edit?usp=sharing ))
    * [x] [lab6](./proj2/byog/lab6 ): Interactivity within [StdDraw](https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html )
    * [x] [disc6](https://sp18.datastructur.es/materials/discussion/disc06sol.pdf ): Access Control, Designing API
    * [x] [examprep6](https://sp18.datastructur.es/materials/discussion/examprep06sol.pdf ): Exception, Linked List
    * [x] [proj2 phase1](./proj2/byog/Core ): Large scale programming, Generative program using pseudo random number
- Week 7
    * [x] [note](./notes/week7.md )
        + Lec.17: Introduction to Asymptotic Analysis, Big Theta
        + Lec.18: 5 Basic Case Studies of Asymptotic Analysis
        + Lec.19: Big O, Big Omega, Amortized Analysis
    * [x] [disc7](https://sp18.datastructur.es/materials/discussion/disc07sol.pdf ): Asymptotic Analysis Examples, Using ADTs
    * [x] [examprep7](https://sp18.datastructur.es/materials/discussion/examprep07sol.pdf ): (A bit exam-flavored) Problems
    * [x] [proj2 phase2](./proj2/ ): UI Design, Serialization
- Week 8
    * [x] [note](./notes/week8.md )
        + Lec.20: Disjoint Set
        + Lec.21: Binary Search Tree Basics
        + Lec.22: Tree Rotation, B-Tree, LLRB
    * [x] disc8: AA of Pseudo Code, Mathematical Discussion of AA
    * [x] examprep8: AA of Pseudo Code
    * [x] hw2: Percolation Simulation with Disjoint Set
- Week 9
    * [ ] note
        + Lec.23
        + Lec.24
        + Lec.25
    * [ ] disc9
    * [ ] examprep9
    * [ ] lab9
    * [ ] hw3
  
  
## Bugs and fix
  
  
- [proj0](./proj0 ): Some characters in [proj0/StdDraw.java](./proj0/StdDraw.java ) could not be compiled on my environment
    - Fix: [`94c2ade`](https://github.com/aviatesk/cs61b-sp18/commit/94c2adea81ea826b103303e4285a62a2ff790615 )
  
> \> javac NBody.java
  
```
.\StdDraw.java:299: error: unmappable character (0x92) for encoding windows-31j
 *  You save your image to a file using the <em>File 竊? Save</em> menu option.
                                                      ^
.\StdDraw.java:433: error: unmappable character (0x93) for encoding windows-31j
 *       from (0.5, 窶?&infin;) to (0.5, &infin;) may not be visible even in the
                     ^
  
.\StdDraw.java:1190: error: unmappable character (0x93) for encoding windows-31j
     * (<em>x</em><sub><em>n</em>窶?1</sub>, <em>y</em><sub><em>n</em>窶?1</sub>).
                                  ^
.\StdDraw.java:1190: error: unmappable character (0x93) for encoding windows-31j
     * (<em>x</em><sub><em>n</em>窶?1</sub>, <em>y</em><sub><em>n</em>窶?1</sub>).
                                                                      ^
.\StdDraw.java:1219: error: unmappable character (0x93) for encoding windows-31j
     * (<em>x</em><sub><em>n</em>窶?1</sub>, <em>y</em><sub><em>n</em>窶?1</sub>).
                                  ^
.\StdDraw.java:1219: error: unmappable character (0x93) for encoding windows-31j
     * (<em>x</em><sub><em>n</em>窶?1</sub>, <em>y</em><sub><em>n</em>窶?1</sub>).
                                                                      ^
Note: .\StdDraw.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
6 errors
```
  
### Java versions (on Windows 10)
  
  
> \> java -version
  
```
java version "11.0.2" 2018-10-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.2+7-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.2+7-LTS, mixed mode)
```
  
  
## Links
  
  
- Course web-page: https://sp18.datastructur.es/index.html
- The original cloned *skeleton* repository: https://github.com/Berkeley-CS61B/skeleton-sp18
- Autograder: https://www.gradescope.com/
  
  
## Who I am
  
  
**KADOWAKI, Shuhei** - *Undergraduate@Kyoto Univ.* - [aviatesk]
  
  
  
  
  
[aviatesk]: https://github.com/aviatesk
[CS61B]: https://sp18.datastructur.es/index.html
  
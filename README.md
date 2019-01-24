  
  
  
# cs61b-sp18 - aviatesk
  
  
This repository is for [aviatesk]'s self-learning of algorithms and data structure with the materials for [CS61B], taught at UC Berkeley in Spring 2018 by Josh Hug.
  
Beyond of all, I really, really appreciate this great learning-materials is made public, and want to say the infinite thanks to the instructor Josh and everyone who contributed to his lecture.
  
  
  
## TODOs
  
  
- [x] Week 1
    * [x] [note](./notes/week1.md ): Lec.1, Lec.2
    * [x] [lab1](./lab1 )
    * [x] hw0
- [x] Week 2
    * [x] [note](./notes/week2.md ): Lec.3, Lec.4, Lec.5
    * [x] [lab2](./lab2 )
    * [x] [proj0](./proj0 )
  
  
  
  
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
  
  
## References
  
  
- Course web-page: https://sp18.datastructur.es/index.html
- The repository is originally cloned from: https://github.com/Berkeley-CS61B/skeleton-sp18
- Auto-grader{>> is at<<}: https://www.gradescope.com/
  
  
  
## Who I am
  
  
**KADOWAKI, Shuhei** - *Undergraduate@Kyoto Univ.* - [aviatesk]
  
  
  
  
  
  
[aviatesk]: https://github.com/aviatesk
[CS61B]: https://sp18.datastructur.es/index.html
  

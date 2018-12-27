# Static typing

- **Type check**: Types are checked before the program is *even run*, allowing developers to catch type errors with ease
- **Type error free**: If the compiled version of a program is distributed, it is (mostly) guaranteed to be free of any type errors
    - makes the code more reliable
- **Easy understanding**: Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code



# Git

- `git init`: Creates a box in which to permanently store panoramic pictures.
- `git add [file]`: Takes a temporary photo of one thing that can be assembled into a panoramic photo later.
- `git commit`: Assembles all available temporary photos into a panoramic photo. Also destroys all temporary photos.
- `git log`: Lists all the panoramic photos we’ve ever taken.
- `git show`: Looks at what is in a particular panoramic photo.
- `git checkout [log ID] [file]`: Rearranges files back to how they looked in a given panoramic photo. Does not affect the panormiac photos in your box in any way.

<br>

- `git clone [remote-repo-URL]`: Makes a copy of the specified repository, but on your local computer. Also creates a working directory that has files arranged exactly like the most recent snapshot in the download repository. Also records the URL of the remote repository for subsequent network data transfers, and gives it the special remote-repo-name “origin”.
- `git remote add [remote-repo-name] [remote-repo-URL]`: Records a new location for network data transfers.
- `git remote -v`: Lists all locations for network data transfers.
- `git pull [remote-repo-name] master`: Get the most recent copy of the files as seen in remote-repo-name
- `git push [remote-repo-name] master`: Pushes the most recent copy of your files to the remote-repo-name.



# Java basics

- All code lives inside of **classes**
- Java program can't run without `main` method: `public static void main(String[] args)`
    - **Client Programs**: methods from one class can be invoked using the `main` method of another class
- **method**: same as *function* in Python
- **Class**:
    - instantiation: `Dog g = new Dog()`
    - **Members**: Java classes can contain methods and/or variables
        - can be accessed with *Dot Notation*: `d.bark()`
        - **static** members: with `static` keyword
            - static methods are taken by **the class itself**: called like `Math.sqrt()`
            - static variables are accessed by **the class itself**: called like `Dog.binomen`
        - **instance** members: without `static` keyword
            - instance methods are taken by **an instance of the class**: called like `d.bark()`
            - instance variables are accessed by **an instance of the class**: called like `d.binomen`
    - `this`: We can refer to the current instance inside a method with `this` keyword
    - `public static void main(String[] args)`
        - `public`: magic for now
        - `static`: meaning `main` method is not associated with any particular instance
        - `void`: no return tyep
        - `main`: the name of the main method
        - `String[] args`: parameter passed to the `main` method

# javafx-space-invaders
JavaFX Space Invaders game developed as an assignment for high school students.

Created by Chris Cannon

## Overview

This is a basic JavaFX space invaders game. The code is split into three primary areas. Datatypes holds the underlying data that is used to run the game, this is basic code that holds information and provides it to the program. Framework is the code that is used to interface between the program and the computer. GUI is the graphical user interface, the code that the user interacts with directly.

## Data Types

### Game

- Accessor methods: accessor methods are used to get the value from a private variable. They should usually begin with "get" and return the private variable they are named after. For example `public String getName() { return name;}`

-Mutator methods: mutator methods are used to change the value of a private variable. Mutator methods should only be written when necessary, as they may lead to unexpected changes in the code. For example `public void setName(String name) { this.name = name; }`

### GameConfigData

- Arrays are data structures that hold values of a single type. In this case, our array will hold String variables. We must instantiate (create) an array to a given size and type. For example, an array that would hold 20 integers called "myInts" would be instantiated like this: `myInts = new String[20];`.

### PlayerProfile

- Constructors are special methods that are used to create instances of an object. Unlike most other methods, they do not have a return type. They often have arguments with the same name as the variables of the class. For example, if we have a class called "Book" that has the String variables "title" and "author", one constructor that takes an argument for each variable would look like this:

```
public Book(String title, String author) {
  this.title = title;
  this.author = author;
}
```

- Additionally, there can be multiple constructors for a single class. For example, a no-argument constructor initializes all variables to their default value, but has no arguments. The no-arg constructor for Book would look like this:

```
public Book() {
  this.title = "";
  this.author = "";
}
```

### PlayerProfileCollection

- An ArrayList is a data structure that holds multiple variables of a specific type, but does not have a set size, like an array does. It grows and shrinks as needed as items are added and removed. One of the most important parts of programming is Encapsulation, which involves protecting data from being used improperly. For a data structure like an ArrayList, it is important to allow access to certain operations without allowing _complete_ access. Lets say we had a private ArrayList called "widgets" that holds `Widget` objects, we would set up the following public methods to access and mutate the ArrayList:

```
public int getNumWidgets() {
  return widgets.getSize();
}

public void addWidget(Widget object) {
  widgets.add(object);
}

public void setWidget(int index, Widget other) {
  widgets.set(index, other);
}

public Widget getWidget(int index) {
  return widgets.get(index);
}

public Widget removeWidget(int index) {
  return widgets.remove(index);
}
```

- To update the high score, you must iterate through the ArrayList profiles, and compare each high score to the current highScore. You will want to use a `for` loop to perform this search.

## Framework

### SpaceInvadersIO

- To write to a file, you will want to use the `PrintWriter` class, and the `print()` method. An example is below

```
try{
  PrintWriter pw = new PrintWriter(new File("filename"));
  pw.println("This will print to the file");
  pw.close();
}catch(java.io.FileNotFoundException ex) {
  System.err.println("Could not find file for printing");
}
```

- The GameConfigData method will read the input file called gameConfigData.txt and populate the imagePaths array. You will use the `Scanner` class and the `nextLine` method. You should use a `for` loop or a `while` loop to read in the 4 image paths. An example of reading one image path is below

```
try{
  Scanner reader = new Scanner(new File("filename"));
  imagePaths[0] = reader.nextLine();
}catch(java.io.FileNotFoundException ex) {
  System.err.println("Could not find file to read")''
}
```

- As you can see, there are try/catch blocks surrounding the `Scanner` and `PrintWriter` code blocks. This is because when dealing with files, it is possible to run into errors if the file isn't found by the program. Therefore, we _must_ tell Java how to handle these errors if they occur.

## GUI

### BadGuyCraft

- To make a class a subclass, you must `extend` the superclass. For example, to make `Student` a subclass of `Person` we would say `public class Student extends Person`.

- Using the constructors of superclasses is a very useful tool that can keep a developer from rewriting code. To call a constructor of a superclass, simply use the `super();` keyword.

### Craft

- Software development is all about math. Computers themselves are nothing but complex mathematical machines that use numbers to perform calculations and interact with users! Here, we are going to use an algorithm, or problem-solving template, to move the Craft. *Hint, you may want to use the Math.cos() and Math.sin() method, as well as the Math.PI constant, to solve this problem**.

- To move the craft:
  - x = x + speed * cos(direction * (2 * pi) / 360)
  - y = y + speed * sin(direction * (2 * pi) / 360)

- You will have to convert the algorithm above to java code to complete the move method.

### GamePane

- This class extends `Pane`, which is a JavaFX class that is used to arrange nodes on a screen. To add something like a `Level` object, use the `this.getChildren().add()` method.

- Pane also has a method to set the preferred height and width of a Pane. Try using IntelliJ's autocomplete feature to find the right methods. Type `this.` and scroll through the list of suggested methods.

### StartMenu

- StartMenu is the main class for this program, which means that it has the `main()` method. The `main()` method is the first method called in any Java program. Java programs can be launched from the command line, with specific arguments that are stored in the array `args`. To access the first command line argument, you would use `args[0]`, for the second command line argument, `args[1]`, and so on.

- To set the visible property of UI components, use the `setVisible()` method. `true` means the component is visible and `false` means it is invisible.

- To validate the `GamerID`, see if it contains a space `" "`. There is a method for String that can help with this. Try doing `gamerID.` and looking through the suggested methods. Then, iterate through the `playerProfiles` to see if one matches `gamerID`. If either one of these things are true, return false.

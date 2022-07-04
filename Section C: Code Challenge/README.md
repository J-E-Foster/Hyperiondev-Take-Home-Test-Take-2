<h1>Section C: Code Challenge</h1>

## Option 1: Say the Number

**This solution defines an algorithm that converts any positive or negative integer, up to +-999.999.999.999,999 (i.e. +-999 trillion), into English text, complete with punctuation (commas, hyphens, & full stops)**.

* The solution was implemented using Java.
* A test suite was included using JUnit5.
* For an in-depth explanation of the code, see the [source code](https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test-Take-2/tree/main/Section%20C:%20Code%20Challenge/SayTheNumber/src/numbersayer).
* For references, please see the [source code](https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test-Take-2/tree/main/Section%20C:%20Code%20Challenge/SayTheNumber/src/numbersayer).
* For a Markdown report specifying and justifying the worst-case space complexity, see [this file](REPORT.md).
* For the original problem, visit [this page](https://edabit.com/challenge/4E9gTrRWErpTCA2FQ).<br /><br />

#### Note: this is my second attempt at solving this problem. The first attempt merely adapted the three-digit-group algorithm proposed by Richard Carr, which can be found [**here**](http://www.blackwasp.co.uk/NumberToWords.aspx).<br /><br />

### Changes from the first solution:

* The algorithm now makes use of a recursive instance method.
* More tests classes were added, including a suite class and a runner class.
* The space complexity report was adapted to include an analysis of the method's recursion depth.
* An attempt was made to create a "standalone" app using a JAR file, albeit with limited success due to a class/module configuration issue with JUnit5, similiar to the one described [**here**](https://github.com/junit-team/junit5/issues/2147).<br /><br />

### To run and edit this program, and run test cases:

* You'll need **Java** and an **IDE**
* If you don't have Java, go [**here**](RUNME.md#how-to-install-java).
* If you don't have an IDE - I strongly reccommend Eclipe as this project has JUnit dependencies. Please go [**here**](RUNME.md#how-to-install-eclipse).
* If you already have Java and Eclipse, navigate to this repositories main directory, and download the whole   repository: 
* Click **Code** and then **Download ZIP**:

![](Github-images/0.jpg).
	
* Unzip the folder in your preferred location.
* Navigate to the **'Section C: Code Challenge'** folder, and find the **'NumberSayer'** folder.
* Then follow [**these steps**](RUNME.md#how-to-run-the-program-in-eclipse).<br /><br />

### To merely run the main method of this program without having to install Eclipse:

* You will still need Java.
* Navigate to the **'Section C: Code Challenge'** folder, and locate the JAR file.
* Then follow [**these steps**](RUNJAR.md)


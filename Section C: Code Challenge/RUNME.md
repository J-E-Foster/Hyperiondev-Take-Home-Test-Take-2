# How to install Java and Eclipse

## How to install Java

* Go to [java.com/en/download].
* Click **"Agree and Start Free Download"**:

![](Java-install-images/1.jpg)

* Save file in prefferred location:

![](Java-install-images/2.jpg)

* Run the **.exe** file (double click on it) and click **"Yes"**.
* Tick the **"Change destination folder"** box, click **"Install"**, and wait for installation:

![](Java-install-images/3.jpg)

![](Java-install-images/4.jpg)

* You have successfully installed Java should display after install. Click **"Close"**:

![](Java-install-images/5.jpg)
 	
* To check installation, open **Command Prompt**:

![](Java-install-images/6.jpg)

* Type the following command and press Enter:

```
java -version
```

* The current version should be displayed:

![](Java-install-images/7.jpg)

## How to install Eclipse

To run Java, you need an [**IDE**], we reccommend **Eclipse**.

* Go to [**eclipse.org/downloads**].
* Click "Download":

![](Eclipse-install-images/1.jpg)

* Select Get Eclipse IDE 2021-03 and click **"Download x86_64"**:

![](Eclipse-install-images/2.jpg)

* Save file to preferred location:

![](Eclipse-install-images/3.jpg)
	
* Run the **.exe** file (double click on it) - **eclipseinstaller** should display.

* Select **"Eclipse IDE for Java Developers"**:

![](Eclipse-install-images/4.jpg)

* Choose your destination folder and click **"Install"**:

![](Eclipse-install-images/5.jpg)

* Click **"Accept now"** on the **User Agreement**:

![](Eclipse-install-images/6.jpg)

* Wait for installation and click **"Launch"**:

![](Eclipse-install-images/7.jpg)

![](Eclipse-install-images/8.jpg)

* Choose your destination folder for Eclipse workspace (this will store all your Java projects) and click **"Launch"**:

![](Eclipse-install-images/9.jpg)

* Eclipse Welcome screen should be displayed:

![](Eclipse-install-images/10.jpg)

## How to run the program in Eclipse

* Select **"File > Open Projects from File System"**:

![](Eclipse-install-images/11.jpg)

* Click **Directory** and choose the folder **SayTheNumber** in the **Section C** folder inside the unzipped repo:

![](Eclipse-install-images/12.jpg)

* Click finish*:

![](Eclipse-install-images/13.jpg)

* The **SayTheNumber** project folder should now appear in the **Package Explorer** view toward the left (if you don't see the **Package Explorer** view, select the small **"J"** icon to the right and close **welcome** tab).
* You should also see some red warning icons.
* To get rid of them, you need to add the JUnit5 library to the build path.
* This can be done easily by right-clicking on the folder, selecting **Build Path**, then **Add Libraries**:

![](Eclipse-install-images/14.jpg)

* On the dialogue window, select **JUnit** and click **Next**:

![](Eclipse-install-images/15.jpg)

* Finally, click **Finish**:

![](Eclipse-install-images/16.jpg)
	
* You can now run or edit the program.
* Double click on the **numbersayer** package, then double click on **Main.java**.
* The main file shoul now open in the editor window.
* Click the small green **Run** button in the toolbar, and the program should start:

![](Eclipse-install-images/17.jpg)

* You can now also run the test classes and test suite by clicking on the **numbersayer.tests** package, 
* by similarly selecting a class and clicking **Run**:

![](Eclipse-install-images/18.jpg)<br /><br />

### [Back to Section C](https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test-Take-2/tree/main/Section%20C:%20Code%20Challenge)

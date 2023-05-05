ImageProcessor Assignment
By: Dejah Gadsden and Sonia Randolph (Sonia is no longer in the class :( )
Source of Test Images: test.ppm -> created using Gimp
6color.ppm -> https://web.eecs.utk.edu/~ssmit285/guide/img/index.html
2ColorVert.ppm -> created by cropping 6color.ppm
2ColorHoriz.ppm -> created by cropping 6color.ppm
4ColorPNG.png -> created by cropping 6color.ppm
4ColorJPED.jpg -> created by cropping 6color.ppm
coding.png -> https://www.splitshire.com/developer-79-free-stock-photos/


Assignment 4: Image Processing (Part 1)
IME: Image Manipulation and Enhancement


In our code we designed the following packages, interfaces and classes to adhere to the MVC design
pattern, but we will be creating a View later on.

ImageUtil Class: This class includes the readPPM method that we modified and the main method.

Model Package: This package includes the following interfaces and classes.
    IImage Interface: Interface for images in an image processor
    Image Class: This class implements out IImage interface and implements our getImageName,
    getImageHeight, getImageWidth, copyPixels, and getPixels methods.
    IModel Interface: This interface includes the methods that the image should be able to use.
    ModelImpl: This class implements the IModel interface and contains the implementations of the
    methods within the interface. The methods are imageLoad, imageSave, greyscale, imageFlip,
    imageBrightness, and copyImage. We also added a brightnessClamp helper function that tells us
    if the given value is in the correct range.
    IPixel: This interface represents an individual Pixel and holds information like its position
    and color.
    Pixel: This class implements the IPixel interface and contains methods to easily change
    the fields of a pixel, which is important when manipulating images and their individual pixels.
    The methods included in this class are the getRow, getCol, getR, getG, getB, setRow, setCol,
    setR, setG, and setB.

Controller Package: This package includes the following interfaces and classes.We used the Command
Design Pattern. All commands that users can do are individual classes that implement Commands.
Our controller is thus simplified and more cohesive because it takes in a command from the user,
creates the corresponding Command object, and then executes the command object.
    ImageController Interface: Interface for the controllers for the image processor that
    initializes all the available commands.
    The controller takes in an input and transmits an image with the given manipulations performed.
    ImageControllerImpl Class: This class is the implementation of the controller interface.
    It takes in a model and a readable input (from user). This class contains the method runCommand
    that makes the mutations on the image.
    Commands Package: We used the command design pattern to promote delegation and the details of
    each command are now kept in separate classes, instead of all appearing within the controller.
        ImageProcessorCommands Interface: This interface is for the commands of an image processor.
        Brighten Class: This class implements the ImageProcessorCommands interface and allows the
        user to brighten or darken the image.This class contains the run method.
        Flip Class: This class implements the ImageProcessorCommands interface and allows the user
        to flip the image horizontally or vertically. This class contains the run method.
        Greyscale Class: This class implements the ImageProcessorCommands interface and allows the
        user to convert the image colors to greyscale. This class contains the run method.
        Load Class: This class implements the ImageProcessorCommands interface and allows the user
        to load an image from an ASCII PPM file. This class contains the run method.
        Save Class: This class implements the ImageProcessorCommands interface and allows the user
        to save an image from an ASCII PPM file. This class contains the run method.

Script of Commands our Program will accept:

Load an image from your files and refer to it by a new name
load (file-name)

Save an image to your files from the program
save (image-name, new-version-name)

Greyscale the image based on the red, green, blue, value, intensity, or luma of the image
red-component (image-name, new-version-name)
green-component (image-name, new-version-name)
blue-component (image-name, new-version-name)
value-component (image-name, new-version-name)
intensity-component (image-name, new-version-name)
luma-component (image-name, new-version-name)

Flip the image in specified direction
horizontal-flip image-name, new-version-name)
vertical-flip (image-name, new-version-name)

Brighten the image based on an amount. You can input a positive number to brighten the image
and input a negative value to darken the image.
brighten (value, image-name, new-image-name)

Assignment 5: Image Processing (Part 2)
MIME: More Image Manipulation and Enhancement (Part 2)

Changes in ImageUtil Class: add readImages in order to read not only PPM file but png, jpeg and
other
conventional image formats.

Changes in Model Package: all I/O processes were moved out of this class in order to adhere to the
MVC pattern these methods included imageLoad, imageSave, greyscale, imageFlip, imageBrightness.

A new method called addToVersions was also add to support this change. The method add the given
image to the arraylist of images in the model.

The flip methods were also fixed to work correctly 

Changes in Controller: methods were moved from model to the function objects of the controller
instead. Two new function objects were also added: Script Command, Filter and Transform.

Script Command given a .txt file the program is able to run the program.


Filter the image using the two filter types
blur (image-name, new-version-name)
sharpen (image-name, new-version-name)

Transform the image using the two transformation types
greyscale (image-name, new-version-name)
sepia (image-name, new-version-name)

Assignment: Image Processing (Part 3)
GRIME: Graphical Image Manipulation and Enhancement

For this part of the assignment I added a view to complete the MVC pattern.
I started first by fixing the script implementation. This was one of the changes I added to the Main
class method along with the implementations of the two other options to run the program.

I Then added the view package: Includes the following Class and Interfaces.
    ImageProcessorGUI: Interface for the GUI view of the program.
    ImageProcessorGUIImpl: This class implements ImageProcessorGUI and its methods, importImage,
    histoRefresh, updateImage, UpdateHistogram.
    HistogramView: The class that creates the histogram for an image.
    MainGUI: the class to the main method for the gui version of the program. This was used to view
    the gui throughout the coding.

Other changes to the original:
controller package:
    Added the following classes to the package:
    IGUIController: The interface for the controller to the GUI which allows for the user to
    interact with the program.
    GUIEvents: Interface for the events that we used as action events in the gui.
    GUIController: Class that implements both IGUIController and GUIEvents as well as the
    ActionListener interface. Methods include those from GUIEvents that use the previously made
    commands for the other iteration of the program:
       redComponentEvent, greenComponentEvent, blueComponentEvent, valueComponentEvent,
       intensityComponentEvent, lumaComponentEvent, SaveEvent, LoadEvent, BlurEvent, SharpenEvent,
       BrightenEvent, FlipHorizEvent, FlipVertEvent, SepiaEvent, GreyscaleEvent.
    and those from IGUIController: getImageHistogramValues

    The control its self uses a switch statement to take in the event and determine the output.









# Technology
* Purchased a scanner from Amazon by WoneNice  the scanner itself has no name but the pamphlet it came with has the words ARM 32-bit Cortex V3.10 stamped on every page
	* I have no idea how to set this thing up  the documentation has no directions  just a set of barcodes with labels such as language and print settings
	* I have spent some time researching how to get this device to pair with the BarcodeScanner API for C# - it is not going well  turns out the scanner needs to be configured as a HID.scanner not an HID.keyboard  I cant seem to change it
	* I think the plan is to setup code to handle the device as a keyboard and listen for input from it
	* After further research, the following are possible solutions:
		* Use a regex and a timer to determine if input was from barcode scanner or keyboard
		* Use data streams to separate keyboard input from barcode input
		* Find a way to configure barcode scanner to serial (RS232) and use the virtual com port for it
		* Purchase a new scanner which allows me to use the libraries built for Barcode Scanners
	* So it turns out I can't use the HidDevice library either because the device uses the usage page 0001 - 0006 which are apparently banned by the library - as a result I suppose I'll have to look at one of the other solutions above - I'm thinking either raw data separation or buying a new scanner
* I am using Visual Studio to build a Windows 8.1 application  XAML is proving to be annoying already, but I have been watching tutorials on how to setup a Universal project (assuming I want to port this to a Windows phone)
	* I'm already familiar with C# because of game design - that said, I'm taking this time to actually learn the proper conventions so that I can have a longterm project that can be updated over time
# Gameplan
* The plan is to now just skip automation with the scanner - the UI will have a text box which we can scan codes into and the user can hit search with it
* The next challenge is to actually have that search hit a server which holds the UPC codes - I'm currently running into major issues with the Amazon API
* All of the data will be held in a local SQLite database as a means of providing scalability in the future
	* A lot of research needs to be done in terms of proper table design - for instance, how many tables should I use and how do I properly implement those tables
	* At the moment, I'm looking at have a books table which stores every book in the library
	* I also plan to have a student table which will store a list of students that have checked out books
	* In addition, I think there needs to be a issue and return table for handling the relationship between each student and all the books

# IT-Professional-Skills-Artifact

Link to demonstration: 
http://loom.com/share/96fce278538442078d01ea49fd165398

Project Description: 
This Project is a general converter app. The types of conversions will include: 
1. Currency converter for the Top 10 most used currencies around the world (EUR, USD, GBP, JPY, CAD, AUD, CNY, INR, MXN, CHF)
2. Temperature converter (Celsius(°C), Fahrenheit(°F), Kelvin(K))
3. Distance converter (Kilometers(Km), Miles, Metres)
4. Weight Converter (Kilograms(Kg), Pounds(Lbs), Stone)

The reason I chose a general converter is because it is something people use in their everyday lives. Checking currencies is useful when budgeting for a trip abroad, distance conversion helps when estimating travel time to a destination, temperature conversion is handy when reading forecasts from other countries, and weight conversion is useful when comparing product weights or tracking fitness goals.

This project also demonstrates core Object Oriented Programming concepts covered throughout the module. An abstract Converter class acts as the base for each conversion category, with CurrencyConverter, TemperatureConverter, DistanceConverter and WeightConverter each extending it. This makes use of inheritance, abstraction and polymorphism — the application stores all converters in a Converter array and calls the same convert() method on whichever category the user selects. The GUI was built using Java Swing, which is built into Java and requires no external libraries or frameworks to run

Program Instructions:
Navigate to the GitHub repository and open the src folder. Download the Java files and import them into a Java-supported IDE such as Eclipse. Once imported, open Main.java and select Run As → Java Application to launch the application. Then the GUI should open up and be free to use. I highly recommend using Eclipse IDE as this was the software i use. It is available on most computers in the computer labs in the college.

Technologies Used: 
1. Language -> Java 21
2. Software -> Eclipse IDE (2025)
3. Libraries -> javax.swing - the GUI library used to build the window, buttons, dropdowns etc.
                java.awt - works alongside Swing, handles layouts, colours and fonts
4. Version Control -> Git & GitHub
5. AI -> Claude
6. Demonstration tool -> Loom video 

AI Acknowledgement:
Throughout this project I used Claude (Anthropic) as a learning tool to help me understand Java Swing, a GUI library I had not worked with before. Rather than building the project all at once, Claude explained each component step by step, covering how Swing handles layouts, panels, buttons and event listeners. Which allowed me to understand the code before adding it to the project. Claude also helped articulate and improve code comments to clearly describe what each method and class does. Minor debugging assistance was provided during development, such as resolving issues with the package setup in Eclipse and fixing the GitHub Desktop connection. All code was reviewed and tested by me before being added to the project, and the core logic — including the conversion calculations, class structure and OOP design — reflects patterns and concepts covered throughout the module. Claude also helped me create the .gitignore file as required.

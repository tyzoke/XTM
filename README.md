# XTM

# Screenshot
![alt text](<Screenshot 2024-09-10 065026.png>)

XTM is a desktop application developed using Java 2 Standard Edition (J2SE) that simulates
the functionalities of a real-world ATM. The application is designed to facilitate common banking
operations in a simulated environment, providing users with an interactive and educational
experience.
Technical Features:
● User Authentication:
○ Implements a secure login system that verifies user credentials against a
predefined dataset (stored in a local database).
○ Features robust error handling and user feedback mechanisms for authentication
failures.
● Core Banking Operations:
○ Withdrawal Functionality:
■ Users can withdraw funds, with the application validating the available
balance before processing the transaction.
■ Transaction logs are maintained for auditing and traceability, allowing for
a complete history of user activities.
○ Deposit Functionality:
■ Users can deposit cash, with real-time updates to the account balance.
■ All deposit transactions are recorded in the transaction history for
transparency and accountability.
○ Fund Transfer:
■ Supports intra-account transfers, allowing users to move funds between
their own accounts.
■ Each transfer transaction undergoes validation to ensure sufficient funds
are available, preventing overdrafts.
○ Balance Inquiry:
■ Users can check their current account balance, which is dynamically
retrieved from the user's session data.
● Technical Functionality:
○ Graphical User Interface (GUI):
■ Built using Java Swing, providing an intuitive and responsive interface for
user interactions.
■ Incorporates various Swing components such as buttons, text fields, and
dialog boxes for a user-friendly experience.
○ Error Handling:
■ Implemented comprehensive error handling throughout the application to
manage exceptions and provide meaningful feedback to users.
● Backend Logic:
○ The application follows a modular architecture, separating business logic from
the user interface.
○ Utilizes object-oriented programming principles, including encapsulation and
polymorphism, to enhance maintainability and scalability.
○ The backend handles transaction processing, balance updates, and user session
management, ensuring a smooth flow of operations.
Database Design:
● Database Schema:
○ Utilizes a local database (e.g., SQLite or a simple file-based storage) to store
user accounts and transaction history.
○ Key tables include:
■ Users Table: Stores user credentials (username, password, account
balance).
■ Transactions Table: Logs all transactions (transaction type, amount, date,
user ID) for audit trails.
● Data Integrity:
○ Enforces data integrity through validation checks during transactions, ensuring
that all operations adhere to banking rules.
Security Measures:
● Employs encryption for sensitive data (e.g., user passwords) to protect against
unauthorized access.
● Implements session management to track user activity and prevent session hijacking.
Backend Overview
The backend of the XTM ATM Simulator is primarily responsible for handling user
authentication, processing transactions (withdrawals, deposits, transfers), and managing user
account data. The backend is designed using Java with a focus on object-oriented programming
principles.
Key Components
1. User Class:
○ Purpose: Represents a user of the ATM, encapsulating user-related attributes
and methods.
○ Attributes:
■ username: The unique identifier for the user.
■ password: The user's password (hashed for security).
■ balance: The current balance of the user’s account.
○ Methods:
■ validateUser(): Validates the provided credentials against the stored
user data.
■ withdraw(): Decreases the balance if sufficient funds are available.
■ deposit(): Increases the balance when a deposit is made.
■ transfer(): Handles fund transfers between accounts.
■ checkBalance(): Returns the current account balance.
2. ATM Class:
○ Purpose: Acts as the interface for users to interact with the ATM functionalities.
○ Methods:
■ authenticateUser(): Accepts user input and calls
User.validateUser() to check credentials.
■ processTransaction(): Determines the type of transaction
(withdrawal, deposit, transfer) and invokes the appropriate methods on
the User object.
■ displayMenu(): Presents options to the user for their next action after
login.
■ exit(): Closes the application.
3. Transaction Class
○ Purpose: Represents a transaction and handles logging transactions in a
transaction history.
○ Attributes:
■ transactionID: A unique identifier for the transaction.
■ type: Type of transaction (withdrawal, deposit, transfer).
■ amount: The amount involved in the transaction.
■ date: Timestamp of when the transaction occurred.
○ Methods:
■ logTransaction(): Saves transaction details to a local storage or a
database for record-keeping.
Key Functionalities
● User Authentication:
○ The ATM class invokes the authenticateUser() method, which prompts the
user for their username and password. The credentials are validated using the
validateUser() method from the User class. If the credentials match, the
user is granted access to the ATM functionalities.
● Transaction Processing:
○ After successful authentication, the user can perform transactions. The
processTransaction() method handles the logic for withdrawals, deposits,
and transfers. Each transaction type checks the user's balance or account limits
as needed and updates the balance accordingly.
● Data Persistence:
○ While the current implementation might store user data in memory, a real-world
application would typically interface with a database or a persistent storage
solution to store user accounts and transaction logs.
Example Code Flow
Here’s a simplified code flow:
1. The user launches the application.
2. The ATM class displays a welcome message and calls authenticateUser().
3. If the user enters valid credentials, they are logged in, and the main menu is displayed
via displayMenu().
4. The user selects a transaction type:
○ For a withdrawal, processTransaction() calls User.withdraw(), which
checks if the balance is sufficient before deducting the amount.
○ For a deposit, it calls User.deposit(), which simply adds the amount to the
user's balance.
○ For a transfer, it calls User.transfer(), which involves checking both the
sender's balance and the receiver's account existence.
5. Each transaction logs the details via the Transaction class, if implemented.
Security Measures
● Password Handling:
○ Passwords should be hashed and stored securely to prevent unauthorized
access.
● Input Validation:
○ All user inputs should be validated to prevent issues like SQL injection (if using a
database) or buffer overflows.
The backend flow of the Pin class in the ATM Simulator project can be understood through the
interactions between the GUI components, user input, and database operations. Here’s a
detailed breakdown of the flow:
1. Initialization
● Card Number: When an instance of the Pin class is created, it receives the user's card
number as an argument, which is stored in the cardNo attribute.
● GUI Setup: The constructor sets up the user interface components (labels, password
fields, buttons) and displays the window.
2. User Interaction
● User Input: The user is prompted to enter a new PIN and then confirm it by re-entering
it.
● Button Clicks:
○ The user can either click the "CHANGE" button to submit the new PIN or the
"BACK" button to return to the previous screen.
3. Action Handling
When the user interacts with the buttons, the actionPerformed method is invoked, and the
flow diverges based on the button clicked:
A. Changing the PIN (Button b1)
1. Validation:
○ The method checks if the new PIN (npin) and the re-entered PIN (rpin) are
both filled in.
○ It checks if the two PINs match.
○ If any validation fails, an appropriate error message is displayed using
JOptionPane.
2. Database Update:
○ If validation passes, a new instance of DbConn is created, establishing a
connection to the database.
○ The following SQL command is executed to update the user's PIN in the
database:
db.stmt.executeUpdate("UPDATE login SET pin = '" + npin + "' WHERE cardno =
'" + cardNo + "'");
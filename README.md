# comp3005-a3-q1

Link to the video demo: https://drive.google.com/file/d/1ma25WG8XaRxv1tLRgzM2LxZt8t1G9H-8/view

Database creation:
- created a database called a3 in pgAdmin 4.
  
- then created a table using this query:
  CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    enrollment_date DATE
);

- then used this SQL query to insert initial data:
  INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');


Steps to compile and run the program:
- Type these into the terminal to compile and run:
- javac -cp "../lib/*:" PostgreSQLJDBCConnection.java
- java -cp "../lib/*:." PostgreSQLJDBCConnection



From this point the program should be running in the terminal.
The menu should be displayed in the program and can proceed following the menu.


# Gabriel User Guide



// Product screenshot goes here

## Overview
Gabriel is a chatbot that helps you manage your tasks efficiently through simple command-line inputs.

With Gabriel, you can:
- Add tasks (Todo, Deadline, Event)
- Mark and unmark tasks
- Find task given keyword
- Delete tasks 
- List all tasks
- Automatically save tasks after every command

---

## Getting Started
1. Launch the application using the command 'java -jar ip.jar'
2. Type a command in the input box.
3. Press **Send** or hit **Enter**.
4. Gabriel will respond with a corresponding message.
All tasks are automatically saved after each command. 

---

## Command Guide

### `help`
Shows the list of available commands.
**Format**
```text
help
```
**Output**
Displays all supported commands

---

### `example`
Shows sample commands.
**Format**
```text
example
```
**Output**
Displays example inputs for each command.

---

### `list`
Displays all current tasks.
**Format**
```text
list
```
**Output**
A numbered list of all current tasks.
---

### `mark`
Marks a task as completed.
**Format**
```text
mark <index>
```
**Example**
```text
mark 1
```
**Example Output**
```text
Psy duck psy psy duck duck!
(OK, I've marked this task as done:)
[X] read books
Psy psy duck duck.
(Now you have 1 tasks in your list.)
```
---

### `unmark`
Marks a task as not completed.
**Format**
```text
unmark <index>
```
**Example**
```text
unmark 1
```
**Example Output**
```text
Duck psy duck duck psy psy!
(OK, I've unmarked this task as not done yet:)
[] read books
Psy psy duck duck.
(Now you have 1 tasks in your list.)
```
---

### `find`
Find a task given a keyword.
**Format**
```text
find <keyword>
```
**Example**
```text
find book 
```
**Example Output**
```text
Psy psy psy:
Here are the matching tasks in your list:
1. [D][] return book (by: Dec 02 2019, 6:00PM)
```
---

### `delete`
Delete a task.
**Format**
```text
delete <index>
```
**Example**
```text
delete 1
```
**Example Output**
```text
Psy duck psy psy.
(Noted, I've removed this task:
 [T][]read books
Psy psy duck duck.
(Now you have 0 tasks in your list) 
```
---

### `todo`
Add a todo task.
**Format**
```text
todo <description>
```
**Example**
```text
todo read books
```
**Example Output**
```text
Psyduck psyduck!
(Got it. I've added this task:)
 [T][] read books 
Psy psy duck duck.
(Now you have 1 tasks in your list.)
```
---

### `deadline`
Add a deadline task.
**Format**
```text
deadline <description> /by dd/mm/yy HHmm
```
**Example**
```text
deadline return book /by 2/12/2019 1800
```
**Example Output**
```text
Psyduck psyduck!
(Got it. I've added this task:)
 [D][] return book (by: Dec 02 2019, 6:00PM)
Psy psy duck duck.
(Now you have 1 tasks in your list.)
```
---

### `event`
Add an event task.
**Format**
```text
 <description> /by dd/mm/yy HHmm
```
**Example**
```text
event <description> /from dd/mm/yy HHmm /to dd/mm/yy HHmm
```
**Example Output**
```text
Psyduck psyduck!
(Got it. I've added this task:)
 [E][] project meeting (from: Dec 02 2019, 6:00PM to: Dec 02 2019, 7:00PM)
Psy psy duck duck.
(Now you have 1 tasks in your list.)
```
---

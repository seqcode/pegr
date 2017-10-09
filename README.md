PEGR is a web platform for sequencing metadata management
Grails 2.5.5 and MySQL 

###  Coding Standards
=====================
References
==========
flake8 is recommended for checking code and verifying coding conventional standards:
https://pypi.python.org/pypi/flake8/

Scriting Code Standards follow:
https://www.python.org/dev/peps/pep-0008/

Java Standards
===============
Java is less reliant on the code lay-out. That being said, here are our lab coding standards: 

1. class names always start with upper case;

2. local variables start with lower case;

3. constants use all upper cases; 

4. do not omit {} in if/for/while blocks; follow general indentation rules.

5. do not omit space around = , e.g. def a = 1; 

6. the length of functions/methods should not exceed a full screen. Otherwise, consider refactoring;

7. always catch and log errors;

8. add as many comments and tests as possible;

9. do not put too much logic in a single line of code.

10. DRY: Do not Repeat Yourself.
     

Python Standards
================
Galaxy follows PEP-8, with particular emphasis on the parts about knowing when to be consistent, and readability being the ultimate goal. One divergence from PEP-8 is line length. Logical (non-comment) lines should be formatted for readability, recognizing the existence of wide screens and scroll bars (sometimes a 200 character line is more readable, though rarely).

Use spaces, not tabs for indenting! 4 spaces per indent.

File names must never include capital letters, and words must be separated by underscore. For example, thisIsWrong.py and this_is_right.py.

Comments and documentation comments should follow the 79 character per line rule.

Python docstrings need to be reStructured Text (RST) and Sphinx markup compatible. See https://wiki.galaxyproject.org/Develop/SourceDoc for more information.

Avoid from module import *. It can cause name collisions that are tedious to track down.

Meta-standards
==============
If you want to add something here, submit is as a Merge Request so that another developer can review it before it is incorporated. . These are best practices. They are not rigid. There are no enforcers.



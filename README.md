# PEGR is a web platform for sequencing metadata management
 * Grails 2.5.5 for Linux (x86_64) using readline 5.1 
 * wrapper.dist.url=http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/
 * Java 8 Update 131 build 11;  MySQL Ver 14.14 Distrib 5.5.43;

# Coding Standards
flake8 is recommended for checking code and verifying coding conventional standards:
https://pypi.python.org/pypi/flake8/  based upon PEP-8 with exceptions as noted in the details below: https://www.python.org/dev/peps/pep-0008/

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
Galaxy follows PEP-8, with particular emphasis on the parts about knowing when to be consistent, and readability being the ultimate goal. One divergence from PEP-8 is line length. 

1.  Logical (non-comment) lines should be formatted for readability, recognizing the existence of wide screens and scroll bars        (sometimes a 200 character line is more readable, though rarely).

2.  Use spaces, not tabs for indenting! 4 spaces per indent.

3.  File names must never include capital letters, and words must be separated by underscore. For example, thisIsWrong.py and this_is_right.py.

4.  Comments and documentation comments should follow the 79 character per line rule.

5.  Python docstrings need to be reStructured Text (RST) and Sphinx markup compatible. See https://wiki.galaxyproject.org/Develop/SourceDoc for more information.

6.  Avoid from module import *. It can cause name collisions that are tedious to track down.


Meta-standards
==============
If you want to add something here, submit is as a Merge Request so that another developer can review it before it is incorporated. . These are best practices. They are not rigid. There are no enforcers.


# Reference Books
Original development used "Grails-in-Action" textbook examples to build PEGR and the APIs.mysql:
[https://www.amazon.com/Grails-Action-Glen-Smith/dp/1933988932/ref=sr_1_3?ie=UTF8&qid=1505746618&sr=8-3&keywords=grails+in+action]
bash-3.2$ cat workspace/graina2-1.0/README
This directory contains the source code for the examples used in the book Grails in Action as well as the sample application that we create in the book: Hubbub.

Baseline Assumptions about Platform Interoperability
====================================================
* PEGR only collects meta-data information from core pipeline run off sequencer.
* Galaxy platform is where further downstream analysis is performed.
* PEGR will point the user to the right sample/dataset Galaxy history to work from.
* PEGR collects only what is necessary and expedient for the visual summary report.
* PEGR MUST retain what information does not change from one sequencing run to the next, for each run of the pipeline.
* Anything prior to and including alignment will be named fields, everything after will be sent/stored in a blob (json dict).

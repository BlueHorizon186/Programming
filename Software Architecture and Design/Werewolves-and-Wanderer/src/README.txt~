= Application Design and Architecture

Authors::   Ivan Diaz A01371166, Jonathan Patlan A01372223
Date::      May 5, 2016


We focus on the design and writing of a Ruby web application that allows playing the revised and improved version of “Werewolves and Wanderer” text adventure game.
The directory structure for the application and its documentation is as follows:
              
    Werewolves-and-Wanderer/
         ├─ doc/                             Folder produced by RDoc.
         ├─ images/                          Folder for the documentation's image files.
         └─ src/                             Folder for the application's source code.
              ├─ adventure_service.rb  
              ├─ console_version.rb
              ├─ db/                         Folder for the database files.
              │   └─ yaml/                   Folder for auxiliary files of player's progress.
              ├─ public/                     Folder for the server's public documents.
              │       ├─ bootstrap-3.3.6/    Folder for Bootstrap.
              │       ├─ css/                Folder for the application's CSS files.
              │       ├─ images/             Folder for the images and backgrounds used.
              │       └─ js/                 Folder for the animation's Javascript files.
              ├─ models/                     Folder for the application's models.
              └─ views/                      Folder for the application's views (ERB files).

This is the command used to produce this documentation (running it from the +Werewolves-and-Wanderer+ directory):

  rdoc --exclude ".yaml|.css|.js|.db|.sql" src

The root of the documentation should now be available at: +Werewolves-and-Wanderer/doc/index.html+

== Installing and Running the Application

To run the _Werewolves and Wanderer_ web server you have to to install the <em>rubytree</em> {\http://rubytree.anupamsg.me/} gem first. Type the following command at your terminal:

  sudo gem install rubytree

Then, to run the web server, you need to type the following command at the terminal from the +Werewolves-and-Wanderer/src+ directory:

  ruby -I. adventure_server.rb

Afterwards, point your web brower at the following URL: +http://server-name/+ (e.g. +http://localhost:8080+)

== 4+1 Architectural View Model

Wesley use Philippe Kruchten's <b>“4+1 View Model”</b> to document the application's architecture. 

=== Logical View

The logical view contains information about the various parts of the system. In UML the logical view is typically modelled using <em>class diagrams</em>.

The following figure represents the UML class diagram:

link:../images/class_diagram.png

=== Process View

The process view describes the concurrent processes within the system. In UML, <em>activity diagrams</em> are used to model this view.

link:../images/activity_diagram.png

=== Development View
The development view focusses on software modules and subsystems. In UML, <em>package diagrams</em> are used to model the development view.

link:../images/package_diagram.png

=== Physical View

The physical view describes the physical deployment of the system, revealing which pieces of software run on what pieces of hardware. In UML, <em>deployment diagrams</em> are used to model the physical view.

link:../images/deployment_diagram.png

=== Scenarios
This view describes the functionality of the system from the perspective from outside world. It contains diagrams describing what the system is supposed to do from a black box perspective. UML <em>use case diagrams</em> are used for this view.

link:../images/use_case_diagram.png

== Patterns Used

- <b>Domain-Specific Language</b>: The +adventure_server.rb+ file consists of a series of Sinatra _routes_. Sinatra is a DSL for creating web applications in Ruby.
- <b>Singleton</b>: The Database Manager is implemented as a Singleton because there is no need to create a new instance every time a player logs in or signs up. One can handle all the required petitions.
- <b>Factory</b>: Since each game instance can either create a new Player object from scratch, or one from retrieved information from the Database, a Factory can better define how the game instances are created.


== References

- "Game Programming Patterns" book by Robert Nystrom.

- About.com. <em>One Tree to Rule Them All.</em> {\http://ruby.about.com/od/textadventure/ss/One-Tree-To-Rule-Them-All.htm} Accessed April 20, 2016.
- Codebar.io. <em>Object Oriented Ruby and Inheritance.</em> {\http://tutorials.codebar.io/ruby/lesson5/tutorial.html} Accessed April 19, 2016.
- Tutorialspoint.com. <em>SQLite - CREATE Table.</em> {\http://www.tutorialspoint.com/sqlite/sqlite_create_table.htm} Accessed April 25, 2016.
- Tutorialspoint.com. <em>SQLite - Data Type.</em> {\http://www.tutorialspoint.com/sqlite/sqlite_data_types.htm} Accessed April 25, 2016.
- W3Schools. <em>Html, CSS, jQuery, Bootstrap.</em> {\http://www.w3schools.com/} Accessed May 5, 2016.

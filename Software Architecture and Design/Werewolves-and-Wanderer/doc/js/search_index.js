var search_data = {"index":{"searchIndex":["databasemanager","event","gameinstance","gameinstancefactory","gametree","object","player","room","apply_event_effects()","begin_game()","initialize_rooms_and_events()","insert()","load_game()","move_to_choice()","new()","new()","new()","new()","new()","new_game()","new_instance()","play_next()","print_map()","refresh_game()","retrieve()","to_s()","to_s()","to_s()","trigger()","update_yaml()","victory?()","licence","readme","readme.txt~","glyphicons-halflings-regular.svg"],"longSearchIndex":["databasemanager","event","gameinstance","gameinstancefactory","gametree","object","player","room","gameinstance#apply_event_effects()","gameinstance#begin_game()","gametree#initialize_rooms_and_events()","databasemanager#insert()","gameinstancefactory::load_game()","gameinstance#move_to_choice()","event::new()","gameinstance::new()","gametree::new()","player::new()","room::new()","gameinstancefactory::new_game()","gameinstancefactory::new_instance()","gameinstance#play_next()","gametree#print_map()","gameinstancefactory::refresh_game()","databasemanager#retrieve()","gameinstance#to_s()","player#to_s()","room#to_s()","event#trigger()","gameinstance#update_yaml()","event#victory?()","","","",""],"info":[["DatabaseManager","","DatabaseManager.html","","<p>The <code>DatabaseManager</code> is an implementation of the Singleton\nPattern. Its purpose is to connect the game …\n"],["Event","","Event.html","","<p>The <code>Event</code> class represents an object containing the\nevent&#39;s information, as well as its effects on …\n"],["GameInstance","","GameInstance.html","","<p>The <code>GameInstance</code> class represents an object that will manage\neach individual player&#39;s game. It is …\n"],["GameInstanceFactory","","GameInstanceFactory.html","","<p>The <code>GameInstanceFactory</code> is an implementation of the Simple\nFactory Pattern. It allows you to create instances …\n"],["GameTree","","GameTree.html","","<p>The <code>GameTree</code> class represents an object that holds a tree data\nstructure via means of the gem <code>RubyTree</code> …\n"],["Object","","Object.html","",""],["Player","","Player.html","","<p>The <code>Player</code> class represents an object that contains all the\nbasic information of a registered player. …\n"],["Room","","Room.html","","<p>The <code>Room</code> class represents an object that contains all the\ninformation about a given room of the mansion …\n"],["apply_event_effects","GameInstance","GameInstance.html#method-i-apply_event_effects","()","<p>Whenever an event is triggered, it impacts the player somehow. This method\nupdates the player&#39;s stats …\n"],["begin_game","GameInstance","GameInstance.html#method-i-begin_game","()","<p>Sets the player&#39;s current room to the castle&#39;s entrance and\nintroduces him/her to the game.\n<p>Returns … &mdash; "],["initialize_rooms_and_events","GameTree","GameTree.html#method-i-initialize_rooms_and_events","()","<p>Adds all remaining rooms and events to the entrance node, generating the\nentire game tree.\n"],["insert","DatabaseManager","DatabaseManager.html#method-i-insert","(*user_data)","<p>Inserts a new player&#39;s information to the database. (Pending…)\n<p>Parameter &mdash; <p>user_data &mdash; An array with …\n\n"],["load_game","GameInstanceFactory","GameInstanceFactory.html#method-c-load_game","(user, password)","<p>Reads a previously registered player record from the database and returns\nits id.\n<p>Parameters &mdash; <p>user &mdash; The …\n"],["move_to_choice","GameInstance","GameInstance.html#method-i-move_to_choice","(choice)","<p>Updates the player&#39;s <strong>current_room</strong> status according to\nhis/her input selection.\n<p>Parameter &mdash; <p>choice &mdash; The …\n\n\n"],["new","Event","Event.html#method-c-new","(desc, str, wlth, battle = false, \\ vict_msg = nil, fail_msg = nil)","<p>Creates a new <code>Event</code> instance.\n<p>Parameters &mdash; <p>desc &mdash; The event&#39;s description.\n<p>str &mdash; The event&#39;s effect on …\n"],["new","GameInstance","GameInstance.html#method-c-new","(player)","<p>Creates a new <code>GameInstance</code> instance. Do not call this method\ndirectly. Use the GameInstanceFactory::new_instance …\n"],["new","GameTree","GameTree.html#method-c-new","()","<p>Creates a new <code>GameTree</code> instance.\n"],["new","Player","Player.html#method-c-new","(options={:id=>0, :name=>\"P\", :strength=>50, :wealth=>50, \\ :monster_tally=>0, :current_room=>nil})","<p>Creates a new <code>Player</code> instance with the provided values, or the\ndefault ones, should it be a newly registered …\n"],["new","Room","Room.html#method-c-new","(name, desc, choices = [], events = false)","<p>Creates a new <code>Room</code> instance with the given description.\n<p>Parameters &mdash; <p>name &mdash; The room&#39;s name.\n<p>desc &mdash; The room&#39;s …\n"],["new_game","GameInstanceFactory","GameInstanceFactory.html#method-c-new_game","(user, password)","<p>Creates a new fresh game and registers the new player in the database. The\n<code>DatabaseManager</code> is called …\n"],["new_instance","GameInstanceFactory","GameInstanceFactory.html#method-c-new_instance","(pl, pl_id)","<p>Creates the new <code>GameInstance</code> object. It is called by means of\nany of the previous methods.\n<p>Parameter &mdash; "],["play_next","GameInstance","GameInstance.html#method-i-play_next","()","<p>The game&#39;s main loop. It is in charge of handling player interaction\nand displaying the player&#39;s …\n"],["print_map","GameTree","GameTree.html#method-i-print_map","()","<p>Calls the <code>RubyTree</code> <strong>print_tree</strong> method to print\nall the nodes so far. Used for debugging purposes only. …\n"],["refresh_game","GameInstanceFactory","GameInstanceFactory.html#method-c-refresh_game","(pl_id)","<p>Looks for the given player&#39;s YAML file and reads his/her previously\nsaved <code>GameInstance</code>.\n<p>Parameter … &mdash; "],["retrieve","DatabaseManager","DatabaseManager.html#method-i-retrieve","(*user_data)","<p>Searches the database for the given player to retrieve his/her information.\n<p>Parameter &mdash; <p>user_data &mdash; An array …\n\n"],["to_s","GameInstance","GameInstance.html#method-i-to_s","()","<p>Get a string containing the representation for this game instance. Used for\ndebugging purposes.\n<p>Returns … &mdash; "],["to_s","Player","Player.html#method-i-to_s","()","<p>Get a string containing the representation for this player object.\n<p>Returns &mdash; This player&#39;s attributes …\n\n"],["to_s","Room","Room.html#method-i-to_s","()","<p>Get a string with all the room&#39;s necessary information for the player\nto be able to take a decision …\n"],["trigger","Event","Event.html#method-i-trigger","()","<p>Enables the <em>triggered</em> flag to mark this event as triggered.\n<p>Returns &mdash; The battle&#39;s outcome or nil if …\n\n"],["update_yaml","GameInstance","GameInstance.html#method-i-update_yaml","()","<p>After each one of the player&#39;s choices, his/her respective YAML file is\nupdated with the appropriate …\n"],["victory?","Event","Event.html#method-i-victory-3F","(rng = rand(100))","<p>Determines if the player won the battle should the event include one. This\nis determined by a random …\n"],["Licence","","src/Licence_txt.html","","<p>GNU GENERAL PUBLIC LICENSE\n<p>Version 3, 29 June 2007\n<p>Copyright (C) 2007 Free Software Foundation, Inc. &lt; …\n"],["README","","src/README_txt.html","","<p>Application Design and Architecture\n<p>Authors &mdash; Ivan Diaz A01371166, Jonathan Patlan A01372223\n<p>Date &mdash; May 5, …\n"],["README.txt~","","src/README_txt~.html","","<p>Application Design and Architecture\n<p>Authors &mdash; Ivan Diaz A01371166, Jonathan Patlan A01372223\n<p>Date &mdash; May 5, …\n"],["glyphicons-halflings-regular.svg","","src/public/bootstrap-3_3_6/fonts/glyphicons-halflings-regular_svg.html","","<p>&lt;?xml version=“1.0” standalone=“no”?&gt; &lt;!DOCTYPE svg PUBLIC\n“-//W3C//DTD …\n"]]}}
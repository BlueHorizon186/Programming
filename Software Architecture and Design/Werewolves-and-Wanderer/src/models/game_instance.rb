# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_instance.rb

require_relative './game_tree'

# The +GameInstance+ class represents an object that will
# manage each individual player's game. It is in charge of
# handling their inputs, events according to said inputs,
# and managing the game progress in general.
class GameInstance

  # The player this GameInstance will be managing.
  attr_reader :player

  # The tree representing the map of the entire game. Uses the
  # RubyTree gem.
  attr_reader :game_map

  # Creates a new +GameInstance+ instance. Do not call this
  # method directly. Use the GameInstanceFactory::new_instance
  # instead because each player's data must be recorded in the
  # database, if it's a new player account, or retrieved if
  # it's a previously registered player.
  #
  # Parameter::
  #   player:: The player this GameInstance is in charge of.
  def initialize(player)
    @player = player
    @game_map = GameTree.new
  end

  # Sets the player's current room to the castle's entrance and
  # introduces him/her to the game.
  #
  # Returns:: A string with a brief introduction to the game.
  def begin_game
    @player.curr_room = @game_map.entrance
    "Welcome to Werewolves and Wanderer!\n
    Do you have what it takes to overcome the fearsome obstacles
    lurking inside the castle?\n
    Click the \"Begin\" button to start and find out!"
  end

  # The game's main loop. It is in charge of handling player
  # interaction and displaying the player's and the game's
  # current state information. It ends whenever the player
  # decides to save and quit or he/she loses all the strength.
  # Calls the +begin_game+ method if the player creates a
  # fresh new game.
  def play
    if @player.curr_room.nil? then begin_game end
  end

  # Get a string containing the representation for this
  # game instance. Used for debugging purposes.
  #
  # Returns:: This game instance's attributes as a string.
  def to_s
    "Instance's Player: #{@player.to_s}\n"
  end

end

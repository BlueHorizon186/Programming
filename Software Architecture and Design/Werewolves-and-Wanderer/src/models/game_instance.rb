# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_instance.rb

require 'yaml'
require_relative './game_tree'

YAML_FILES_PATH = "db/yaml"

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
    update_yaml
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
  def play_next
    begin_game if @player.curr_room.nil?
    @player.curr_room.content
  end

  # Whenever an event is triggered, it impacts the player
  # somehow. This method updates the player's stats accordingly.
  def apply_event_effects
    @player.strength += @player.curr_room.content.str_effect
    @player.wealth += @player.curr_room.content.wlth_effect
    update_yaml
  end

  # Updates the player's *current_room* status according to
  # his/her input selection.
  #
  # Parameter::
  #   choice:: The player's selection.
  def move_to_choice(choice)
    if @player.curr_room.name == 'Entrance'
      @player.curr_room = @player.curr_room[0]

    elsif @player.curr_room.content.is_a?(Event)
      @player.curr_room = @player.curr_room.parent
    else
      if choice == 0 then @player.curr_room = @player.curr_room.parent
      else @player.curr_room = @player.curr_room[choice - 1]
      end
    end

    update_yaml
  end

  # After each one of the player's choices, his/her respective
  # YAML file is updated with the appropriate information, which
  # is stored in this player object instance.
  def update_yaml
    file_path = "#{YAML_FILES_PATH}/y_#{@player.id}.yaml"
    data = YAML.load_file(file_path)

    data.player.strength = @player.strength
    data.player.wealth = @player.wealth
    data.player.monster_tally = @player.monster_tally
    data.player.curr_room = @player.curr_room

    File.open(file_path, 'w') { |f| YAML.dump(data, f) }
  end

  # Get a string containing the representation for this
  # game instance. Used for debugging purposes.
  #
  # Returns:: This game instance's attributes as a string.
  def to_s
    "Instance's Player: #{@player.to_s}\n"
  end

end

# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/core/game_instance_factory.rb

require '../player'
require 'game_instance'

# The +GameInstanceFactory+ is an implementation of the {Simple
# Factory Pattern}[https://en.wikipedia.org/wiki/Factory_method_pattern].
# It allows you to create instances of the games for each required
# player, whether it's a new game or load a preexistent one from the
# players database.
class GameInstanceFactory

  @@player = nil

  # Get the input player's record from the database, if any.
  #
  # Parameters::
  #   user:: The player's name
  #   password:: The player's password
  #
  # Returns:: An array with the player's data if it exists,
  #           or *nil* otherwise.
  def self.load_game(user, password)
    nil
  end

  # Creates a new +GameInstance+ with the retrieved player's
  # progress or a completely clean start of the game if none
  # was found.
  #
  # Returns:: The generated +GameInstance+ object.
  def self.new_instance
    if self.load_game("user", "password")
      @@player = Player.new("Read the values from the Database")
    else
      @@player = Player.new("Player")
    end
    GameInstance.new(@@player)
  end

end

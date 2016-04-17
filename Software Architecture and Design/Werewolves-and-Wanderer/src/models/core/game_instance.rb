# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/core/game_instance.rb

# The +GameInstance+ class represents an object that manages
# each player's progress and loads the respective rooms
# of the map.
class GameInstance

  # The player this +GameInstance+ will be managing.
  attr_reader :player

  # Creates a new instance of the game. Do not call this
  # method directly. Instead, make sure to create it by
  # means of the GameInstanceFactory::new_instance factory
  # method. This is because the database must be searched
  # to get if it's a new player or a preexistent one.
  #
  # Parameters::
  #   player:: The player this game instance is in charge of.
  def initialize(player)
    @player = player
  end

end

# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_instance.rb

# The +GameInstance+ class represents an object that will
# manage each individual player's game. It is in charge of
# handling their inputs, events according to said inputs,
# and managing the game progress in general.
class GameInstance

  # The player this GameInstance will be managing.
  attr_reader :player

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
  end

end

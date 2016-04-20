# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/player.rb

# The +Player+ class represents an object that stores
# each stored player's stats.
class Player

  # The player's name
  attr_reader :name

  # The player's strength: It represents his/her energy.
  # When it's depleted, it's game over.
  attr_accessor :strength

  # Number of dollars the player has.
  attr_accessor :wealth

  # Number of monsters the player has defeated so far.
  attr_accessor :monster_tally

  # Room where the player is currently located.
  attr_accessor :curr_room

  # Creates a new +Player+ instance. Before creating it, make
  # sure it does not previously exist in the Sqlite database.
  #
  # Parameters::
  #   name:: The player's name.
  #   strength:: The player's strength. If this player is not
  #              registered in the database, +strength+ is
  #              initialised with the default starting value.
  #   wealth:: The player's wealth. Its value is either the
  #            stored one in the database, or the default one.
  #   monsters:: The number of monsters the player has defeated
  #              so far.
  #   room:: The room the player is in. Defaults to nil.
  def initialize(name, strength = 50, wealth = 50, monsters = 0, room = nil)
    @name = name
    @strength = strength
    @wealth = wealth
    @monster_tally = monsters
    @curr_room = room
  end

end

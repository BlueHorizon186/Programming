# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/player.rb

# The +Player+ class represents an object that contains
# all the basic information of a registered player. It
# is managed during the course of any game.
class Player

  # The player's username.
  attr_reader :name

  # The player's current _strength_.
  attr_accessor :strength

  # The player's current _wealth_.
  attr_accessor :wealth

  # The number of enemies the player has defeated.
  attr_accessor :monster_tally

  # The room's id the player is currently in.
  attr_accessor :curr_room

  # Creates a new +Player+ instance with the provided values,
  # or the default ones, should it be a newly registered player.
  # The constructor arguments are passed in a hash with the
  # followingly described keys.
  #
  # Parameters::
  #   name:: The player's username.
  #   strength:: The player's current _strength_. Starting is 50.
  #   wealth:: The player's current _wealth_. Starting is 50.
  #   monsters:: The total amount of defeated enemies in this run.
  #   room:: The room the player is currently in.
  def initialize(options={:name=>"P", :strength=>50, :wealth=>50, \
                          :monster_tally=>0, :current_room=>nil})
    @name = options[:name]
    @strength = options[:strength]
    @wealth = options[:wealth]
    @monster_tally = options[:monster_tally]
    @curr_room = options[:current_room]
  end

  # Get a string containing the representation for this
  # player object.
  #
  # Returns:: This player's attributes as a string.
  def to_s
    "Name: #{@name} -> Strength: #{@strength} -> Wealth: #{@wealth} \
    -> Monsters: #{@monster_tally} -> Current Room: #{@curr_room}\n"
  end

end

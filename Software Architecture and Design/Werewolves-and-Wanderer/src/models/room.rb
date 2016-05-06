# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Authors: A01371166 Ivan David Diaz Sanchez
#          A01372223 Jonathan Patlan

# File: models/room.rb

# The +Room+ class represents an object that contains
# all the information about a given room of the mansion
# in the game.
class Room

  # The room's name (e.g. Entrance, Great Hall, etc).
  attr_reader :name

  # The room's description.
  attr_reader :description

  # The possible choices the player can make in this room.
  attr_reader :choices

  # Some rooms in the game trigger events automatically.
  # This variable stores whether the room contains events or not.
  attr_accessor :has_events

  # Creates a new +Room+ instance with the given description.
  #
  # Parameters::
  #   name:: The room's name.
  #   desc:: The room's description.
  #   choices:: The room's possible choice alternatives.
  #   event:: The room's automatic event(s) or nil if it
  #           has none.
  def initialize(name, desc, choices = [], events = false)
    @name = name
    @description = desc
    @choices = choices
    @has_events = events
  end

  # Get a string with all the room's necessary information for
  # the player to be able to take a decision on what he/she
  # should do next.
  #
  # Returns:: The room's attributes in a built string.
  def to_s
    "#{@name}:\n#{@description}"
  end

end

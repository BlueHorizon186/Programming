# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/room.rb

# The +Room+ class represents an object that contains
# all the information about a given room of the mansion
# in the game.
class Room

  # The room's name (e.g. Entrance, Great Hall, etc).
  attr_reader :name

  # The room's description.
  attr_reader :description

  # Creates a new +Room+ instance with the given description.
  #
  # Parameters::
  #   name:: The room's name.
  #   desc:: The room's description.
  def initialize(name, desc)
    @name = name
    @description = desc
  end

  # Get a string with all the room's necessary information for
  # the player to be able to take a decision on what he/she
  # should do next.
  #
  # Returns:: The room's attributes in a built string.
  def to_s
    "#{@name}\n#{@description}"
  end

end

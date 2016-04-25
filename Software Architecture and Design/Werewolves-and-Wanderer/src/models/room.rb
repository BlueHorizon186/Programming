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

  # The possible choices the player can make in this room.
  attr_reader :choices

  # Creates a new +Room+ instance with the given description.
  #
  # Parameters::
  #   name:: The room's name.
  #   desc:: The room's description.
  #   choices:: The room's possible choice alternatives.
  def initialize(name, desc, choices = [])
    @name = name
    @description = desc
    @choices = choices
  end

  # Numbers the possible decisions listed in the _choices_
  # array and builds a list with it. Used in the console
  # version of the game only.
  #
  # Returns:: A string with the choices' numbered list.
  def show_choices
    decision_list = "\n"
    choices.each_with_index {|e, i| decision_list << "#{i+1}. #{e}\n"}
    decision_list
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

# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/event.rb

# The +Event+ class represents an object containing
# the event's information, as well as its effects on
# the player that triggered it.
class Event

  # What occurs when the event is triggered.
  attr_reader :description

  # The event's effect on the player's strength.
  attr_reader :str_effect

  # The event's effect on the player's wealth.
  attr_reader :wlth_effect

  # Creates a new +Event+ instance.
  #
  # Parameters::
  #   desc:: The event's description.
  #   str:: The event's effect on the player's strength.
  #   wlth:: The event's effect on the player's wealth.
  def initialize(desc, str, wlth)
    @description = desc
    @str_effect = str
    @wlth_effect = wlth
  end
  
end

# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Authors: A01371166 Ivan David Diaz Sanchez
#          A01372223 Jonathan Patlan

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

  # A flag indicating if the event has already been triggered.
  attr_reader :triggered

  # A flag indicating if the event includes a battle.
  attr_reader :battle

  # The displayed message if the player wins the battle.
  attr_reader :victory_msg

  # The displayed message if the player loses the battle.
  attr_reader :failure_msg

  # Creates a new +Event+ instance.
  #
  # Parameters::
  #   desc:: The event's description.
  #   str:: The event's effect on the player's strength.
  #   wlth:: The event's effect on the player's wealth.
  #   battle:: Whether the event includes a battle or not.
  def initialize(desc, str, wlth, battle = false, \
                 vict_msg = nil, fail_msg = nil)
    @description = desc
    @str_effect = str
    @wlth_effect = wlth
    @triggered = false
    @battle = battle
    @victory_msg = vict_msg
    @failure_msg = fail_msg
  end

  # Enables the _triggered_ flag to mark this event as triggered.
  #
  # Returns:: The battle's outcome or nil if the event does not
  #           include a battle.
  def trigger
    @triggered = true
    return victory? if battle
    return nil
  end

  # Determines if the player won the battle should the event
  # include one. This is determined by a random number.
  #
  # Parameter::
  #   rng:: The Random Number defining the battle's outcome.
  #
  # Returns:: True if the player wins of False otherwise.
  def victory?(rng = rand(100))
    unless rng >= 50
      @str_effect = @str_effect * -1
      @wlth_effect = @wlth_effect * -1
      return false
    end
    return true
  end
  
end

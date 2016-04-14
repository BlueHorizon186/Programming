#==========================================================
# Student Name: Ivan David Diaz Sanchez
# Student Id: A01371166
#==========================================================

#==========================================================
# Code for question 1

class Troll
  attr_reader :name, :power
  def initialize(name, power)
    @name = name
    @power = power
  end
  def to_s
    "#{ @name } (#{ @power})"
  end
end

#----------------------------------------------------------
# Place here your code for these classes: TrollArmy,
# NameFormationStrategy, and PowerFormationStrategy.
#----------------------------------------------------------

class TrollArmy
  attr_accessor :formation_strategy
  attr_reader :troll_soldiers

  def initialize
    @formation_strategy = nil
    @troll_soldiers = []
  end

  def add(t)
    @troll_soldiers.push(t)
    self
  end

  def set_formation
    unless (@formation_strategy.nil?)
      @formation_strategy.arrange_army(@troll_soldiers)
    end
  end

  def attack
    set_formation()
    if (@formation_strategy == nil) then
      puts('Undefined strategy, cannot attack.')

    else
      puts('*** Attack formation ***')
      for t in @troll_soldiers
        puts(t.to_s)
      end
    end
  end
end

class NameFormationStrategy
  def arrange_army(army)
    army.sort! { |x, y| x.name <=> y.name }
  end
end

class PowerFormationStrategy
  def arrange_army(army)
    army.sort! { |x, y| y.power <=> x.power }
  end
end

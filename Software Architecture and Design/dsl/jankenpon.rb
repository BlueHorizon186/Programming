# Domain-Specific Language Pattern
# Date: 30-Mar-2016
# Author:
#          A01371166 Ivan David Diaz Sanchez

# File: jankenpon_test.rb

ROCK = 0
PAPER = 1
SCISSORS = 2
LIZARD = 3
SPOCK = 4

class GameChoice

  attr_reader :id, :name, :win_conditions
  @id = nil
  @win_conditions = []

  def self.id
    return @id
  end

  def self.+(rival)
    winner = @win_conditions[rival.id]

    if winner
      puts("#{self} #{attack(rival)} #{rival} (winner #{self})")
      return self

    else
      puts("#{rival} #{rival.attack(self)} #{self} (winner #{rival})") \
        unless winner.nil?

      puts("#{self} tie (winner #{self})") if winner.nil?
      return rival
    end
  end

  def self.-(rival)
    winner = @win_conditions[rival.id]

    unless winner
      puts("#{rival} #{rival.attack(self)} #{self} (loser #{self})") \
        unless winner.nil?

      puts("#{self} tie (winner #{self})") if winner.nil?
      return self
    end

    puts("#{self} #{attack(rival)} #{rival} (loser #{rival})")
    return rival
  end

  def self.attack(*against)
    raise "Not a valid choice for the game."
  end

end

class Rock < GameChoice

  @id = ROCK
  @win_conditions = [nil, false, true, true, false]

  def self.attack(*against)
    "crushes"
  end

end

class Paper < GameChoice

  @id = PAPER
  @win_conditions = [true, nil, false, false, true]

  def self.attack(against)
    return "disproves" if against.id == SPOCK
    return "covers"
  end

end

class Scissors < GameChoice

  @id = SCISSORS
  @win_conditions = [false, true, nil, true, false]

  def self.attack(against)
    return "decapitate" if against.id == LIZARD
    return "cut"
  end

end

class Lizard < GameChoice

  @id = LIZARD
  @win_conditions = [false, true, false, nil, true]

  def self.attack(against)
    return "eats" unless against.id == SPOCK
    return "poisons"
  end

end

class Spock < GameChoice

  @id = SPOCK
  @win_conditions = [true, false, true, false, nil]

  def self.attack(against)
    return "smashes" if against.id == SCISSORS
    return "vaporizes"
  end

end

def show(winner)
  puts("Result = #{winner}")
end

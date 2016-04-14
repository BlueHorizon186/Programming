# Decorator Pattern
# Date: 16-Mar-2016
# Author:
#          A01371166 Ivan David Diaz Sanchez

# File: coffee.rb

class Beverage

  attr_reader :description, :cost

  def initialize
    @description = ""
    @cost = 0.0
  end

  def cost
    @cost.round(2)
  end

end

class DarkRoast < Beverage

  def initialize
    @description = "Dark Roast Coffee"
    @cost = 0.99
  end

end

class Espresso < Beverage

  def initialize
    @description = "Espresso"
    @cost = 1.99
  end

end

class HouseBlend < Beverage

  def initialize
    @description = "House Blend Coffee"
    @cost = 0.89
  end

end

class CondimentDecorator < Beverage

  def initialize(beverage)
    @description = beverage.description
    @cost = beverage.cost
  end

end

class Mocha < CondimentDecorator

  def initialize(beverage)
    @description = beverage.description.concat(", Mocha")
    @cost = beverage.cost + 0.20
  end

end

class Whip < CondimentDecorator

  def initialize(beverage)
    @description = beverage.description.concat(", Whip")
    @cost = beverage.cost + 0.10
  end

end

class Soy < CondimentDecorator

  def initialize(beverage)
    @description = beverage.description.concat(", Soy")
    @cost = beverage.cost + 0.15
  end

end

class Milk < CondimentDecorator

  def initialize(beverage)
    @description = beverage.description.concat(", Milk")
    @cost = beverage.cost + 0.10
  end

end

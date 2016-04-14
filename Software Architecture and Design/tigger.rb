# File: tigger.rb

class Tigger

  @@instance = Tigger.new

  def self.instance
    return @@instance
  end

  def to_s
    return "I'm the only one!"
  end

  def roar
    'Grrr!'
  end

  private_class_method :new

end

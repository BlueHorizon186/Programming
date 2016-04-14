# Command Pattern
# Date: 24-Feb-2016
# Authors:
#          A01371166 Ivan David Diaz Sanchez

# File: control.rb

class RemoteControlWithUndo

  def initialize
    @on_commands = []
    @off_commands = []
    no_command = NoCommand.new
    7.times do
      @on_commands << no_command
      @off_commands << no_command
    end
    @undo_command = no_command
  end

  def set_command(slot, on_command, off_command)
    @on_commands[slot] = on_command
    @off_commands[slot] = off_command
  end

  def on_button_was_pushed(slot)
    @on_commands[slot].execute
    @undo_command = @on_commands[slot]
  end

  def off_button_was_pushed(slot)
    @off_commands[slot].execute
    @undo_command = @off_commands[slot]
  end

  def undo_button_was_pushed()
    @undo_command.undo
  end

  def inspect
    string_buff = ["\n------ Remote Control -------\n"]
    @on_commands.zip(@off_commands).each_with_index do |commands, i|
      on_command, off_command = commands
      string_buff << "[slot #{i}] #{on_command.class}  #{off_command.class}\n"
    end
    string_buff << "[undo] #{@undo_command.class}\n"
    string_buff.join
  end

end

class NoCommand

  def execute
  end

  def undo
  end

end

class Light

  attr_reader :level

  def initialize(location)
    @location = location
    @level = 0
  end

  def on
    @level = 100
    puts("Light is on")
  end

  def off
    @level = 0
    puts("Light is off")
  end

  def dim(level)
    @level = level
    if level == 0
      off
    else
      puts("Light is dimmed to #{@level}%")
    end
  end

end

class CeilingFan

  # Access these constants from outside this class as CeilingFan::HIGH,
  # CeilingFan::MEDIUM, and so on.  
  HIGH   = 3
  MEDIUM = 2
  LOW    = 1
  OFF    = 0

  attr_reader :speed

  def initialize (location)
    @location = location
    @speed = OFF
  end

  def high
    @speed = HIGH;
    puts("#{@location} ceiling fan is on high")
  end

  def medium
    @speed = MEDIUM
    puts("#{@location} ceiling fan is on medium")
  end

  def low
    @speed = LOW
    puts("#{@location} ceiling fan is on low")
  end

  def off
    @speed = OFF
    puts("#{@location} ceiling fan is off")
  end
  
end

class LightOnCommand

  attr_reader :light_object

  def initialize(light)
    @light_object = light
    @prev_lvl = 0
  end

  def execute
    @prev_lvl = @light_object.level
    @light_object.on
  end

  def undo
    @light_object.dim(@prev_lvl)
  end

end

class LightOffCommand

  attr_reader :light_object

  def initialize(light)
    @light_object = light
    @prev_lvl = 0
  end

  def execute
    @prev_lvl = @light_object.level
    @light_object.off
  end

  def undo
    if (@prev_lvl == 100) then
      @light_object.on
    else
      @light_object.dim(@prev_lvl)
    end
  end

end

class CeilingFanHighCommand

  attr_accessor :prev_state
  attr_reader :fan_object

  def initialize(fan)
    @fan_object = fan
    @prev_state = CeilingFan::OFF
  end

  def execute
    @prev_state = @fan_object.speed
    @fan_object.high
  end

  def undo

    case @prev_state
      when CeilingFan::OFF
        @fan_object.off
      when CeilingFan::LOW
        @fan_object.low
      when CeilingFan::MEDIUM
        @fan_object.medium
    end

  end

end

class CeilingFanMediumCommand

  attr_accessor :prev_state
  attr_reader :fan_object

  def initialize(fan)
    @fan_object = fan
    @prev_state = CeilingFan::OFF
  end

  def execute
    @prev_state = @fan_object.speed
    @fan_object.medium
  end

  def undo

    case @prev_state
      when CeilingFan::OFF
        @fan_object.off
      when CeilingFan::LOW
        @fan_object.low
      when CeilingFan::HIGH
        @fan_object.high
    end

  end

end

class CeilingFanOffCommand

  attr_accessor :prev_state
  attr_reader :fan_object

  def initialize(fan)
    @fan_object = fan
    @prev_state = CeilingFan::OFF
  end

  def execute
    @prev_state = @fan_object.speed
    @fan_object.off
  end

  def undo

    case @prev_state
      when CeilingFan::LOW
        @fan_object.low
      when CeilingFan::MEDIUM
        @fan_object.medium
      when CeilingFan::HIGH
        @fan_object.high
    end

  end

end

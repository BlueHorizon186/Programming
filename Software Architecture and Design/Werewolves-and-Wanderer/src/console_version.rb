# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: console_version.rb

require 'models/player'

puts "\nWelcome to the Werewolves and Wanderer adventure game!"
puts 'Please enter your name: '
player_name = gets.chomp
player = Player.new({:name=>player_name, :strength=>50, :wealth=>50 \
                    :monster_tally=>0, :current_room=>1})

show_player_info()

def show_player_info
  puts 'Player info will go here.'
end

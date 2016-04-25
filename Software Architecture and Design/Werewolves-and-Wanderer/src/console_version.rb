# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: console_version.rb

require 'models/player'
require 'models/game_tree'

def main
  puts "\nWelcome to the Werewolves and Wanderer adventure game!"
  puts 'Please enter your name: '
  player_name = gets.chomp
  player = Player.new({:name=>player_name, :strength=>50, :wealth=>50, \
                      :monster_tally=>0, :current_room=>N_entrance})

  loop do
    show_player_info(player)
    show_room_info(player)
    choice = gets.to_i
    break if choice == 0

    unless player.curr_room.content.choices.size == 1
      if choice == 1
        player.curr_room = player.curr_room.parent
      else
        player.curr_room = player.curr_room[choice - 2]
      end
      next
    end

    player.curr_room = player.curr_room[choice - 1]
  end

  puts 'Thanks for playing!'
end

def show_player_info(p)
  puts p.to_s
end

def show_room_info(p)
  r = p.curr_room.content
  puts r.to_s
  puts r.show_choices
end

main()

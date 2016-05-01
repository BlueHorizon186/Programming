# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_tree.rb

require 'tree'

require_relative './room'
require_relative './event'

# The +GameTree+ class represents an object that holds a
# tree data structure via means of the gem +RubyTree+, in
# order to store the castle map used throughout the game.
# It links all rooms and events according to the original
# game script to guide the player through the adventure.
class GameTree

  # The first room of the castle. In this case, it's the entrance.
  attr_reader :entrance

  # Creates a new +GameTree+ instance.
  def initialize
    @entrance = Tree::TreeNode.new('Entrance', \
                  Room.new('Entrance', \
                    'You are at the entrance of a forbidding-looking
                    stone castle. You are facing east. The huge wooden
                    entrance door stands lightly open.',
                    ['Enter the Castle']))
    initialize_rooms_and_events
  end

  # Adds all remaining rooms and events to the entrance node,
  # generating the entire game tree.
  def initialize_rooms_and_events
    n_hallway_e = Tree::TreeNode.new('Hallway Entrance', \
                    Room.new('Hallway Entrance', \
                      'You are in the hallway entrance to the castle.
                      It is dark and gloomy, and the air of decay and
                      desolation is very depressing. You suddenly feel
                      very frightened.',
                      ['Run away', 'Proceed through South Door']))
    @entrance << n_hallway_e

    n_audience_ch = Tree::TreeNode.new('Audience Chamber', \
                      Room.new('Audience Chamber', \
                        'The faded tapestries on the wall only hint at
                        the splendor which this room once had. There is
                        a window to the west. By craning your neck through
                        it to the right you can see the castle entrance.',
                        ['Leave by north', 'Leave by south or east']))
    n_hallway_e << n_audience_ch
  end

  # Calls the +RubyTree+ *print_tree* method to print
  # all the nodes so far.
  def print_map
    @entrance.print_tree
  end
end

# For debugging purposes only.
# gtree = GameTree.new
# gtree.print_map

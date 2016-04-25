# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_tree.rb

require 'tree'

require_relative './room'
require_relative './event'

# In this file, we will use a special convention in naming our
# tree nodes representing the individual rooms and events in
# the game. They are called N_name, where 'name' is which room
# or event will be stored there.

# Entrance
N_entrance = Tree::TreeNode.new('Entrance', \
              Room.new('Entrance', \
                'You are at the entrance of a forbidding-looking
                stone castle. You are facing east. The huge wooden
                entrance door stands lightly open.',
                ['Enter the Castle']))

# Hallway Entrance
N_hallway_e = Tree::TreeNode.new('Hallway Entrance', \
                Room.new('Hallway Entrance', \
                  'You are in the hallway entrance to the castle.
                  It is dark and gloomy, and the air of decay and
                  desolation is very depressing. You suddenly feel
                  very frightened.',
                  ['Run away', 'Proceed through South Door']))

# Join!
N_entrance << N_hallway_e

# Audience Chamber
N_audience_ch = Tree::TreeNode.new('Audience Chamber', \
                    Room.new('Audience Chamber', \
                      'The faded tapestries on the wall only hint at
                      the splendor which this room once had. There is
                      a window to the west. By craning your neck through
                      it to the right you can see the castle entrance.',
                      ['Leave by north', 'Leave by south or east']))

# Join!
N_hallway_e << N_audience_ch

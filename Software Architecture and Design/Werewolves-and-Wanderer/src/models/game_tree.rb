# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Authors: A01371166 Ivan David Diaz Sanchez
#          A01372223 Jonathan Patlan

# File: models/game_tree.rb

require 'tree'

require_relative './room'
require_relative './event'

# The +GameTree+ class represents an object that holds a
# tree data structure via means of the gem +RubyTree+, in
# order to store the castle map used throughout the game.
# It links all rooms and events according to the original
# game script to guide the player through the adventure.
#
# Each node contains either a +Room+ object or an +Event+
# object with its respective description. The +Room+ objects
# also contain an array with all the possible choices the
# player can make there. The first index will always refer
# to the parent node, i.e. the previous room.
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
                        ['Leave by north', 'Trigger', 'Leave by south or east'], true))
    n_hallway_e << n_audience_ch

    n_audiencech_ev = Tree::TreeNode.new('Audience_Ch_Ev', \
                        Event.new('You must fight the fanatical Fleshgorger,
                          which suddenly stumbles into the room.', 25, 0, true, \
                          'You defeated the Fleshgorger!', 'You lost strength!'))
    n_audience_ch << n_audiencech_ev

    n_great_hall = Tree::TreeNode.new('Great Hall', \
                      Room.new('Great Hall', \
                        'There are two doors in the L-shaped room. You notice
                        the wood panels around the room are warped and faded.
                        As you look around, a mouse scampers across the floor.
                        You whirl around at a sudden noise.',
                        ['Leave by north or west', 'Trigger', 'Look out by the windows', \
                          'Move to east'], true))
    n_audience_ch << n_great_hall

    n_greathall_nothing = Tree::TreeNode.new('Great_Hall_Nothing', \
                            Event.new('You see to your relief there is nothing there.', \
                              0, 0, false))
    n_great_hall << n_greathall_nothing

    n_greathall_lookout = Tree::TreeNode.new('Great_Hall_Lookout', \
                            Event.new('By straining your eyes through the mist which
                              has swirled up while you\'ve been exploring, you can
                              see below you, looking southwards, an ornamental lake.
                              By craning your neck round to the right through the
                              west window you can just see the entrance door to the
                              castle.', 0, 0, false))
    n_great_hall << n_greathall_lookout

    n_inner_h = Tree::TreeNode.new('Inner Hallway', \
                  Room.new('Inner Hallway', \
                    'You are in the inner hallway, which contains a door to
                    the north one to the west, and a circular stairwell.
                    The room is small and unfriendly.', \
                    ['Leave by west', 'Look down the stairwell', \
                      'Look up the stairs', 'Leave by north door', \
                      'Go up the stairs', 'Go down the stairs']))
    n_great_hall << n_inner_h

    n_innerh_lookdown = Tree::TreeNode.new('Inner_Hall_Lookdown', \
                          Event.new('Looking down the stairwell, you can
                            see a gloomy, unpleasant looking room, the
                            guardroom of the ancient dungeon.', 0, 0, false))
    n_inner_h << n_innerh_lookdown

    n_innerh_lookup = Tree::TreeNode.new('Inner_Hall_Lookup', \
                        Event.new('Looking up the stairwell you can just
                          make out an elaborately decorated hallway.', 0, 0, false))
    n_inner_h << n_innerh_lookup

    n_monarch_r = Tree::TreeNode.new('Monarch\'s Room', \
                    Room.new('Monarch\'s Meeting Room', \
                      'This is the monarch\'s private meeting room. The echo
                      of ancient plotting and wrangling hangs heavy in the
                      musty air.', \
                      ['Exit through south door', 'Trigger'], true))
    n_inner_h << n_monarch_r

    n_monarchr_ev = Tree::TreeNode.new('Monarch_R_Ev', \
                      Event.new('You are attacked by a ghastly Gruesomeness,
                        which was hiding behind the drapes.', 0, 100, true, \
                        'You won and found an emerald!', 'The Gruesomeness stole
                        from you while you were exhausted.'))
    n_monarch_r << n_monarchr_ev

    n_lshaped_h = Tree::TreeNode.new('L-Shaped Upper Hallway', \
                    Room.new('L-Shapped Upper Hallway', \
                      'You are in the L-shaped upper hallway of the castle.
                      A moth flutters across the room near the ceiling.
                      To the north is a door and there is a stairwell in the
                      hall as well.', \
                      ['Go down where you came from', 'Peek through the door', \
                        'Look down the new stairwell', 'Go through the door'], false))
    n_inner_h << n_lshaped_h

    n_prison_gr = Tree::TreeNode.new('Prison Guardroom', \
                    Room.new('Prison Guardroom', 'You are in the prison guardroom,
                      in the basement of the castle. The stairwell ends in this room.
                      There is one other exit, a small hole in the east wall.
                      The air is damp and unpleasant... a chill wind rushes into
                      the room from gaps in the stone at the top of the walls.', \
                      ['Go up the stairs', 'Go through hole in the east']))
    n_inner_h << n_prison_gr

    #L-Shapped Hallway Pending...
    n_lshapedh_peek = Tree::TreeNode.new('LShaped_Peek', \
                        Event.new('Peering into the room you see it was once the
                          master bedroom. It appears quiet. As you turn back you
                          notice a small bottle on the ground. Quickly, you uncork
                          it and swallow the contents. The bottle contained a
                          wonderful elixir which has increased your strength.', 30, 0, false))
    n_lshaped_h << n_lshapedh_peek

    n_lshapedh_lookdown = Tree::TreeNode.new('LShaped_Lookdown', \
                            Event.new('To be implemented...', 0, 0, false))
    n_lshaped_h << n_lshapedh_lookdown

    n_master_bed = Tree::TreeNode.new('Master Bedroom', \
                    Room.new('Master Bedroom', 'You find yourself in the
                      master bedroom on the upper level of the castle.
                      Looking down from the window to the west you can
                      see the entrance to the castle, while the secret
                      herb garden is visible below the north window.
                      There are doors to the east and to the south.', \
                      ['Leave by south door', 'Trigger', 'Leave by east door'], true))
    n_lshaped_h << n_master_bed

    # Master Bedroom Pending...
    n_masterbed_ev = Tree::TreeNode.new('Master_Bed_Ev', \
                      Event.new('Aha... wealth! You find great treasure here
                        worth $900 in gems and gold.', 0, 900, false))
    n_master_bed << n_masterbed_ev

    n_dungeon = Tree::TreeNode.new('Dungeon', \
                  Room.new('Dungeon', 'You are in a dank, dark dungeon.
                    The only light comes into the room from a small hole
                    in the west wall.', \
                    ['Leave the dungeon', 'Trigger'], true))
    n_prison_gr << n_dungeon

    n_dungeon_ev = Tree::TreeNode.new('Dungeon_Ev', \
                    Event.new('The ghost of the guard has awoken!
                      Your strength is reduced due to the fright you
                      suffered.', -35, 0, false))
    n_dungeon << n_dungeon_ev
  end

  # Calls the +RubyTree+ *print_tree* method to print
  # all the nodes so far. Used for debugging purposes only.
  def print_map
    @entrance.print_tree
  end
end

# For debugging purposes only.
# gtree = GameTree.new
# gtree.print_map
# puts gtree.entrance[0]
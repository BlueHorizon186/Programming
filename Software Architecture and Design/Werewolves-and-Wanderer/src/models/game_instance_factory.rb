# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Authors: A01371166 Ivan David Diaz Sanchez
#          A01372223 Jonathan Patlan

# File: models/game_instance_factory.rb

require 'yaml'
require_relative './player'
require_relative './game_instance'
require_relative './database_manager'

# The +GameInstanceFactory+ is an implementation of the {Simple
# Factory Pattern}[https://en.wikipedia.org/wiki/Factory_method_pattern].
# It allows you to create instances of the ::GameInstance class
# by accessing the database and afterwards, calling the ::new_instance
# class method. If it is a preexisting game, then it just searches for
# the appropriate YAML file and returns the object stored in it.
class GameInstanceFactory

  @@db = DatabaseManager.instance

  # Creates a new fresh game and registers the new player in
  # the database. The +DatabaseManager+ is called for this
  # purpose.
  #
  # Parameters::
  #   user:: The player's username.
  #   password:: The player's account password.
  #
  # Returns:: The fresh +GameInstance+ object.
  def self.new_game(user, password)
    new_player_id = @@db.insert(user, password)
    player = Player.new({:id=>new_player_id, :name=>user, :strength=>50, \
                          :wealth=>50, :monster_tally=>0, :current_room=>nil})
    self.new_instance(player, new_player_id)
  end

  # Reads a previously registered player record from the
  # database and returns its id.
  #
  # Parameters::
  #   user:: The player's username.
  #   password:: The player's account password.
  #
  # Returns:: The given player's id.
  def self.load_game(user, password)
    saved_game = @@db.retrieve(user, password)

    unless saved_game.nil?
      return saved_game[1][:id]
    end

    return nil
  end

  # Looks for the given player's YAML file and reads his/her
  # previously saved +GameInstance+.
  #
  # Parameter::
  #   pl_id:: The player's id.
  #
  # Returns:: The given player's +GameInstance+ with his/her progress.
  def self.refresh_game(pl_id)
    YAML.load_file("#{YAML_FILES_PATH}/y_#{pl_id}.yaml")
  end

  # Creates the new +GameInstance+ object. It is called by
  # means of any of the previous methods.
  #
  # Parameter::
  #   pl:: The player's information
  #
  # Returns:: The +GameInstance+ object.
  def self.new_instance(pl, pl_id)
    new_inst = GameInstance.new(pl)

    File.open("#{YAML_FILES_PATH}/y_#{pl_id}.yaml", "w") do |file|
      file.puts YAML::dump(new_inst)
    end

    pl_id
  end

end

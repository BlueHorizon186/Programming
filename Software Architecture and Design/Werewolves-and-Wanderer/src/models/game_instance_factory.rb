# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/game_instance_factory.rb

require_relative './player'
require_relative './game_instance'
require_relative './database_manager'

# The +GameInstanceFactory+ is an implementation of the {Simple
# Factory Pattern}[https://en.wikipedia.org/wiki/Factory_method_pattern].
# It allows you to create instances of the ::GameInstance class
# by accessing the database and afterwards, calling the ::new_instance
# class method.
class GameInstanceFactory

  @@player = nil
  @@db = DatabaseManager.instance

  # Creates a new fresh game and registers the new player in
  # the database. The +DatabaseManager+ is called for this
  # purpose.
  #
  # Parameters::
  #   user:: The player's username.
  #   password:: The player's account password.
  #
  # Returns:: An array with the database operation result
  #           message and the fresh +GameInstance+ object.
  def self.new_game(user, password)
    db_status = @@db.insert(user, password)
    @@player = Player.new(user)
    [db_status, self.new_instance]
  end

  # Creates a new +GameInstance+ with the player's information
  # read from the database. The +DatabaseManager+ is called for
  # this purpose.
  #
  # Parameters::
  #   user:: The player's username.
  #   password:: The player's account password.
  #
  # Returns:: An array with the database operation result
  #           message and the generated +GameInstance+ object
  #           with the player's previously saved progress.
  def self.load_game(user, password)
    saved_game = @@db.retrieve(user, password)
    @@player = Player.new(saved_game[1])
    [saved_game[0], self.new_instance]
  end

  # Creates the new +GameInstance+ object. It is called by
  # means of any of the previous methods.
  #
  # Returns:: The +GameInstance+ object.
  def self.new_instance
    GameInstance.new(@@player)
  end

end

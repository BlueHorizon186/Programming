# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/core/game_instance_factory.rb

require_relative '../player'
require_relative './game_instance'
require_relative './db_manager'

# The +GameInstanceFactory+ is an implementation of the {Simple
# Factory Pattern}[https://en.wikipedia.org/wiki/Factory_method_pattern].
# It allows you to create instances of the games for each required
# player, whether it's a new game or load a preexistent one from the
# players database.
class GameInstanceFactory

  @@player = nil
  @@db = DBManager.instance

  # Stores the newly registered player to the database and grants
  # him/her access to the game.
  #
  # Parameters::
  #   username:: The player's username
  #   password:: The player's account password
  #
  # Returns:: A new +GameInstance+ for the player to begin
  #           the game.
  def self.new_game(username, password)
    status = @@db.insert(username, password)
    @@player = Player.new(username)
    return status, self.new_instance
  end

  def self.load_game(user, password)
  end

  def self.new_instance
    GameInstance.new(@@player)
  end

end

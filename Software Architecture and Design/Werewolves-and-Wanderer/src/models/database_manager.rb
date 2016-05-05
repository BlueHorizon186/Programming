# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/database_manager.rb

require 'singleton'
require 'sequel'

DB_PATH = './db/adv_game.db'
DB = Sequel.connect(:adapter=>'sqlite', :database=>DB_PATH)

# The +DatabaseManager+ is an implementation of the {Singleton
# Pattern}[https://en.wikipedia.org/wiki/Singleton_pattern].
# Its purpose is to connect the game classes to the player
# database and manage all the necessary transactions.
class DatabaseManager

  include Singleton

  # Inserts a new player's information to the database. (Pending...)
  #
  # Parameter::
  #   user_data:: An array with the player's username
  #               and password.
  #
  # Returns:: The recently added player's assigned id, or the
  # error message in case it occurs.
  def insert(*user_data)
    usr = user_data[0]
    pswd = user_data[1]

    begin
      players = DB.from(:Players)
      players.insert(:name => usr, :password => pswd)
    rescue Sequel::Error => e
      return e.message
    end

    new_id = 0
    DB.fetch("select * from SQLITE_SEQUENCE WHERE name='Players'") \
              { |row| new_id = row[:seq] }
    new_id
  end

  # Searches the database for the given player to retrieve
  # his/her information.
  #
  # Parameter::
  #   user_data:: An array with the player's username
  #               and password.
  #
  # Returns::
  #   - If the player was not found, then it returns nil.
  #   - If an error occurred, then it returns an array with the
  #     error message and a nil object.
  #   - If everything went right, it returns an array with a
  #     success message and the player's retrieved information.
  def retrieve(*user_data)
    usr = user_data[0]
    pswd = user_data[1]
    requested_pl = nil

    begin
      players = DB.from(:Players)
      requested_pl = players[:name => usr, :password => pswd]

      if requested_pl.nil? then return nil end
      requested_pl.delete(:password)

    rescue Sequel::Error => e
      return [e.message, nil]
    end

    ['Game loaded successfully!', requested_pl]
  end

  # Implementation pending...
  def update(*user_data)
  end

end

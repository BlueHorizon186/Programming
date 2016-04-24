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

  # Inserts a new player's information to the database.
  #
  # Parameters::
  #   user_data:: An array with the player's username
  #               and password.
  #
  # Returns:: A message indicating success or the error that
  #           was produced, if any.
  def insert(*user_data)
    usr = user_data[0]
    pswd = user_data[1]

    begin
      players = DB.from(:Players)
      players.insert(:name => usr, :password => pswd)
    rescue Sequel::Error => e
      return e.message
    end

    'Your player account has been successfully created!'
  end

  # Implementation pending...
  def retrieve(*user_data)
    usr = user_data[0]
    pswd = user_data[1]
    requested_pl = nil

    begin
      players = DB.from(:Players)
      requested_pl = players[:name => usr, :password => pswd]

      if requested_pl.nil? then return nil end

      requested_pl.delete(:id)
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
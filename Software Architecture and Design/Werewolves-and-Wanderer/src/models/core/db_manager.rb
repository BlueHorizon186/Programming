# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/core/db_manager.rb

require 'singleton'
require 'sequel'

DB_PATH = '././raw/werewolves_and_wanderer.db'
DB = Sequel.connect(:adapter=>'sqlite', :database=>DB_PATH)

# The +DBManager+ class is an implementation of the {Singleton
# Pattern}[https://en.wikipedia.org/wiki/Singleton_pattern].
# It allows you to access the player database with any of the
# *CRUD* transactions and manage its stored records.
class DBManager

  include Singleton

  # Inserts a new player's information into the database.
  #
  # Parameter::
  #   new_player:: The player object with all the information
  #                to be stored.
  #
  # Returns:: A message stating if the player was registered
  #           or not, and the cause.
  def insert(user, password)
    pl_record = DB["INSERT INTO Players (name,password) VALUES (?,?)", \
                   user, password]
    begin
      pl_record.insert
    rescue Sequel::Error => e
      return e.message
    end

    'Your player has been successfully created!'
  end

  # Searches the database for the user's player data and fetches it
  # if it exists.
  #
  # Parameters::
  #   user:: The player's username
  #   password:: The player's account password
  #
  # Returns:: A dictionary with the player's data.
  def retrieve(user, password)
  end

  # Updates an existent player's record to save his progress
  # in the game.
  #
  # Parameter::
  #   player:: The object containing the player's progress
  def update(player)
  end

end

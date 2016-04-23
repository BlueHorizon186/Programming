# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: models/database_manager.rb

require 'singleton'

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
  # Returns:: The database message.
  def insert(user_data)
  end

  # Implementation pending...
  def retrieve(user_data)
  end

  # Implementation pending...
  def update(user_data)
  end

end

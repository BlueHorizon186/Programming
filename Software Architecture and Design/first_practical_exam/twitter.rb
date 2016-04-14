#==========================================================
# Student Name: Ivan David Diaz Sanchez
# Student Id: A01371166
#==========================================================

#==========================================================
# Code for question 2

#----------------------------------------------------------
# Place here your code for the Twitter class.
#----------------------------------------------------------

require 'observer'

class Twitter
  include Observable

  attr_reader :username, :message

  def initialize(username)
    @username = username
    @message = ""
  end

  def follow(user)
    user.add_observer(self)
    self
  end

  def tweet(new_status)
    @message = new_status
    puts("#{@username} just tweeted: #{@message}")

    changed
    notify_observers(self)
  end

  def update(incoming)
    puts("#{@username} received a tweet from #{incoming.username}: #{incoming.message}")
  end
end

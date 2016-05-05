# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: adventure_server.rb

require 'sinatra'
require 'models/game_instance_factory'

set :bind, '0.0.0.0'
set :port, ENV['PORT']
enable :sessions
set :session_secret, 'SecretString#!$%'

# Home Screen
get '/' do
  redirect '/welcome'
end

get '/welcome' do
  erb :index
end

# Login Screen and Post Processing
get '/login' do
  unless session[:pl_id].nil?
    redirect '/gameadv'
  end
  erb :login
end

post '/login' do
  # Login failure pending...
  session[:pl_id] = GameInstanceFactory.load_game(params[:usr],
                                                  params[:pswd])
  redirect '/gameadv'
end

# Sign Up Screen and Post Processing (Pending...)
get '/signup' do
  erb :signup
end

post '/signup' do
  session[:pl_id] = GameInstanceFactory.new_game(params[:usr], params[:pswd])
  redirect '/gameadv'
end

# Game!
get '/gameadv' do
  # Temporary: Prevent users from accessing this page without
  # logging in.
  if session[:pl_id].nil? then redirect '/login' end

  game_inst = GameInstanceFactory.refresh_game(session[:pl_id])

  player = game_inst.player
  next_state = game_inst.play_next

  if next_state.is_a?(Room) then
    @next_st = next_state.name
    @next_choices = next_state.choices
  elsif next_state.is_a?(Event)
    @next_st = 'Event'
    @next_choices = ['Return']
    @victory = next_state.trigger
    @message = ''

    unless @victory.nil?
      if @victory then @message = next_state.victory_msg
      else @message = next_state.failure_msg end
    end

    game_inst.apply_event_effects
  end

  @username = player.name
  @str = player.strength
  @wealth = player.wealth
  @monsters = player.monster_tally
  @next_desc = next_state.description
  erb :game
end

post '/gameadv' do
  game_inst = GameInstanceFactory.refresh_game(session[:pl_id])
  game_inst.move_to_choice(params[:btn].to_i)
  redirect '/gameadv'
end

# Logout function
get '/logout' do
  session[:pl_id] = nil
  redirect '/welcome'
end

# New game function
get '/newgame' do
  redirect '/gameadv'
end

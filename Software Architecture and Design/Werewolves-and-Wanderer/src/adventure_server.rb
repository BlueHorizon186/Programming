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
  unless session[:pl_inst].nil?
    redirect '/gameadv'
  end
  erb :login
end

post '/login' do
  # Right now, we are assuming the user will always enter valid
  # credentials. The error handling will be made later.
  g_inst = GameInstanceFactory.load_game(params[:usr], params[:pswd])
  session[:pl_inst] = g_inst[1]

  # Login failure pending...
  # if message[1].nil? then redirect '/loginerror' end
  redirect '/gameadv'
end

# Sign Up Screen and Post Processing
get '/signup' do
  erb :signup
end

post '/signup' do
  g_inst = GameInstanceFactory.new_game(params[:usr], params[:pswd])
  session[:pl_inst] = g_inst[1]
  redirect '/gameadv'
end

# Game!
get '/gameadv' do
  # Here will go all the game logic management and user interaction.
  # It will be aided by jQuery functionality.

  # Temporary: Prevent users from accessing this page without
  # logging in.
  if session[:pl_inst].nil? then redirect '/login' end

  @username = session[:pl_inst].player.name
  erb :game
end

# Logout function
get '/logout' do
  session[:pl_inst] = nil
  redirect '/welcome'
end

# post '/welcome' do
#   session[:user] = params[:usrtxt]
#   session[:password] = params[:usrpswd]
#   redirect '/signupsuccess'
# end

# get '/signupsuccess' do
#   @message = GameInstanceFactory.load_game(session[:user], session[:password])
#   if @message[1].nil? then @message = ['NULL', @message[0]] end
#   erb :signup_success
# end

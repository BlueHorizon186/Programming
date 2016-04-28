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
  erb :login
end

post '/login' do
  message = GameInstanceFactory.load_game(params[:usr], params[:pswd])
  # Login failure pending...
  if message[1].nil? then message = ['NULL', @message[0]] end
  session[:pl_inst] = message
  redirect '/loginsuccess'
end

get '/loginsuccess' do
  sleep 1.0
  redirect '/gameadv'
end

# Sign Up Screen and Post Processing
get '/signup' do
  erb :signup
end

# Game!
get '/gameadv' do
  # Here will go all the game logic management and user interaction.
  # It will be aided by jQuery functionality.
  erb :game
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

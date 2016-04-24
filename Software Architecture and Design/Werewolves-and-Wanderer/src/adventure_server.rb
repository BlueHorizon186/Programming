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

get '/' do
  redirect '/welcome'
end

get '/welcome' do
  erb :index
end

post '/welcome' do
  session[:user] = params[:usrtxt]
  session[:password] = params[:usrpswd]
  redirect '/signupsuccess'
end

get '/signupsuccess' do
  @message = GameInstanceFactory.load_game(session[:user], session[:password])
  erb :signup_success
end

# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: adventure_server.rb

require 'sinatra'
require 'models/core/game_instance_factory.rb'

set :bind, '0.0.0.0'
set :port, ENV['PORT']
enable :sessions
set :session_secret, 'SecretString#!$%'

get '/' do
  redirect '/adventuresignup'
end

get '/adventuresignup' do
  erb :index
end

post '/adventuresignup' do
  session[:user] = params[:user]
  session[:password] = params[:password]
  redirect '/signupsuccess'
end

get '/signupsuccess' do
  @game_instance = GameInstanceFactory.new_game(session[:user], \
                                                session[:password])
  erb :new_user_success
end

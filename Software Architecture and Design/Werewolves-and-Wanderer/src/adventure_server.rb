# Final Project: A Text Adventure Game
# Date: 05-May-2016
# Author: A01371166 Ivan David Diaz Sanchez

# File: adventure_server.rb

require 'sinatra'

set :bind, '0.0.0.0'
set :port, ENV['PORT']
enable :sessions
set :session_secret, 'SecretString#!$%'

get '/' do
  redirect '/adventurelogin'
end

get '/adventurelogin' do
  erb :index
end

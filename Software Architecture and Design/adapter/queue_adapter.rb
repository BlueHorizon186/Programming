# Adapter Pattern
# Date: 02-Mar-2016
# Author:
#          A01371166 Ivan David Diaz Sanchez

# File: queue_adapter.rb

class QueueAdapter

  def initialize(q)
    @adaptee = q
    @elements = []
  end

  def push(x)
    @adaptee.insert(x)
    @elements << x
    self
  end

  def pop
    if empty?
      nil
    else
      removed = self.peek

      while not empty?
        @adaptee.remove
      end

      @elements = @elements[0...-1]
      @elements.each {|x| @adaptee.insert(x)}
      removed
    end
  end

  def peek
    if empty?
      nil
    else
      @elements.last
    end
  end

  def empty?
    @adaptee.empty?
  end

  def size
    @adaptee.size
  end

end

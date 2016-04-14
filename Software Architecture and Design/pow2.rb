
e = Enumerator.new do |y|
  n = 1
  loop do
    y << n
    n *= 2
  end
end

p e.take(10).drop(5)

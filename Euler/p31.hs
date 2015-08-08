combinations = [ sum | ones <- [0..200], 
					   twos <- [0..100], 
					   fives <- [0..40], 
					   tens <- [0..20], 
					   twenties <- [0..10], 
					   fifties <- [0..4], 
					   hundreds <- [0..2], 
					   twoHundreds <- [0..1], 
					   let sum = ones+twos*2+fives*5+tens*10+twenties*20+fifties*50+hundreds*100+twoHundreds*200, 
					   sum == 200]


--combinations = [ (ones,twos,sum) | ones <- [0..200], 
					   --twos <- [0..100], 
					   --let sum = ones+twos*2, 
					   --sum == 200]
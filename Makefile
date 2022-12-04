PORT ?= 40000
HOST ?= localhost

nrepl:
	clj -A:nrepl --port $(PORT) --host $(PORT)

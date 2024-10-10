# Define default target
.DEFAULT_GOAL := help
.PHONEY: help install-dependencies test

help:
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' Makefile | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

install-dependencies: ## Runs pip install from requirements.txt
	pip install -r requirements.txt

test: ## Runs the unit test
	pytest
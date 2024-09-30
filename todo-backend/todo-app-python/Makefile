# Define default target
.DEFAULT_GOAL := help
.PHONEY: help install-dependencies run test

help:
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' Makefile | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

install-dependencies: ## Runs pip install from requirements.txt
	pip install -r requirements.txt

run: ## Starts the To Do List app
	uvicorn app.main:app --host 0.0.0.0 --port 3000

test: ## Runs the unit test
	pytest
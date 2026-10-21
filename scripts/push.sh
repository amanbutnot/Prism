#!/bin/zsh

set -e

BRANCH=$(git branch --show-current)

echo "Pushing '$BRANCH' to GitHub..."
git push origin "$BRANCH"

echo "Pushing '$BRANCH' to Forgejo..."
git push forg "$BRANCH"

echo "Successfully pushed '$BRANCH' to both repositories."
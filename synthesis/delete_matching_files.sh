#!/bin/bash

# Exit on error or unset variable
set -euo pipefail

# Usage check
if [[ $# -ne 1 ]]; then
  echo "Usage: $0 /path/to/folder"
  exit 1
fi

TARGET_DIR="$1"

# Verify that the target is a directory
if [[ ! -d "$TARGET_DIR" ]]; then
  echo "Error: '$TARGET_DIR' is not a directory."
  exit 2
fi

# List of patterns to match (glob-style wildcards)
patterns=(
  "*.aux"
  "*.ind"
  "*.idx"
  "*.lot"
  "*.ids"
  "*.dvi"
  "*.tmp"
  ".DS_Store"
)

# Deleting files matching the patterns
for pattern in "${patterns[@]}"; do
  echo "Looking for files matching: $pattern"
  find "$TARGET_DIR" -type f -name "$pattern" -print -delete
done

echo "Done."

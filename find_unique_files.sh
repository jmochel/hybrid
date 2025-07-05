#!/bin/bash

# Script to find unique files across subfolders with numeric prefixes
# Usage: ./find_unique_files.sh <filename> <destination_folder>

set -e  # Exit on any error

# Check if correct number of arguments provided
if [ $# -ne 2 ]; then
    echo "Usage: $0 <filename> <destination_folder>"
    echo "Example: $0 koran.ltx ./unique_files"
    exit 1
fi

SEARCH_FILENAME="$1"
DESTINATION_FOLDER="$2"

# Validate destination folder
if [ ! -d "$DESTINATION_FOLDER" ]; then
    echo "Creating destination folder: $DESTINATION_FOLDER"
    mkdir -p "$DESTINATION_FOLDER"
fi

# Check if search filename is provided
if [ -z "$SEARCH_FILENAME" ]; then
    echo "Error: Filename to search for cannot be empty"
    exit 1
fi

echo "Searching for files named: $SEARCH_FILENAME"
echo "Destination folder: $DESTINATION_FOLDER"
echo "----------------------------------------"

# Initialize associative array to store MD5 sums (requires bash 4.0+)
declare -A seen_md5s
declare -A file_info

# Counter for unique files found
unique_count=0
total_count=0

# Function to extract numeric prefix from folder path
extract_numeric_prefix() {
    local filepath="$1"
    local numeric_prefix=""
    
    # Split the path into components
    IFS='/' read -ra path_parts <<< "$filepath"
    
    # Look for the highest-level folder that matches various numeric prefix patterns
    for part in "${path_parts[@]}"; do
        # Check for various patterns:
        # YYYY-MM-DD-suffix (e.g., 1996-01-18-sh)
        # YYYY-MM-DD-N-suffix (e.g., 1996-01-18-1-sh)
        # YYYY-N-suffix (e.g., 2009-1-sh)
        # YYYY-suffix (e.g., 2009-sh)
        if [[ $part =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2}-[a-zA-Z]+$ ]] || \
           [[ $part =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2}-[0-9]+-[a-zA-Z]+$ ]] || \
           [[ $part =~ ^[0-9]{4}-[0-9]+-[a-zA-Z]+$ ]] || \
           [[ $part =~ ^[0-9]{4}-[a-zA-Z]+$ ]]; then
            # Use the entire matched part as the numeric prefix
            numeric_prefix="$part"
            break
        fi
    done
    
    echo "$numeric_prefix"
}

# Find all files matching the search filename
while IFS= read -r -d '' filepath; do
    total_count=$((total_count + 1))
    
    # Extract the numeric prefix from the folder path
    numeric_prefix=$(extract_numeric_prefix "$filepath")
    
    # Extract file extension
    file_extension="${SEARCH_FILENAME##*.}"
    
    # Extract file root name (without extension)
    file_root_name="${SEARCH_FILENAME%.*}"
    
    # Generate MD5 checksum
    md5sum_result=$(md5sum "$filepath" | cut -d' ' -f1)
    
    # Check if we've seen this MD5 before
    if [ -z "${seen_md5s[$md5sum_result]}" ]; then
        # This is a unique file
        unique_count=$((unique_count + 1))
        
        # Store the MD5 and file info
        seen_md5s[$md5sum_result]=1
        file_info[$md5sum_result]="$filepath|$numeric_prefix"
        
        # Create new filename: <file-root-name>-<numeric-prefix>.<fileextension>
        if [ -n "$numeric_prefix" ]; then
            new_filename="${file_root_name}-${numeric_prefix}.${file_extension}"
        else
            # This shouldn't happen if numeric prefix is always found, but just in case
            folder_name=$(basename "$(dirname "$filepath")")
            new_filename="${file_root_name}-${folder_name}.${file_extension}"
        fi
        
        # Copy file to destination
        cp "$filepath" "$DESTINATION_FOLDER/$new_filename"
        
        echo "Unique file found: $filepath"
        echo "  MD5: $md5sum_result"
        echo "  Numeric prefix: $numeric_prefix"
        echo "  Copied as: $new_filename"
        echo ""
    else
        echo "Duplicate found: $filepath (MD5: $md5sum_result)"
        echo "  Original: ${file_info[$md5sum_result]%|*}"
        echo ""
    fi
    
done < <(find . -name "$SEARCH_FILENAME" -type f -print0)

echo "----------------------------------------"
echo "Summary:"
echo "  Total files found: $total_count"
echo "  Unique files copied: $unique_count"
echo "  Destination folder: $DESTINATION_FOLDER"

if [ $unique_count -gt 0 ]; then
    echo ""
    echo "Unique files copied:"
    ls -la "$DESTINATION_FOLDER/"
fi 
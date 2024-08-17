#!/bin/bash

main() {
  parseScanUrl "$1"
}

parseScanUrl() {
  echo "Whitesource scanned log path: $1"

  # Define the path to the log file
  log_file="$1"

  # Create a Markdown file
  md_file="whitesource_scan_urls.md"
  rm -rf "$md_file"

  current_date=$(date +"%a %b %e %T %Z %Y")

  # Add the title and scan date
  echo "# Whitesource Scan Report" > "$md_file"
  echo "Scan Date: $current_date" >> "$md_file"
  echo "" >> "$md_file"

  # Add the table headers
  echo "| CONTAINER | CHECKSUM | URL |" >> "$md_file"
  echo "|-----------|----------|-----|" >> "$md_file"

  echo "Start : Generate Markdown report...."

  # Search for the keyword "End: Update Inventory" and start processing from there
  keyword="End: Update Inventory"
  start_line=$(grep -n "$keyword" "$log_file" | cut -d: -f1 | head -n 1)

  if [ -z "$start_line" ]; then
    echo "Keyword '$keyword' not found in log file. Exiting."
    exit 1
  fi

  echo "Processing log file from line: $start_line"

  # Extract and process the log lines from the start line
  tail -n +$start_line "$log_file" | grep -E 'Project name:|latest \(|URL:' | while IFS= read -r line; do
    container=$(echo "$line" | grep -oP '(?<=Project name: bigfix/)\S+')
    checksum=$(echo "$line" | grep -oP '(?<=latest \()[0-9a-f]+')
    url=$(echo "$line" | grep -oP '(?<=project URL:)\S+')

    echo "container................................$container"
    echo "checksum.............................$checksum"
    echo "url..............................$url"

    if [ ! -z "$container" ] && [ ! -z "$checksum" ] && [ ! -z "$url" ]; then
      echo "| $container | $checksum | [$url]($url) |" >> "$md_file"
    fi
  done

  echo "End : Generate Markdown Report..."

  echo "Markdown file with hyperlinks created: $md_file"
}

main "$1"
#!/bin/bash

main() {
  parseScanUrl "$1"
}

parseScanUrl() {
  echo "Whitesource scanned log path: $1"

  # Define the path to the log file
  log_file="$1"

  # Create an HTML file and write the table structure
  html_file="whitesource_scan_urls.html"
  rm -rf "$html_file"

  # Extract the build number    
    build_number=$(grep -oP '\b[RD]\d+ \(' "$log_file" | head -1 | tr -d ' (')
    echo "Bigfix-Docker build_number..........................${build_number}"
    
  echo "<html><head><style>
  table {
        width: 100%;
        color: #333;
        font-family: Arial, sans-serif;
        font-size: 0.8rem;
        text-align: left;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        margin: auto;
      }
      table th {
        background-image: linear-gradient(to right, #592ac4, #3d8efa);
        color: #fff;
        font-weight: bold;
        padding: 1rem;
        text-transform: uppercase;
        letter-spacing: 1px;
      }
      .table-column-header th {
        background: gray;
        text-align: center;
      }
      table tr:nth-child(even) td {
        background-color: #f2f2f2;
      }
      table td {
        background-color: gainsboro;
        padding: 10px;
        border-bottom: 1px solid #ccc;
        font-weight: bold;
      }
      .gray-background th {
        background-color: gray !important;
      }
      .center-text {
        text-align: center;
      }
  </style></head><body>" > "$html_file"

  current_date=$(date +"%a %b %e %T %Z %Y")
  # Use the build number in the title with proper formatting
  echo "<table><tr><th class="center-text"><p><span style=\"float: left\">Whitesource Scan Report</span><span style=\"float: right\">Scan Date: $current_date</span></p><p>Build No: ${build_number}</p></th></tr></table><table><tr class="table-column-header"><th>CONTAINER</th><th>CHECKSUM</th><th>URL</th></tr>" >> "$html_file"
  
  echo "Start : Generate HTML report...."
  # Extract and process the log lines
  while IFS= read -r line; do
    container=$(echo "$line" | grep -oP 'bigfix/(\S+)' | cut -d'/' -f 2)
    info=$(echo "$line" | grep -oP '\S+ [RD]\d+ \([0-9a-f]+\)' | cut -c 1-)
    url=$(echo "$line" | grep -oP 'URL: \K[^ ]+')
    checksum=$(echo "$info" | cut -d' ' -f 3 | sed -e 's/[(|)]//g')

    if [ ! -z "$container" ] && [ ! -z "$checksum" ] && [ ! -z "$url" ]; then
      echo "<tr><td>$container</td><td>$checksum</td><td><a href=\"$url\" target=\"_blank\">$url</a></td></tr>" >> "$html_file"
    fi
  done < "$log_file"
  echo "End : Generate HTML Report..."

  echo "</table></body></html>" >> "$html_file"
  echo "HTML file with hyperlinks created: $html_file"
}

main "$1"

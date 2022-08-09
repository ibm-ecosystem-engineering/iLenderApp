#!/bin/sh

echo "....................................................... process verify results started ......................................................."

echo "verify build jar ......"
echo "BUILD FAILURE text should not appear below ...... if appears press Ctrl+C and stop the process"
cat a.txt | grep "BUILD FAILURE"
cat a.txt | grep "BUILD SUCCESS"

echo "....................................................... process verify results completed ......................................................."
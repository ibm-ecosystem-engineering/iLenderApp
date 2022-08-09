#!/bin/sh

echo "....................................................... process verify results image started ......................................................."

echo "verify build image ....."
echo "The text like error or fail should not appear below ......"
cat b.txt | grep "Successfully built"
cat b.txt | grep "The push refers to a repository"
cat b.txt | grep "0.0.1: digest: "
cat b.txt | grep "error"
cat b.txt | grep "fail"

echo "....................................................... process verify results image completed ......................................................."
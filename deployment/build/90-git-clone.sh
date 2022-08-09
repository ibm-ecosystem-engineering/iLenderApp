#!/bin/sh

echo "process started ......................................................."

export ROOT_FOLDER=/root/gandhi/ilender/mcm

rm -rfd $ROOT_FOLDER/ilender
cd $ROOT_FOLDER

echo "Cloning ilender ....."
git clone https://github.ibm.com/SI-Lab-Apps/ilender.git

echo "process completed ......................................................."
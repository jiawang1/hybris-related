#!/bin/bash

CONFIG_URL=`pwd`
BASE_URL=$CONFIG_URL"/properties"
HOST_NAME=`hostname`
HOST_CONFIG=$BASE_URL
ENV_CONFIG=$BASE_URL
COMMON_CONFIG=$CONFIG_URL"/local.properties"
FILE_NAME="local.properties"


function findConfiguration(){
     
    echo $BASE_URL
    HOST_CONFIG=`find $BASE_URL -name "$HOST_NAME"`
    ENV_CONFIG=${HOST_CONFIG%/*}"/$FILE_NAME"
    HOST_CONFIG=$HOST_CONFIG"/$FILE_NAME"
    echo $HOST_CONFIG
    echo $ENV_CONFIG
}

function flushToCommon(){
    source=$1
    ## To avoid infinate loop
    if [[ "$source" = "$COMMON_CONFIG" ]]; then
        echo "Error: Source $source cannot be the same file as common config file $COMMON_CONFIG"
        return
    fi
    cat $source | while read LINE
    do
        if [[ "$LINE" = "" || ${LINE:0:1} = "#" ]]; then
		continue
        fi
        realkey=${LINE%%=*}
        key=$realkey'='
        echo "key is $realkey"
        ## search key in common configuration and comment it. for mac
        #sed -i "" s/^$key/##$key/ $COMMON_CONFIG
        ## below is used for linux
        sed -i s/^$key/##$key/ $COMMON_CONFIG
    done

    echo "" >> $COMMON_CONFIG
    cat $source >> $COMMON_CONFIG
}

function backupCommon(){
    if [ ! -f $COMMON_CONFIG ]; then
        echo "Common configration file $COMMON_CONFIG not found!"
        exit 1
    fi
    cp $COMMON_CONFIG $COMMON_CONFIG.bk
}


findConfiguration
backupCommon
flushToCommon $ENV_CONFIG
flushToCommon $HOST_CONFIG

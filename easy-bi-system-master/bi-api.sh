#!/bin/bash

if [ $# -lt 1 ]
then
  echo "No Args Input...."
  exit;
fi

case $1 in

"start")
  nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn32m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC system-service.jar >/dev/null 2>&1&
  nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn32m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC metadata-service.jar >/dev/null 2>&1&
  nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn32m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC datasource-service.jar >/dev/null 2>&1&
  nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn32m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC main-service.jar >/dev/null 2>&1&

;;

"stop")
  PID0=$( ps -ef | grep main-service.jar | grep -v grep | awk '{print $2}')
  PID1=$( ps -ef | grep datasource-service.jar | grep -v grep | awk '{print $2}')
  PID2=$( ps -ef | grep metadata-service.jar | grep -v grep | awk '{print $2}')
  PID3=$( ps -ef | grep system-service.jar | grep -v grep | awk '{print $2}')

  if [ -z "$PID0" ]
    then
        echo main-service is already stopped
    else
        echo main-service is stopped
        kill $PID0
    fi

  if [ -z "$PID1" ]
  then
      echo datasource-service is already stopped
  else
      echo datasource-service is stopped
      kill $PID1
  fi

  if [ -z "$PID2" ]
  then
      echo metadata-service is already stopped
  else
      echo metadata-service is stopped
      kill $PID2
  fi

  if [ -z "$PID3" ]
    then
        echo system-service is already stopped
    else
        echo system-service is stopped
        kill $PID3
    fi

;;

"psAll")
  PID0=$( ps -ef | grep main-service.jar | grep -v grep | awk '{print $2}')
  PID1=$( ps -ef | grep datasource-service.jar | grep -v grep | awk '{print $2}')
  PID2=$( ps -ef | grep metadata-service.jar | grep -v grep | awk '{print $2}')
  PID3=$( ps -ef | grep system-service.jar | grep -v grep | awk '{print $2}')

  if [ -z "$PID0" ]
    then
        echo main-service is already stopped
    else
        echo main-service is running
    fi

  if [ -z "$PID1" ]
  then
      echo datasource-service is already stopped
  else
      echo datasource-service is running
  fi

  if [ -z "$PID2" ]
  then
      echo metadata-service is already stopped
  else
      echo metadata-service is running
  fi

  if [ -z "$PID3" ]
    then
        echo system-service is already stopped
    else
        echo system-service is running
    fi

;;

*)
  echo "Input Args Error..."
;;
esac
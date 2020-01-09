# Iot Mobile App
#### Application for showing the relationships of devices via BLE and Wifi.

![alt text](https://github.com/IsaacWalker/IotMobileApp/blob/master/img/app_diagram.png "App Diagram")

## 1. Application UI
#### Nothing at the moment - will show relationships and allow the user to update settings.

## 2. Foreground Service
#### The Service which acts as a container for the workers (threads) and storage.

## 3. Scanning Worker
#### A worker which reads from bluetooth, wifi and device sensors and pushes to the shared scan database.

## 4. Pusher Worker
#### A worker which removes scans from the shared scan database and pushes them to the web service. This device consumes the /api/scan service. It operates at a set interval, retrieving the scans out of database and pushing them to the service in batches.

## 5. Scan Database
#### This is a shared data structure used by the Scanning and Pusher workers. 

## 6. Configuration
#### The configuration database provides settings (configs) to the worker and application UI.

## 7. ConfigWorker
#### This worker is responsible for updating the configuration database with the settings specified by the remote service. It operates at a set interval, consuming the /api/setting service.


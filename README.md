# Iot Mobile App
#### Application for showing the relationships of devices via BLE and Wifi.

### Components
## Frontend App (UI)
#### Nothing at the moment - will show relationships and allow the user to update settings.

## Foreground Service
#### The Service which acts as a container for the workers (threads) and storage.

## Shared Database
#### A shared structure which allows the sharing of data between the workers.

## Scanning Worker
#### A worker which reads from bluetooth, wifi and device sensors and pushes to the shared scan database.

## Pusher Worker
#### A worker which removes scans from the shared scan database and pushes them to the web service.